package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class PayDao extends BaseDao{
    Scanner in = new Scanner(System.in);
    public void findPay(){
        String sno=null;
        boolean sExist = false;
        while (!sExist){
            System.out.println("请输入学号：");
            sno = in.next();
            if (!findStudent(sno)){
                System.out.println("该学生不存在！请重新输入！");
            } else {
				s_exist = true;
			}
        }
        find_no(sno);
        String sql = "SELECT pay_time,need_money,paid_money,diff_money FROM pay WHERE sno=?";
        ResultSet rs = executeQuery(sql,new Object[]{sno});
        try {
            if (rs.next()){
                System.out.println("该学生缴纳的时间是："+rs.getDate(1)+"\n该学生应缴纳的费用是："+rs.getDouble(2)
                        +"\n该学生实际缴纳费用是："+rs.getDouble(3)+
                        "\n该学生还应缴纳："+rs.getDouble(4));
            } else {
				System.out.println("未找到该学生的缴费记录！");
			}
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close_rs(rs);
        }
    }
    public void findPayBytime(){
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
        String sql="SELECT sno,need_money,paid_money,diff_money FROM pay WHERE pay_time > ? AND pay_time<?";
        ResultSet rs = executeQuery(sql,new Object[]{start_d,end_d});
        try {
            System.out.println("学号\t需缴款\t已交款\t欠款");
            while (rs.next()){
                System.out.print(rs.getString("sno")+"\t");
                System.out.print(rs.getString("need_money")+"\t");
                System.out.print(rs.getString("paid_money")+"\t");
                System.out.print(rs.getString("diff_money")+"\n");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally {
            close_rs(rs);
        }
    }
}
