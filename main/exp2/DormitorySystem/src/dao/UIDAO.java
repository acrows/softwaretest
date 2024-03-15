package dao;

import entity.OutRoom;
import entity.ReplaceRoom;
import entity.Room;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class UIDAO extends BaseDao{
    Scanner in = new Scanner(System.in);
    //登录
    public boolean logIn(){
        String user,password;
        String sql="select count(*) from admin where user=? and password=?";
        ResultSet rs=null;
        boolean key = false;
        System.out.println("请输入管理员账号：");
        user = in.next();
        System.out.println("请输入管理员密码：");
        password = in.next();
        rs = executeQuery(sql,new Object[]{user,password});
        try {
            if (rs.next()){
                int i = rs.getInt(1);
                if (i>0) {
					key = true;
				}
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return key;
    }
    //录入学生信息
    public void addStudent(){
        StudentDao sd = new StudentDao();
        sd.addsutdent();
    }
    //房间管理菜单
    public void room(){
        RoomDao rd = new RoomDao();
        int option;
        boolean exit=false;
        while (!exit){
            System.out.println("1.宿舍登记\t2.宿舍信息修改\t3.退出");
            option = in.nextInt();
            switch (option){
                case 1:
                    rd.addroom();
                    break;
                case 2:
                    rd.change_room();
                    break;
                case 3:
                    exit = true;
                    break;
                    default:
            }
        }
    }
    //住宿管理菜单
    public void stay(){
        int option;
        StayDao sd = new StayDao();
        ChangeStayDao csd = new ChangeStayDao();
        OutRoomDao ord = new OutRoomDao();
        ReplaceRoomDao rrd = new ReplaceRoomDao();
        boolean exit = false;
        while (!exit){
            System.out.println("请输入1.入住办理\t2.退宿办理\t3.宿舍调换\t4.住宿调整\t5.退出");
            option = in.nextInt();
            switch (option){
                case 1:
                    sd.addStay();
                    break;
                case 2:
                    ord.addOutRoon();
                    break;
                case 3:
                    rrd.addReplaceRoon();
                    break;
                case 4:
                    csd.changestay();
                    break;
                case 5:
                    exit = true;
                    break;
                default:
            }
        }
    }
    //费用管理模块
    public void pay(){
        int option;
        boolean exit=false;
        PayDao pd = new PayDao();
        while (!exit){
            System.out.println("请输入1.根据学号查询费用缴纳信息\t2.根据时间查询费用缴纳\t3.退出");
            option = in.nextInt();
            switch (option){
                case 1:
                    pd.find_pay();
                    break;
                case 2:
                    pd.find_pay_bytime();
                    break;
                case 3:
                    exit = true;
                    break;
                default:
            }
        }
    }
    //查询菜单
    public void basicCheck(){
        int option;
        boolean exit=false;
        SelectDao sd = new SelectDao();
        while (!exit){
            System.out.println("请输入1.根据学号查询住宿信息\t2.根据性别查询住宿信息\t3.根据房间号查询\t4.退出");
            option = in.nextInt();
            switch (option){
                case 1:
                    sd.select_by_sno();
                    break;
                case 2:
                    sd.select_by_sex();
                    break;
                case 3:
                    sd.select_by_rno();
                    break;
                case 4:
                    exit = true;
                    break;
                default:
            }
        }
    }
    //记录查询
    public void recordCheck(){
        int option;
        boolean exit=false;
        RecordDao rd = new RecordDao();
        while (!exit){
            System.out.println("请输入1.根据学号查询调房记录\t2.根据学号查询缴费记录\t3.根据时间查询入住记录\t4.根据时间查询退宿记录\t5.退出");
            option = in.nextInt();
            switch (option){
                case 1:
                    rd.select_replace();
                    break;
                case 2:
                    rd.select_pay();
                    break;
                case 3:
                    rd.select_stay_by_time();
                    break;
                case 4:
                    rd.select_out_by_time();
                    break;
                case 5:
                    exit = true;
                    break;
                default:
            }
        }
    }
    //人数统计
    public void checkSum(){
        int option;
        SelectDao sd = new SelectDao();
        boolean exit = false;
        while (!exit){
            System.out.println("1.当前人数统计信息\t2.当前宿舍统计信息\t3.退出");
            option = in.nextInt();
            switch (option){
                case 1:
                    sd.get_sum();
                    break;
                case 2:
                    sd.select_room();
                    break;
                case 3:
                    exit = true;
                    break;
                default:
            }
        }
    }
}
