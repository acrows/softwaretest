package entity;

//缴费信息
public class Pay {
    private String sno;
    private double payMoney;
    private String padTime;
    private double needMoney;
    private double paidMoney;
    private double diffMoney;

    public Pay() {
    }

    public Pay(String sno, double payMoney, String padTime, double needMoney, double paidMoney, double diffMoney) {
        this.sno = sno;
        this.payMoney = payMoney;
        this.padTime = padTime;
        this.needMoney = needMoney;
        this.paidMoney = paidMoney;
        this.diffMoney = diffMoney;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public double getpayMoney() {
        return payMoney;
    }

    public void setpayMoney(double payMoney) {
        this.payMoney = payMoney;
    }

    public String getpadTime() {
        return padTime;
    }

    public void setpadTime(String padTime) {
        this.padTime = padTime;
    }

    public double getneedMoney() {
        return needMoney;
    }

    public void setneedMoney(double needMoney) {
        this.needMoney = needMoney;
    }

    public double getpaidMoney() {
        return paidMoney;
    }

    public void setpaidMoney(double paidMoney) {
        this.paidMoney = paidMoney;
    }

    public double getdiffMoney() {
        return diffMoney;
    }

    public void setdiffMoney(double diffMoney) {
        this.diffMoney = diffMoney;
    }
}
