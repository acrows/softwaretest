package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import utils.DBUtil;

public class BaseDao {
    Scanner in = new Scanner(System.in);

    //数据库更新
    public int executeUpdate(String sql, Object[] params) {
        Connection connection = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        int result = -1;
        try {
            pstmt = connection.prepareStatement(sql);
            if (params != null) {
                //有参数，就需要给参数赋值
                for (int i = 0; i < params.length; i++) {
                    pstmt.setObject(i + 1, params[i]);
                }
                result = pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtil.closeAll(connection, pstmt, null);
        return result;
    }

    //数据库查询
    public ResultSet executeQuery(String sql, Object[] params) {
        Connection connection = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = connection.prepareStatement(sql);
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    pstmt.setObject(i + 1, params[i]);
                }
            }
            rs = pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public void getResult(int i) {
        if (i > 0) {
            System.out.println("添加成功！");
        } else {
            System.out.println("添加失败！");
        }
    }

    public String judgeSex() {
        boolean is = false;
        String sex = null;
        while (!is) {
            System.out.println("请输入性别：");
            sex = in.next();
            if (!("男".equals(sex) || "女".equals(sex))) {
                System.out.println("输入错误!请重新输入！");
            } else {
                is = true;
            }
        }
        return sex;
    }

    //判断房间是否存在
    public boolean judgeRoom(String rno) {
        boolean exist = true;
        String sql = "SELECT * FROM room WHERE rno=?";
        ResultSet rs = executeQuery(sql, new Object[]{rno});
        try {
            if (!rs.next()) {
                exist = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exist;
    }

    //判断学生和房间性别是否匹配
    public boolean judgeSexMatch(String sno, String rno) {
        boolean match = false;
        String ssex = null, rsex = null;
        String sql = "SELECT ssex,rsex FROM student,room WHERE sno=? AND rno=?";
        ResultSet rs = executeQuery(sql, new Object[]{sno, rno});
        try {
            if (rs.next()) {
                ssex = rs.getString(1);//获取学生的性别
                rsex = rs.getString(2);//获取房间性别
                if (ssex.equals(rsex)) {
                    //性别匹配
                    match = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return match;
    }

    //判断该床位是否有人
    public boolean judgeBedNo(String rno, String bedno) {
        boolean match = false;
        String sql = "SELECT * FROM stay_room WHERE rno=? AND bedno=? AND out_room=0";
        ResultSet rs = executeQuery(sql, new Object[]{rno, bedno});
        try {//如果该床位有人
            if (rs.next()) {
                match = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return match;
    }

    //判断房间是否满人
    public boolean judgeRoomFull(String rno) {
        boolean full = false;
        String sql = "";
        ResultSet rs;
        int inSum, roomSum;
        sql = "SELECT COUNT(*)FROM stay_room WHERE rno=? and out_room=0";
        rs = executeQuery(sql, new Object[]{rno});
        sql = "SELECT sum FROM room WHERE rno=?";
        ResultSet r = executeQuery(sql, new Object[]{rno});
        try {
            if (rs.next() && r.next()) {
                in_sum = rs.getInt(1);//获得该房间实际住了多少人
                room_sum = r.getInt(1);//获得该房间能住多少人
                if (in_sum == room_sum) {
                    full = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close_rs(r);
            close_rs(rs);
        }
        return full;
    }

    //查找该学生是否已经入住
    public boolean findStudentStay(String sno) {
        String sql = "SELECT * FROM stay_room WHERE rno=? AND out_room=0";
        ResultSet rs = executeQuery(sql, new Object[]{sno});
        boolean exist = false;
        try {
            if (rs.next()) {
                exist = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exist;
    }

    //根据学号查找该学生入住的房间号和床位号
    public Map<String, String> findNo(String sno) {
        Map<String, String> map = new HashMap<String, String>(16);
        String sql = "SELECT rno,bedno FROM stay_room WHERE sno=?";
        ResultSet rs = executeQuery(sql, new Object[]{sno});
        String rno = null, bedno = null;
        try {
            if (rs.next()) {
                rno = rs.getString(1);
                bedno = rs.getString(2);
                System.out.println("该学生入住的房间号是：" + rno + "\n该学生入住的床位号是：" + bedno);
            } else {
                System.out.println("未找到该学生租住信息！");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        map.put("rno", rno);
        map.put("bedno", bedno);
        return map;
    }

    //查找学生是否存在
    public boolean findStudent(String sno) {
        boolean exist = true;
        String sql = "SELECT * FROM student WHERE sno=?";
        ResultSet rs = executeQuery(sql, new Object[]{sno});
        try {
            if (!rs.next()) {
                exist = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exist;

    }

    //判断房间还能入住多少人
    public int[] judgeRoomNum(String rno) {
        int num = 0;
        int[] a = new int[2];
        String sql = "";
        ResultSet rs;
        int inSum, roomSum;
        sql = "SELECT COUNT(*)FROM stay_room WHERE rno=? and out_room=0";
        rs = executeQuery(sql, new Object[]{rno});
        sql = "SELECT sum FROM room WHERE rno=?";
        ResultSet r = executeQuery(sql, new Object[]{rno});
        try {
            if (rs.next() && r.next()) {
                in_sum = rs.getInt(1);//获得该房间实际住了多少人
                room_sum = r.getInt(1);//获得该房间能住多少人
                num = room_sum - in_sum;//房间还能住多少人
                a[0] = num;
                a[1] = room_sum;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close_rs(r);
            close_rs(rs);
        }
        return a;
    }

    public void closeRs(ResultSet rs) {
        DBUtil.closeAll(null, null, rs);
    }
}
