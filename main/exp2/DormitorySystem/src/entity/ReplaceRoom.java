package entity;

//调换房间
public class ReplaceRoom {
    private String sno;
    private String newRno;
    private String newBedno;
    private String changeTime;

    public ReplaceRoom() {
    }

    public ReplaceRoom(String sno, String newRno, String newBedno, String changeTime) {
        this.sno = sno;
        this.newRno = newRno;
        this.newBedno = newBedno;
        this.changeTime = changeTime;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getnewRno() {
        return newRno;
    }

    public void setnewRno(String newRno) {
        this.newRno = newRno;
    }

    public String getnewBedno() {
        return newBedno;
    }

    public void setnewBedno(String newBedno) {
        this.newBedno = newBedno;
    }

    public String getchangeTime() {
        return changeTime;
    }

    public void setchangeTime(String changeTime) {
        this.changeTime = changeTime;
    }
}
