package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class RecordDao extends BaseDao{
    Scanner in = new Scanner(System.in);
    public void selectReplace(){
        String sno=null;
        boolean sExist = false;
        while (!sExist){
            System.out.println("请输入学号：");
            sno = in.next();
            if (!find_student(sno)){
                System.out.println("该学生不存在！请重新输入！");
            } else {
				s_exist = true;
			}
        }
        String sql="SELECT * FROM replace_room WHERE sno=?";
        ResultSet rs = executeQuery(sql,new Object[]{sno});
        try {
            if (rs.next()){
                System.out.println("学号\t新房间号\t新床位号\t调房时间");
                System.out.print(rs.getString(1)+"\t");
                System.out.print(rs.getString(2)+"\t");
                System.out.print(rs.getString(3)+"\t");
                System.out.println(rs.getString(4));
            } else {
				System.out.println("未找到该学生的调房记录！");
			}
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            close_rs(rs);
        }
    }
    //根据学号查找缴费记录
    public void selectPay(){
        String sno=null;
        boolean sExist = false;
        while (!sExist){
            System.out.println("请输入学号：");
            sno = in.next();
            if (!find_student(sno)){
                System.out.println("该学生不存在！请重新输入！");
            } else {
				s_exist = true;
			}
        }
        String sql="SELECT * FROM pay WHERE sno=?";
        ResultSet rs = executeQuery(sql,new Object[]{sno});
        try {
            if (rs.next()){
                    System.out.println("学号\t\t需缴款\t已交款\t欠款\t\t交款时间");
                    System.out.print(rs.getString(1)+"\t\t");
                    System.out.print(rs.getString(2)+"\t");
                    System.out.print(rs.getString(4)+"\t\t");
                    System.out.print(rs.getString(5)+"\t\t");
                System.out.println(rs.getString(3));

            } else {
				System.out.println("未找到该学生的缴费信息");
			}
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close_rs(rs);
        }
    }
    public void selectStayByTime(){
        String y,m,d;
        String startD = null;
        String endD = null;
        System.out.println("请输入开始时间：");
        System.out.println("请输入年：");
        y = in.next();
        System.out.println("请输入月：");
        m = in.next();
        System.out.println("请输入日：");
        d = in.next();
        start_d = y+"-"+m+"-"+d;
        System.out.println("请输入结束时间：");
        System.out.println("请输入年：");
        y = in.next();
        System.out.println("请输入月：");
        m = in.next();
        System.out.println("请输入日：");
        d = in.next();
        end_d = y+"-"+m+"-"+d;
        String sql="SELECT * FROM stay_room WHERE intime > ? AND intime<?";
        ResultSet rs = executeQuery(sql,new Object[]{start_d,end_d});
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
    public void selectOutByTime(){
        String y,m,d;
        String startD= null;
        String endD = null;
        System.out.println("请输入开始时间：");
        System.out.println("请输入年：");
        y = in.next();
        System.out.println("请输入月：");
        m = in.next();
        System.out.println("请输入日：");
        d = in.next();
        start_d = y+"-"+m+"-"+d;
        System.out.println("请输入结束时间：");
        System.out.println("请输入年：");
        y = in.next();
        System.out.println("请输入月：");
        m = in.next();
        System.out.println("请输入日：");
        d = in.next();
        end_d = y+"-"+m+"-"+d;
        String sql="SELECT * FROM out_room WHERE out_time > ? AND out_time<?";
        ResultSet rs = executeQuery(sql,new Object[]{start_d,end_d});
        try {
            System.out.println("学号\t原因\t 需缴纳金额\t实际缴纳金额\t欠款\t\t退宿时间");
            while (rs.next()){
                System.out.print(rs.getString(1)+"\t");
                System.out.print(rs.getString(3)+"\t");
                System.out.print(rs.getString(4)+"\t\t");
                System.out.print(rs.getString(5)+"\t\t\t");
                System.out.print(rs.getString(6)+"\t\t");
                System.out.print(rs.getString(2)+"\n");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            close_rs(rs);
        }
    }
}
