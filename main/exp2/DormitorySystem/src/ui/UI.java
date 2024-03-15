package ui;

import dao.UIDAO;

import java.util.Scanner;

public class UI {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int sum=0,option,o2;
        UIDAO uidao = new UIDAO();
        boolean exit=false,e2=false;
        while (sum<3){
            if(uidao.log_in()){
                //密码正确
                while (!exit){
                    System.out.println("请输入1.录入学生\t2.房间管理\t3.住宿管理\t4.费用管理\t5.信息查询\t6.数据统计信息\t7.退出");
                    option = in.nextInt();
                    switch (option){
                        case 1:
                            uidao.add_student();
                            break;
                        case 2:
                            uidao.room();
                            break;
                        case 3:
                            uidao.stay();
                            break;
                        case 4:
                            uidao.pay();
                            break;
                        case 5:
                            while (!e2){
                                System.out.println("请选择1.基本信息查询\t2.记录查询\t3.退出");
                                o2 = in.nextInt();
                                switch (o2){
                                    case 1:
                                        uidao.basic_check();
                                        break;
                                    case 2:
                                        uidao.Record_check();
                                        break;
                                    case 3:
                                        e2 = true;
                                        break;
                                    default:
                                }
                            }
                            break;
                        case 6:
                            uidao.check_sum();
                            break;
                        case 7:
                            System.exit(1);
                            break;
                        default:
                    }
                }
            }
            else {
                System.out.println("密码错误！还有"+(2-sum)+"次机会");
                sum++;
            }
        }
    }
}
