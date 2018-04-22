package com.example.sergey.myapplication.DataBase;

/**
 * Created by sergey on 25.02.2018.
 */

public class BankCard {
    public String bank;
    public String  len_cred;
    public String len_vklads;

    public BankCard() {
    }

    public BankCard(String name, String len_cred, String len_vklads) {
        this.bank = name;
        this.len_cred = len_cred;
        this.len_vklads = len_vklads;
    }
}
