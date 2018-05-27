package com.example.sergey.myapplication.DataBase;

/**
 * Created by sergey on 07.01.2018.
 */

public class DBCard {
    private String title;
    private double perinrub;
    private int suminrub;
    private int srokinrub;
    private String bank;
    private int absoluteID;
    private String link;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPerinrub() {
        return perinrub;
    }

    public void setPerinrub(double perinrub) {
        this.perinrub = perinrub;
    }

    public int getSuminrub() {
        return suminrub;
    }

    public void setSuminrub(int suminrub) {
        this.suminrub = suminrub;
    }

    public int getSrokinrub() {
        return srokinrub;
    }

    public void setSrokinrub(int srokinrub) {
        this.srokinrub = srokinrub;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public int getAbsoluteID() {
        return absoluteID;
    }

    public void setAbsoluteID(int absoluteID) {
        this.absoluteID = absoluteID;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
