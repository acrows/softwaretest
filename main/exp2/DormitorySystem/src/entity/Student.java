package entity;

public class Student{
    private String sno;
    //学号
    private String ssex;
    //性别
    private String sname;
    //姓名
    private String phone;
    //电话

    public Student() {
    }

    public Student(String sno, String ssex, String sname, String phone) {
        this.sno = sno;
        this.ssex = ssex;
        this.sname = sname;
        this.phone = phone;
    }

    public String getSsex() {
        return ssex;
    }

    public void setSsex(String ssex) {
        this.ssex = ssex;
    }

    public String getSno() {
        return sno;
    }

    public String getSname() {
        return sname;
    }

    public String getPhone() {
        return phone;
    }


    public void setSno(String sno) {
        this.sno = sno;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
