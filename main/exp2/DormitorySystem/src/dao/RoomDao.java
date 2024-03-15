package dao;

import entity.Room;

import java.nio.channels.SelectableChannel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class RoomDao extends  BaseDao{
    Scanner in  = new Scanner(System.in);
    public void addroom(){
        String sql="INSERT INTO room values(?,?,?)";
        String rno;
        String rsex="";
        int sum;
        System.out.println("请输入房间号：");
        rno = in.next();
        rsex = judge_sex();//输入性别
        System.out.println("请输入房间住宿人数：");
        sum = in.nextInt();
        Room r = new Room(rno,rsex,sum);
        int rs = executeUpdate(sql,new Object[]{r.getRno(),r.getRsex(),r.getSum()});
        if(rs>0){
            System.out.println("添加房间成功！");
        }
        else {
            System.out.println("添加房间失败！");
        }
    }
    public void changeRoom(){
        String sql="SELECT rno FROM room WHERE rno=?";
        String rno;
        String rsex;
        int sum;
        System.out.println("请输入需要更改的房间号：");
        rno = in.next();
        ResultSet rs = executeQuery(sql,new Object[]{rno});
        try {
            if (!rs.next()){
                System.out.println("该房间不存在！");
            }
            else {
                System.out.println("房间存在！");
                System.out.println("请输入房间住宿性别：");
                rsex = in.next();
                System.out.println("请输入房间住宿人数：");
                sum = in.nextInt();
                sql = "UPDATE  room  SET rsex=?, sum=? WHERE rno = ?";
                int r = executeUpdate(sql,new Object[]{rsex,sum,rno});
                if (r>0) {
					System.out.println("更新成功！");
				} else {
					System.out.println("更新失败！");
				}
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close_rs(rs);
        }
    }
}
