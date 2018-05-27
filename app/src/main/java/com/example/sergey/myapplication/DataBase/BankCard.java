package com.example.sergey.myapplication.DataBase;

/**
 * Created by sergey on 25.02.2018.
 */

public class BankCard {
    private String bank;
    private String  len_cred;
    private String len_vklads;

    public BankCard() {
    }

    public BankCard(String name, String len_cred, String len_vklads) {
        this.bank = name;
        this.len_cred = len_cred;
        this.len_vklads = len_vklads;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getLen_cred() {
        return len_cred;
    }

    public void setLen_cred(String len_cred) {
        this.len_cred = len_cred;
    }

    public String getLen_vklads() {
        return len_vklads;
    }

    public void setLen_vklads(String len_vklads) {
        this.len_vklads = len_vklads;
    }
}
