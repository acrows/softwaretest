package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//根据学号查询
public class SelectDao extends BaseDao{
    Scanner in = new Scanner(System.in);
    public void select_by_sno(){
        String sno=null;
        boolean s_exist = false;
        while (!s_exist){
            System.out.println("请输入学号：");
            sno = in.next();
            if (!find_student(sno)){
                System.out.println("该学生不存在！请重新输入！");
            } else {
				s_exist = true;
			}
        }
        String sql="SELECT * FROM stay_room WHERE sno=?";
        ResultSet rs = executeQuery(sql,new Object[]{sno});
        try {
            if (rs.next()){
                System.out.println("学号\t房间号\t床位号\t入住时间\t\t\t\t入住剩余天数\t入住截至时间\t\t\t备注\t\t是否退租(1.是0.否)");
                System.out.print(rs.getString(1)+"\t");
                System.out.print(rs.getString(2)+"\t\t");
                System.out.print(rs.getString(3)+"\t");
                System.out.print(rs.getString(4)+"\t\t");
                System.out.print(rs.getString(5)+"\t\t\t");
                System.out.print(rs.getString(6)+"\t");
                System.out.print(rs.getString(7)+"\t\t");
                System.out.print(rs.getString(8)+"\n");
            } else {
				System.out.println("未找到该学生的住宿信息！");
			}
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close_rs(rs);
        }
    }
    //根据性别查询
    public void select_by_sex(){
        String sex=null;
        System.out.println("请输入性别：");
        sex = in.next();
        String sql="SELECT * FROM student,stay_room WHERE student.sno = stay_room.sno AND student.ssex=?";
        ResultSet rs = executeQuery(sql,new Object[]{sex});
        try {
            System.out.println("学号\t房间号\t床位号\t入住时间\t\t\t\t入住剩余天数\t入住截至时间\t\t\t备注\t\t是否退租(1.是0.否)");
            while (rs.next()){
                System.out.print(rs.getString(5)+"\t");
                System.out.print(rs.getString(6)+"\t\t");
                System.out.print(rs.getString(7)+"\t");
                System.out.print(rs.getString(8)+"\t\t");
                System.out.print(rs.getString(9)+"\t\t\t");
                System.out.print(rs.getString(10)+"\t");
                System.out.print(rs.getString(11)+"\t\t");
                System.out.print(rs.getString(12)+"\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close_rs(rs);
        }
    }
    //根据房间号查询
    public void select_by_rno(){
        String rno=null;
        boolean s_exist = false;
        while (!s_exist){
            System.out.println("请输入学号：");
            rno = in.next();
            if (!judge_room(rno)){
                System.out.println("该房间不存在！请重新输入！");
            } else {
				s_exist = true;
			}
        }
        String sql="SELECT * FROM stay_room WHERE rno=?";
        ResultSet rs = executeQuery(sql,new Object[]{rno});
        try {
            System.out.println("学号\t房间号\t床位号\t入住时间\t\t\t\t入住剩余天数\t入住截至时间\t\t\t备注\t\t是否退租(1.是0.否)");
            while (rs.next()){
                System.out.print(rs.getString(1)+"\t");
                System.out.print(rs.getString(2)+"\t\t");
                System.out.print(rs.getString(3)+"\t");
                System.out.print(rs.getString(4)+"\t\t");
                System.out.print(rs.getString(5)+"\t\t\t");
                System.out.print(rs.getString(6)+"\t");
                System.out.print(rs.getString(7)+"\t\t");
                System.out.print(rs.getString(8)+"\n");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            close_rs(rs);
        }
    }
    //统计人数
    public void get_sum(){
        int sex;
        String sql=null;
        System.out.println("请输入性别1.男2.女3.总人数");
        ResultSet rs=null;
        sex = in.nextInt();
        switch (sex){
            case 1:
                sql="SELECT COUNT(*) FROM stay_room,student WHERE  stay_room.sno=student.sno AND student.ssex='男' AND out_room=0";
                rs=executeQuery(sql,new Object[]{});
                try {
                    if (rs.next()) {
						System.out.println("男生入住总人数为："+rs.getString(1));
					}
                    break;
                } catch (SQLException e) {
                    e.printStackTrace();
                }finally {
                    close_rs(rs);
                }
            case 2:
                sql="SELECT COUNT(*) FROM stay_room,student WHERE  stay_room.sno=student.sno AND student.ssex='女' AND out_room=0";
                rs=executeQuery(sql,new Object[]{});
                try {
                    if (rs.next()) {
						System.out.println("女生入住总人数为："+rs.getString(1));
					}
                    break;
                } catch (SQLException e) {
                    e.printStackTrace();
                }finally {
                    close_rs(rs);
                }
            case 3:
                sql="SELECT COUNT(*) FROM stay_room WHERE out_room=0";
                rs=executeQuery(sql,new Object[]{});
                try {
                    if (rs.next()) {
						System.out.println("当前入住总人数为："+rs.getString(1));
					}
                    break;
                } catch (SQLException e) {
                    e.printStackTrace();
                }finally {
                    close_rs(rs);
                }
                defalut:
        }
    }
    //查询宿舍可入住情况
    public void select_room(){
        String sql="SELECT COUNT(*),SUM(room.sum)-sumin FROM room,(SELECT count(*) AS sumin FROM stay_room WHERE out_room=0) AS stayin GROUP BY rno ORDER BY rno;";
        ResultSet rs;
        String rno=null;
        rs = executeQuery(sql,new Object[]{});
        try {
            if (rs.next()){
                System.out.println("当前有房间："+rs.getInt(1)+"间");
                System.out.println("当前房间可住人数有："+rs.getInt(2)+"人");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close_rs(rs);
        }
        int [] a = new int[2];
        boolean exist=false;
        while (!exist){
            System.out.println("请输入要查询的房间号：");
            rno = in.next();
            if (!judge_room(rno)) {
				System.out.println("该房间不存在！请重新输入！");
			} else {
				exist = true;
			}
        }
        a = judge_room_num(rno);//获得该宿舍还能住多少人
        StringBuffer sb = new StringBuffer();
        System.out.println("该宿舍还能入住："+a[0]+"人");
        sql = "SELECT bedno FROM stay_room WHERE rno=? AND out_room=0";
        rs = executeQuery(sql,new Object[]{rno});//获得当前入住的床位号
        StringBuffer bedin = new StringBuffer();
        StringBuffer bed = new StringBuffer();
        try {
            if (rs.next()){
                for (int i = 0; i < a[1]-a[0]; i++) {//将已经入住的床位号存入bedin中
                    bedin.append(rs.getString((i+1)));
                }
                for (int i = 0; i < a[1]; i++) {
                    if (bedin.indexOf((i+1)+"")<0){
                        bed.append((i+1)+"");
                    }
                }
                System.out.println("该宿舍还能入住的床位号是：");
                for (int i = 0; i < bed.length(); i++) {
                    System.out.println(bed.charAt(i)+"号");
                }
            }
            else {
                System.out.println("该房间所用床位都能入住！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
