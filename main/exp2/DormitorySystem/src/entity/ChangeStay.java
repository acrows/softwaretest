package entity;

//住宿调整
public class ChangeStay {
    private String sno;
    private int days;
    private double needMoney;
    private double paidMoney;
    private double diffMoney;
    private int extend;//延长(1)或提前(0)住宿

    public ChangeStay() {
    }

    public ChangeStay(String sno, int days, double needMoney, double paidMoney, double diffMoney, int extend) {
        this.sno = sno;
        this.days = days;
        this.needMoney = needMoney;
        this.paidMoney = paidMoney;
        this.diffMoney = diffMoney;
        this.extend = extend;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
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

    public int getExtend() {
        return extend;
    }

    public void setExtend(int extend) {
        this.extend = extend;
    }
}
