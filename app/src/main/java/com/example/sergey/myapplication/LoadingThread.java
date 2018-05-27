package com.example.sergey.myapplication;


import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentTransaction;


/**
 * Created by sergey on 12.03.2018.
 */

public class LoadingThread extends AsyncTask<Void, Void, Void> {
    private FragmentTransaction transaction;


    public LoadingThread(FragmentTransaction transaction) {
        this.transaction = transaction;

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected Void doInBackground(Void... voids) {
        transaction.commit();
        return null;
    }
}
