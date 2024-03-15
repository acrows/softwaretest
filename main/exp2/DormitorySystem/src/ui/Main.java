package ui;

import dao.*;


public class Main {
    public static void main(String[] args) {
        SelectDao sd = new SelectDao() ;
        sd.select_room();
    }
}
