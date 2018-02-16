package com.example.sergey.myapplication.DataBase;

/**
 * Created by sergey on 07.01.2018.
 */

public class DBCard {
    public String title;
    public double percents;
    public int sum;
    public String srok;
    public String bank;
    public int absoluteID;

    public DBCard(String title, double percents, int sum, String srok, String bank) {
        this.title = title;
        this.percents = percents;
        this.sum = sum;
        this.srok = srok;
        this.bank = bank;
    }
}
