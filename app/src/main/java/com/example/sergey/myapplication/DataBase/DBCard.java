package com.example.sergey.myapplication.DataBase;

/**
 * Created by sergey on 07.01.2018.
 */

public class DBCard {
    public String title;
    public double perinrub;
    public int suminrub;
    public int srokinrub;
    public String bank;
    public int absoluteID;
    public String link;

    public DBCard() {
    }

    public DBCard(String title, double percents, int sum, int srok, String bank, String link) {
        this.link = link;
        this.title = title;
        this.perinrub = percents;
        this.suminrub = sum;
        this.srokinrub = srok;
        this.bank = bank;
    }
}
