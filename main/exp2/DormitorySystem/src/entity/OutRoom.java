package entity;

//退宿
public class OutRoom {
    private String sno;
    private String outTime;
    private String reason;
    private double needMoney;
    private double paidMoney;
    private double diffMoney;

    public OutRoom() {
    }

    public OutRoom(String sno, String outTime, String reason, double needMoney, double paidMoney, double diffMoney) {
        this.sno = sno;
        this.outTime = outTime;
        this.reason = reason;
        this.needMoney = needMoney;
        this.paidMoney = paidMoney;
        this.diffMoney = diffMoney;
    }

    public String getSno() {
        return sno;
    }

    public String getoutTime() {
        return outTime;
    }

    public String getReason() {
        return reason;
    }

    public double getneedMoney() {
        return needMoney;
    }

    public double getpaidMoney() {
        return paidMoney;
    }

    public double getdiffMoney() {
        return diffMoney;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public void setoutTime(String outTime) {
        this.outTime = outTime;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setneedMoney(double needMoney) {
        this.needMoney = needMoney;
    }

    public void setpaidMoney(double paidMoney) {
        this.paidMoney = paidMoney;
    }

    public void setdiffMoney(double diffMoney) {
        this.diffMoney = diffMoney;
    }
}
