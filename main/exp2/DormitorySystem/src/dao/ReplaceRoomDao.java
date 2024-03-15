package dao;

import entity.ReplaceRoom;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;

public class ReplaceRoomDao extends  BaseDao{
    Scanner in = new Scanner(System.in);
    public void  addReplaceRoon(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date d= new Date();
        ReplaceRoom replaceRoom = new ReplaceRoom();
        String changeTime = simpleDateFormat.format(d);
        String sql="";
        String sno,rno=null,bedno=null;
        String newRno=null,newBedno=null;
        String replaceSno=null;//被换位置学生的学号
        int i;
        System.out.println("请输入学号：");
        sno = in.next();
        ResultSet rs;
        sql = "SELECT rno,bedno FROM stay_room WHERE sno=?";
        rs = executeQuery(sql,new Object[]{sno});
        try {
            if(!rs.next()){
                System.out.println("未找到该学生入住信息！");
            }
            else {//找到了该学生信息
                replaceRoom.setSno(sno);
                Map<String,String> map = find_no(sno);//查找该学生的房间、床位号
                rno = map.get("rno");
                bedno = map.get("bedno");
                boolean match = false,exist=false;
                while (!match&&!exist){
                    match = false;
                    exist=false;
                    System.out.println("请输入该学生想要更换的房间号：");
                    new_rno = in.next();
                    if (!judge_room(new_rno)) {
						System.out.println("该房间不存在！请重新输入！");
					} else if (!judge_sex_match(sno,new_rno)) {
						System.out.println("学生性别与房间性别不匹配！请重新输入！");
					} else{
                        match = true;
                        exist = true;
                    }

                }
                if (judge_room_full(new_rno)){//判断转移的房间是否满人
                    System.out.println("该房间已经满人！");
                }
                else {//房间有空位置
                    //获得新床位的信息
                    System.out.println("请输入该学生要入住的床位号：");
                    new_bedno = in.next();
                    sql = "SELECT sno FROM stay_room WHERE rno=? AND bedno=? AND out_room=0";
                    rs = executeQuery(sql,new Object[]{new_rno,new_bedno});
                    if (rs.next()){//该位置有人在
                        replace_sno = rs.getString(1);
                        System.out.println("该位置已经有人了！");
                        System.out.println("是否继续更改1.继续2.停止");
                        int num;
                        num = in.nextInt();
                        if(num==1){//更新两人入住信息
                            //更新该学生的信息
                            sql = "UPDATE stay_room SET rno=? ,bedno=? WHERE sno=?";
                            i = executeUpdate(sql,new Object[]{new_rno,new_bedno,sno});
                            System.out.print("该学生");
                            get_result(i);
                            //更新被换位置的学生信息
                            sql = "UPDATE stay_room SET rno=? ,bedno=? WHERE sno=?";
                            i = executeUpdate(sql,new Object[]{rno,bedno,replace_sno});
                            System.out.print("被换位置学生");
                            get_result(i);
                        }
                    }
                    else {//该位置没人在，可以直接更改
                        sql = "INSERT INTO replace_room VALUES(?,?,?,?)";
                        i = executeUpdate(sql,new Object[]{sno,new_rno,new_bedno,change_time});
                        System.out.print("更换房间表");
                        get_result(i);
                        sql = "UPDATE stay_room SET rno=? ,bedno=? WHERE sno=?";
                        i = executeUpdate(sql,new Object[]{new_rno,new_bedno,sno});
                        System.out.print("入住表");
                        get_result(i);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close_rs(rs);
        }
    }
}
