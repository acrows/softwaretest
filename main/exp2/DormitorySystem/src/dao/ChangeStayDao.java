package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ChangeStayDao extends BaseDao {
	Scanner in = new Scanner(System.in);

	public void changestay(){
        String sno=null;
        String sql="SELECT outtime FROM stay_room WHERE sno=?";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date d= new Date();
        String time = simpleDateFormat.format(d);
        int s,days;
        double need=0;
        double paid;
        double diff;
        Date outd = null;
        String outttime;
        System.out.println("请输入学号：");
        sno = in.next();
        find_no(sno);//查找该学生当前入住的房间、床位号
        ResultSet rs = executeQuery(sql,new Object[]{sno});
        sql = "SELECT diff_money FROM pay WHERE sno=?";
        ResultSet rs2 = executeQuery(sql,new Object[]{sno});
        try {
            if (rs.next()){
                outd = rs.getDate(1);//获取该学生目前退宿时间
                if (rs2.next()){
                    need = rs2.getDouble(1);//获取该学生还有多少钱未交
                    System.out.println("请选择1.延长入住时间\t2.提前住宿时间");
                    s = in.nextInt();
                    switch (s){
                        case 1:
                            System.out.println("请输入延长时间：");
                            days = in.nextInt();
                            outd = new Date(outd.getTime()+(long)days* 24 * 60 * 60 * 1000 );
                            need = need + 5 * days;
                            System.out.println("该学生需缴费："+need+"请输入该学生缴费金额：");
                            paid = in.nextDouble();
                            diff = paid - need;
                            if (diff<0) {
								System.out.println("该学生未完全缴费！");
							}
                            //更新change_stay表信息
                            sql = "UPDATE pay set need_money=?,pay_time=?,need_money=?,paid_money=paid_money+?,diff_money=? where sno=?";
                            executeUpdate(sql,new Object[]{need,time,need,paid,Math.abs(diff),sno});
                            sql = "UPDATE stay_room SET outtime=? WHERE sno=?";
                            executeUpdate(sql,new Object[]{time,sno});
                            break;
                        case 2:
                            System.out.println("请输入提前住宿时间：");
                            days = in.nextInt();
                            outd = new Date(outd.getTime()-(long)days* 24 * 60 * 60 * 1000 );
                            need = need - 5 * days;
                            if (need > 0){
                                System.out.println("该学生需缴费："+need+"请输入该学生缴费金额：");
                                paid = in.nextDouble();
                                diff = paid - need;
                                if (diff<0) {
									System.out.println("该学生未完全缴费！");
								}
                            }
                            else {
                                System.out.println("应给该学生退"+Math.abs(need)+"元");
                                paid = 0;
                                need = 0;
                                diff = 0;
                            }
                            sql = "UPDATE pay set need_money=?,pay_time=?,need_money=?,paid_money=?,diff_money=? where sno=?";
                            executeUpdate(sql,new Object[]{need,time,need,paid,Math.abs(diff),sno});
                            sql = "UPDATE stay_room SET outtime=? WHERE sno=?";
                            executeUpdate(sql,new Object[]{time,sno});
                            break;
                          defalut:
                    }
                } else {
					System.out.println("未找到该学生缴纳信息！");
				}
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            close_rs(rs);
        }

    }
}
