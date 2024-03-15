package entity;

public class Room {
    private String rno;//房间号
    private String rsex;//房间住宿性别
    private int sum;//房间住宿人数

    public Room() {
    }

    public Room(String rno, String rsex, int sum) {
        this.rno = rno;
        this.rsex = rsex;
        this.sum = sum;
    }

    public String getRno() {
        return rno;
    }

    public String getRsex() {
        return rsex;
    }

    public int getSum() {
        return sum;
    }
}
