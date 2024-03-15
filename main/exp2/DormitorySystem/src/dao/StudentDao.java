package dao;

import entity.Student;

import java.util.Scanner;
import java.util.Set;

public class StudentDao extends BaseDao{
    //添加学生
    Scanner in = new Scanner(System.in);
    public void addsutdent(){
        String sql="INSERT INTO student VALUES(?,?,?,?)";
        String sno=null;
        String sname;
        String ssex;
        String phone;
        boolean exist = true;
        while (exist){
            System.out.println("请输入学号：");
            sno = in.next();
            if (find_student(sno)){
                System.out.println("该学号已存在！请重新输入！");
            } else {
				exist = false;
			}
        }
        System.out.println("请输入姓名：");
        sname = in.next();
        ssex = judge_sex();
        System.out.println("请输入电话：");
        phone = in.next();
        Student s= new Student(sno,ssex,sname,phone);
        int i = executeUpdate(sql,new Object[]{s.getSno(),s.getSname(),s.getPhone(),s.getSsex()});
        get_result(i);
    }
}
