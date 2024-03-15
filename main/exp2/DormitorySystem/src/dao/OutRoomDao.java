package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class OutRoomDao extends BaseDao{
    public void addOutRoon(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd ");
        Date d= new Date();
        String outtime = simpleDateFormat.format(d);
        Scanner in = new Scanner(System.in);
        String reason = null;
        String rno,bedno;
        double needMoney=0;
        double paidMoney=0;
        double diffMoney=0;
        String sql = "SELECT diff_money FROM pay WHERE sno=?";
        String sql2 = "SELECT outtime FROM stay_room WHERE sno=?";
        String sno;
        System.out.println("请输入学号：");
        sno = in.next();
        ResultSet rs =  executeQuery(sql,new Object[]{sno});
        ResultSet rs2 = executeQuery(sql2,new Object[]{sno});
        try {
            if (!rs2.next()){
                System.out.println("未找到该学生租住信息！");
            }
            else{
                if (rs.next()){
                    needMoney = rs.getDouble(1);
                    Date startime = rs2.getDate(1);
                    findNo(sno);//查找该学生入住的房间、床位号
                    long star = startime.getTime();
                    long end = d.getTime();
                    double days = (star-end)/24/60/60/1000;
                    if (days>0) {
						System.out.println("该学生入住天数还剩："+days+"天");
					}
                    if (needMoney>0){//未缴纳全租款
                        if(needMoney-days*5>0){//计算完退款后仍未补上欠款
                            System.out.println("该学生还应缴费："+(days*5-need_money)+"其中含应退款的："+(days*5));
                            System.out.println("请输入缴费数目：");
                            paid_money = in.nextDouble();
                            diff_money = need_money - paid_money;
                            if (diff_money>0) {
								System.out.println("该学生未完全缴费！");
							}
                        }
                        else {
                            System.out.println("应给该学生退款："+(days*5-need_money)+"元");
                            need_money = 0;
                        }
                    }
                    else {//已缴纳全租款
                        System.out.println("该学生已完全缴费！");
                        need_money = 0;
                        diff_money = 0;
                        paid_money = 0;
                    }
                    sql="INSERT INTO out_room VALUES(?,?,?,?,?,?)";
                    int i = executeUpdate(sql,new Object[] {sno,outtime,reason,need_money,paid_money,diff_money});
                    get_result(i);
                    sql = "UPDATE stay_room SET out_room = 1 WHERE sno=?";
                    executeUpdate(sql,new Object[]{sno});
                    sql = "UPDATE pay set need_money=?,pay_time=?,need_money=?,paid_money=paid_money+?,diff_money=? where sno=?";
                    executeUpdate(sql,new Object[]{need_money,outtime,need_money,paid_money,diff_money,sno});
                }
                else {
                    System.out.println("未找到该学生的缴费记录！");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close_rs(rs);
        }
    }
}
