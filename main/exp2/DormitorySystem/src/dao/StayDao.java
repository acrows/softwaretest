package dao;

import entity.StayRoom;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class StayDao extends BaseDao{
    Scanner in = new Scanner(System.in);
    public void addStay(){
        String sql="INSERT INTO stay_room VALUES(?,?,?,?,?,?,?,?)";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date d= new Date();
        String sno=null;
        String rno=null;
        String bedno=null;
        String intime;
        int days;
        String outtime;
        String remark=null;//备注信息
        double needMoney;
        double paidMoney;
        double diffMoney;
        int outRoom=0;
        boolean sExist = false;
        while (!sExist){
            System.out.println("请输入学号：");
            sno = in.next();
            if (!find_student(sno)){
                System.out.println("该学生不存在！请重新输入！");
            }
            else if (find_student_stay(sno)){
                System.out.println("该学生已经入住！请重新输入！");
            }
            else{
                s_exist = true;
            }

        }
        boolean match = false,full=false,exist=false;
        while (!match&&!full&&!exist){
            match = false;
            full=false;
            exist=false;
            System.out.println("请输入房间号：");
            rno = in.next();
            if (!judge_room(rno)) {
				System.out.println("该房间不存在！请重新输入！");
			} else if (judge_room_full(rno)) {
				System.out.println("该房间已满人！请重新输入！");
			} else if (!judge_sex_match(sno,rno)) {
				System.out.println("学生性别与房间性别不匹配！请重新输入！");
			} else{
                exist = true;
                match = true;
                full = true;
            }

        }
        match = false;
        while (!match){
            System.out.println("请输入床位号：");
            bedno = in.next();
            if(judge_bedno(rno,bedno)) {
				System.out.println("该床位已经有人了！请重新输入");
			} else {
				match = true;
			}
        }
        System.out.println("请输入住宿时间：(一天5元)");
        days = in.nextInt();
        need_money = days*5;
        //获取时间
        intime = simpleDateFormat.format(d);
        outtime = simpleDateFormat.format(new Date(d.getTime() + (long) days* 24 * 60 * 60 * 1000));

        System.out.println("应缴费用："+need_money);
        System.out.println("请输入现缴费数目：");
        paid_money = in.nextDouble();
        diff_money = need_money - paid_money;
        StayRoom sr = new StayRoom(sno,rno,bedno,intime,days,outtime,remark,out_room);
        int i = executeUpdate(sql,new Object[]{sno,rno,bedno,intime,days,outtime,remark,out_room});
        get_result(i);
        sql = "INSERT INTO pay VALUES(?,?,?,?,?,?)";
        executeUpdate(sql,new Object[]{sno,need_money,intime,need_money,paid_money,diff_money});
    }

}
