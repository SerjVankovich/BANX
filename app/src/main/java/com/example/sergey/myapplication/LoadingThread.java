package com.example.sergey.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.cooltechworks.views.shimmer.ShimmerRecyclerView;
import com.example.sergey.myapplication.DataBase.DBCard;
import com.example.sergey.myapplication.DataBase.DataBaseHelper;
import com.example.sergey.myapplication.adapters.MyScrollListener;
import com.example.sergey.myapplication.adapters.ResAdapter;
import com.example.sergey.myapplication.comparaters.PercentVkladsCompatrator;
import com.example.sergey.myapplication.comparaters.PercentsCreditsComparator;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Created by sergey on 12.03.2018.
 */

public class LoadingThread extends AsyncTask<Void, Void, Void> {
    List<DBCard> main_array;
    PercentVkladsCompatrator compatrator;
    PercentsCreditsComparator percentsCreditsComparator;
    String whatComp;
    Context context;


    public LoadingThread(List<DBCard> main_array, PercentVkladsCompatrator compatrator, PercentsCreditsComparator percentsCreditsComparator, String whatComp, Context context) {
        this.main_array = main_array;
        this.compatrator = compatrator;
        this.percentsCreditsComparator = percentsCreditsComparator;
        this.whatComp = whatComp;
        this.context = context;

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected Void doInBackground(Void... voids) {
            getMain_array();


        return null;
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)


    public void getMain_array (){
        DataBaseHelper helper = new DataBaseHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();

        db.delete(DataBaseHelper.TABLE_CACHE, null, null);
        for (DBCard card:
             main_array) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(DataBaseHelper.KEY_TITLE, card.title);
            contentValues.put(DataBaseHelper.KEY_PERCENTS, card.perinrub);
            contentValues.put(DataBaseHelper.KEY_SUM_IN_RUB, card.suminrub);
            contentValues.put(DataBaseHelper.KEY_SROK_IN_RUB, card.srokinrub);
            contentValues.put(DataBaseHelper.KEY_BANK, card.bank);
            contentValues.put(DataBaseHelper.KEY_LINK, card.link);

            db.insert(DataBaseHelper.TABLE_CACHE, null,  contentValues);
        }
        main_array = new ArrayList<>();
        Cursor cursor = db.query(DataBaseHelper.TABLE_CACHE, null, null, null, null, null, DataBaseHelper.KEY_PERCENTS);
        if (cursor.moveToFirst()){
            int idInd = cursor.getColumnIndex(DataBaseHelper.KEY_ID);
            int titleInd = cursor.getColumnIndex(DataBaseHelper.KEY_TITLE);
            int perInd = cursor.getColumnIndex(DataBaseHelper.KEY_PERCENTS);
            int sumInd = cursor.getColumnIndex(DataBaseHelper.KEY_SUM_IN_RUB);
            int srokInd = cursor.getColumnIndex(DataBaseHelper.KEY_SROK_IN_RUB);
            int bankInd = cursor.getColumnIndex(DataBaseHelper.KEY_BANK);
            int linkInd = cursor.getColumnIndex(DataBaseHelper.KEY_LINK);
            while(cursor.moveToNext()){
                DBCard card = new DBCard(cursor.getString(titleInd), cursor.getDouble(perInd), cursor.getInt(sumInd), cursor.getInt(srokInd), cursor.getString(bankInd), cursor.getString(linkInd));
                main_array.add(card);
            }
        }
        cursor.close();
        db.close();







 /*       myBase.child(child).child(ChildChild).addListenerForSingleValueEvent(new ValueEventListener() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GenericTypeIndicator<List<DBCard>> t = new GenericTypeIndicator<List<DBCard>>(){};
                main_array = dataSnapshot.getValue(t);
                if (Objects.equals(whatComp, "vklads")){
                    compatrator = new PercentVkladsCompatrator();
                    Collections.sort(main_array, compatrator);
                } else if (Objects.equals(whatComp, "kredits")) {
                    percentsCreditsComparator = new PercentsCreditsComparator();
                    Collections.sort(main_array, percentsCreditsComparator);
                }
                recyclerView.setAdapter(new ResAdapter(context, main_array, DataBaseHelper.TABLE_VKLADS));

            //    updateUI();

 /*               GlobalFunctions.getData(main_array, dataSnapshot, recyclerView);
                DataBaseHelper helper = new DataBaseHelper(context);
                SQLiteDatabase db = helper.getWritableDatabase();
                try{
                    db.delete(DataBaseHelper.TABLE_CACHE, null, null);
                } catch (SQLiteException e){};

                for (DBCard card:
                        main_array) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(DataBaseHelper.KEY_TITLE, card.title);
                    contentValues.put(DataBaseHelper.KEY_BANK, card.bank);
                    contentValues.put(DataBaseHelper.KEY_SROK_IN_RUB, card.srok);
                    contentValues.put(DataBaseHelper.KEY_SUM_IN_RUB, card.sum);
                    contentValues.put(DataBaseHelper.KEY_PERCENTS, card.percents);
                    contentValues.put(DataBaseHelper.KEY_LINK, card.link);


                    db.insert(DataBaseHelper.TABLE_CACHE, null, contentValues);

                }
                updateUI();



            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("MyLog", "Failed to read value.", error.toException());
            }
        });*/


    }
 //   @RequiresApi(api = Build.VERSION_CODES.KITKAT)
/*    public void updateUI(){
        List<DBCard> list = new ArrayList<>();
        DataBaseHelper helper = new DataBaseHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.query(DataBaseHelper.TABLE_CACHE, null, null, null, null, null,null);
        if (cursor.moveToFirst()){
            int titleIndex = cursor.getColumnIndex(DataBaseHelper.KEY_TITLE);
            int idIndex = cursor.getColumnIndex(DataBaseHelper.KEY_ID);
            int sumIndex = cursor.getColumnIndex(DataBaseHelper.KEY_SUM_IN_RUB);
            int srokIndex = cursor.getColumnIndex(DataBaseHelper.KEY_SROK_IN_RUB);
            int bankID = cursor.getColumnIndex(DataBaseHelper.KEY_BANK);
            int linkIndex = cursor.getColumnIndex(DataBaseHelper.KEY_LINK);
            int percentsIndex = cursor.getColumnIndex(DataBaseHelper.KEY_PERCENTS);
            while(cursor.moveToNext()){
                DBCard card = new DBCard(cursor.getString(titleIndex),
                                        cursor.getDouble(percentsIndex),
                                        cursor.getInt(sumIndex),
                                        cursor.getInt(srokIndex),
                                        cursor.getString(bankID),
                                        cursor.getString(linkIndex));
                list.add(card);
            }
        }
        cursor.close();
        db.close();
        if (Objects.equals(whatComp, "vklads")){
            compatrator = new PercentVkladsCompatrator();
            Collections.sort(main_array, compatrator);
        } else if (Objects.equals(whatComp, "kredits")) {
            percentsCreditsComparator = new PercentsCreditsComparator();
            Collections.sort(main_array, percentsCreditsComparator);
        }
        List<DBCard> showArray = new ArrayList<>();

        adapter = new ResAdapter(context, main_array, DataBaseHelper.TABLE_CREDITS);
        GlobalFunctions.loadMore(main_array, showArray, adapter, 0, 15);

        recyclerView.setAdapter(adapter);
    //    recyclerView.addOnScrollListener(new MyScrollListener(recyclerView, main_array, adapter, showArray));
        recyclerView.hideShimmerAdapter();
    } */
}
