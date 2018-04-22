package com.example.sergey.myapplication.DataBase;

/**
 * Created by sergey on 28.03.2018.
 */

public class Cotirovka {
   private String name;
   private String charCode;
   private double value;
   private double privious;
   private int nominal;

    public Cotirovka(String name, String charCode, double value, double privious, int nominal) {
        this.name = name;
        this.charCode = charCode;
        this.value = value;
        this.privious = privious;
        this.nominal = nominal;
    }

    public Cotirovka() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharCode() {
        return charCode;
    }

    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getPrivious() {
        return privious;
    }

    public void setPrivious(double privious) {
        this.privious = privious;
    }

    public int getNominal() {
        return nominal;
    }

    public void setNominal(int nominal) {
        this.nominal = nominal;
    }
}
