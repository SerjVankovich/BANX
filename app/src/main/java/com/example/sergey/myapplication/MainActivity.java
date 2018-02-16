package com.example.sergey.myapplication;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Rect;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sergey.myapplication.DataBase.DataBaseHelper;

import com.example.sergey.myapplication.fragments.FragmentCredits;
import com.example.sergey.myapplication.fragments.FragmentVklads;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.yarolegovich.slidingrootnav.SlideGravity;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener{

    Toolbar toolbar;
    TextView toolbar_text;
    String tb_text;

    DataBaseHelper dbHelper;
    FragmentVklads fvklads;
    FragmentCredits fragmentCredits;

    NavigationView navigationView;
    SlidingRootNav slidingBuilder;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentCredits = new FragmentCredits();
        fvklads = new FragmentVklads();
        @SuppressLint("ResourceType") LinearLayout menuLayout = findViewById(R.layout.activity);





        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        toolbar_text = findViewById(R.id.toolbar_text);


        dbHelper = new DataBaseHelper(this);

        slidingBuilder = new SlidingRootNavBuilder(this)
                .withMenuLayout(R.layout.activity)
                .withToolbarMenuToggle(toolbar)
                .inject();

        navigationView = findViewById(R.id.menu);
        navigationView.setNavigationItemSelectedListener(this);

    }



    @Override
    public void onBackPressed() {
        if (fvklads.isResumed()){
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction().remove(fvklads);
            fragmentTransaction.commit();
            fvklads.onDetach();
            slidingBuilder.closeMenu();
            toolbar_text.setText("BANX");
            navigationView.refreshDrawableState();
        } else if (fragmentCredits.isResumed()){
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction().remove(fragmentCredits);
            fragmentTransaction.commit();
            fragmentCredits.onDetach();
            slidingBuilder.closeMenu();
            toolbar_text.setText("BANX");
            navigationView.refreshDrawableState();
        } else {
            super.onBackPressed();
        }


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        SQLiteDatabase database = dbHelper.getWritableDatabase();
        int id = item.getItemId();

        if (id == R.id.vklads){
            toolbar_text.setText("DEPOSITS");


            FragmentTransaction ftrans = getSupportFragmentManager().beginTransaction().replace(R.id.container, fvklads);
            ftrans.commit();
        } else if (id == R.id.kredits) {
            toolbar_text.setText("CREDITS");


            FragmentTransaction ftrans = getSupportFragmentManager().beginTransaction().replace(R.id.container, fragmentCredits);
            ftrans.commit();
        }
        slidingBuilder.closeMenu();

        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(fvklads.isAdded()) {
            fvklads.onSaveInstanceState(outState);
        } else if (fragmentCredits.isAdded()){
            fragmentCredits.onSaveInstanceState(outState);
        }


    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (fvklads.isAdded()){
            fvklads.onViewStateRestored(savedInstanceState);
            this.onAttachFragment(fvklads);
        } else if (fragmentCredits.isAdded()) {
            fragmentCredits.onViewStateRestored(savedInstanceState);
            this.onAttachFragment(fragmentCredits);
        }
        super.onRestoreInstanceState(savedInstanceState);


    }
}
