package entity;


public class StayRoom {
    private String sno;
    private String rno;
    private String bedno;
    private String intime;
    private int days;
    private String outtime;
    private String remark;
    private int outRoom;

    public StayRoom() {
    }

    public StayRoom(String sno, String rno, String bedno, String intime, int days, String outtime, String remark, int outRoom) {
        this.sno = sno;
        this.rno = rno;
        this.bedno = bedno;
        this.intime = intime;
        this.days = days;
        this.outtime = outtime;
        this.remark = remark;
        this.out_room = out_room;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getRno() {
        return rno;
    }

    public void setRno(String rno) {
        this.rno = rno;
    }

    public String getBedno() {
        return bedno;
    }

    public void setBedno(String bedno) {
        this.bedno = bedno;
    }

    public String getIntime() {
        return intime;
    }

    public void setIntime(String intime) {
        this.intime = intime;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public String getOuttime() {
        return outtime;
    }

    public void setOuttime(String outtime) {
        this.outtime = outtime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getOutRoom() {
        return outRoom;
    }

    public void setOutRoom(int outRoom) {
        this.outRoom = outRoom;
    }
}
