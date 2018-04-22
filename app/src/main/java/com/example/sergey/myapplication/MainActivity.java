package com.example.sergey.myapplication;


import android.annotation.SuppressLint;

import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sergey.myapplication.DataBase.DBCard;
import com.example.sergey.myapplication.DataBase.DataBaseHelper;

import com.example.sergey.myapplication.adapters.ResAdapter;
import com.example.sergey.myapplication.adapters.ResDialogAdapter;
import com.example.sergey.myapplication.fragments.BanxFragment;
import com.example.sergey.myapplication.fragments.CotirFragment;
import com.example.sergey.myapplication.fragments.FragmentCredits;
import com.example.sergey.myapplication.fragments.FragmentVklads;
import com.example.sergey.myapplication.fragments.Getter;
import com.example.sergey.myapplication.fragments.MyCredits;
import com.example.sergey.myapplication.fragments.MyVklads;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener{

    Toolbar toolbar;
    TextView toolbar_text;
    Button filter;

    DataBaseHelper dbHelper;
    FragmentVklads fvklads;
    FragmentCredits fragmentCredits;
    MyCredits credits;
    MyVklads vklads;
    BanxFragment banxFragment;
    CotirFragment cotirFragment;

    NavigationView navigationView;
    SlidingRootNav slidingBuilder;
    private AdView mAdView;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentCredits = new FragmentCredits();
        fvklads = new FragmentVklads();
        credits = new MyCredits();
        banxFragment = new BanxFragment();
        vklads = new MyVklads();
        cotirFragment = new CotirFragment();
        @SuppressLint("ResourceType") LinearLayout menuLayout = findViewById(R.layout.activity);


        filter = findViewById(R.id.filter);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        toolbar_text = findViewById(R.id.toolbar_text);


        dbHelper = new DataBaseHelper(this);

        slidingBuilder = new SlidingRootNavBuilder(this)
                .withMenuLayout(R.layout.activity)
                .withToolbarMenuToggle(toolbar)
                .withSavedState(savedInstanceState)
                .inject();

        navigationView = findViewById(R.id.menu);
        navigationView.setNavigationItemSelectedListener(this);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        banxFragment.setTransaction(getSupportFragmentManager().beginTransaction());
        fragmentTransaction.replace(R.id.container, banxFragment);
        fragmentTransaction.commit();
        navigationView.setCheckedItem(R.id.banks);

    }



    @Override
    public void onBackPressed() {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            banxFragment.setTransaction(transaction);
            toolbar_text.setText("BANX");
            super.onBackPressed();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        FragmentTransaction ftrans = getSupportFragmentManager().beginTransaction();
        ftrans.setCustomAnimations(R.animator.fragment_left_anim, R.animator.fragment_right_anim);

        if (id == R.id.vklads){
            toolbar_text.setText("ВКЛАДЫ");

            filter.setVisibility(View.VISIBLE);
            filter.setEnabled(true);
            filter.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public void onClick(View v) {
                    buildDialog(fvklads);
                }
            });


            ftrans.replace(R.id.container, fvklads);

        } else if (id == R.id.kredits) {
            toolbar_text.setText("КРЕДИТЫ");
            filter.setVisibility(View.VISIBLE);
            filter.setEnabled(true);
            filter.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public void onClick(View v) {
                    buildDialog(fragmentCredits);
                }
            });
            ftrans.replace(R.id.container, fragmentCredits);

        } else if (id == R.id.my_kredit){
            filter.setVisibility(View.INVISIBLE);
            filter.setEnabled(false);
            ftrans.replace(R.id.container, credits);

            toolbar_text.setText("МОИ КРЕДИТЫ");
        } else if (id == R.id.banks){
            filter.setVisibility(View.INVISIBLE);
            filter.setEnabled(false);
            banxFragment.setTransaction(getSupportFragmentManager().beginTransaction());
            ftrans.replace(R.id.container, banxFragment);

            toolbar_text.setText("BANX");
        } else if (id == R.id.my_deposits){
            filter.setVisibility(View.INVISIBLE);
            filter.setEnabled(false);
            ftrans.replace(R.id.container, vklads);
            toolbar_text.setText("МОИ ВКЛАДЫ");
        } else if (id == R.id.valutes) {
            ftrans.replace(R.id.container, cotirFragment);
            filter.setVisibility(View.INVISIBLE);
            filter.setEnabled(false);
            toolbar_text.setText("КОТИРОВКИ");
        }
        new LoadingThread(ftrans).execute();
        slidingBuilder.closeMenu();

        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public List<DBCard> filterByPercents(List<DBCard> list, String fromPercent, String toPercent) {
        if (!Objects.equals(fromPercent, "") && !Objects.equals(toPercent, "")){
            List<DBCard> resultList = new ArrayList<>();
            for (DBCard card:
                    list) {
                if (card.perinrub >= Double.parseDouble(fromPercent) && card.perinrub <= Double.parseDouble(toPercent)) {
                    resultList.add(card);
                }
            }
            return resultList;
        } else {
            return list;
        }


    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static List<DBCard> filterByBank(List<DBCard> list, List<String> banks){
        if (!banks.isEmpty()) {
            List<DBCard> resultList = new ArrayList<>();
            for (DBCard card:
                    list) {
                if (banks.contains(card.bank)) {
                    resultList.add(card);
                }
            }
            return resultList;
        } else {
            return list;
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public List<DBCard> filterBySum (List<DBCard> list, String fromSum, String toSum) {
        if (!Objects.equals(fromSum, "") && !Objects.equals(toSum, "")){
            List<DBCard> resultList = new ArrayList<>();
            for (DBCard card:
                    list) {
                if (card.suminrub >= Double.parseDouble(fromSum) && card.suminrub <= Double.parseDouble(toSum)) {
                    resultList.add(card);
                }
            }
            return resultList;
        } else {
            return list;
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public List<DBCard> filterBySrok (List<DBCard> list, String fromSrok, String toSrok) {
        if (!Objects.equals(fromSrok, "") && !Objects.equals(toSrok, "")){
            List<DBCard> resultList = new ArrayList<>();
            for (DBCard card:
                    list) {
                if (card.srokinrub >= Double.parseDouble(fromSrok) && card.srokinrub <= Double.parseDouble(toSrok)) {
                    resultList.add(card);
                }
            }
            return resultList;
        } else {
            return list;
        }
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



    public String translateIntoUnderstand( String String) {
        String bank = "";
        switch (String) {
            case "Альфабанк":
                bank = "alfabank";
                break;
            case "АТБ":
                bank = "atb";
                break;
            case "Байкал Инвест Банк":
                bank = "baikalinvestbank";
                break;
            case "ББР Банк":
                bank = "bbrbank";
                break;
            case "Бинбанк":
                bank = "binbank";
                break;
            case "Дальневосточный":
                bank = "dalnevostochny";
                break;
            case "Газпромбанк":
                bank = "gazprombank";
                break;
            case "Банк Хоум Кредит":
                bank = "homecreditbank";
                break;
            case "Мособлбанк":
                bank = "mosoblbank";
                break;
            case "МТС-Банк":
                bank = "mts-bank";
                break;
            case "ФК Открытие":
                bank = "otkritie";
                break;
            case "Почтабанк":
                bank = "pochtabank";
                break;
            case "Банк Приморье":
                bank = "primorye";
                break;
            case "Примсоцбанк":
                bank = "primsotsbank";
                break;
            case "Промсвязьбанк":
                bank = "promsvyazbank";
                break;
            case "Примтеркомбанк":
                bank = "ptkb";
                break;
            case "Росгосстрах Банк":
                bank = "rgsbank";
                break;
            case "Росбанк":
                bank = "rosbank";
                break;
            case "Банк Россиийский Капитал":
                bank = "roscap";
                break;
            case "Банк Русский Стандарт":
                bank = "rsb";
                break;
            case "Россельхозбанк":
                bank = "rshb";
                break;
            case "Русфинанс Банк":
                bank = "rusfinancebank";
                break;
            case "Сбербанк":
                bank = "sberbank";
                break;
            case "СКБ-Банк":
                bank = "skb-bank";
                break;
            case "Совкомбанк":
                bank = "sovcombank";
                break;
            case "Связьбанк":
                bank = "sviaz-bank";
                break;
            case "Тинькофф":
                bank = "tinkoff";
                break;
            case "Банк Уссури":
                bank = "ussury";
                break;
            case "Восточный экспресс банк":
                bank = "v-express-bank";
                break;
            case "Банк ВТБ":
                bank = "vtb";
                break;
        }
        return bank;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public List<DBCard> filterProc(EditText fromPer, EditText toPer, EditText fromSum, EditText toSum, EditText fromSrok, EditText toSrok, ResDialogAdapter adapter, Getter getter){
        String numFromPer = fromPer.getText().toString();
        String numToPer = toPer.getText().toString();
        String numFromSum = fromSum.getText().toString();
        String numToSum = toSum.getText().toString();
        String numFromSrok = fromSrok.getText().toString();
        String numToSrok = toSrok.getText().toString();
        List<CheckBox> boxes = adapter.getCheckBoxes();
        List<String> actualBanks = new ArrayList<>();
        for (int i = 0; i < boxes.size(); i++) {
            if (boxes.get(i).isChecked()) {
                actualBanks.add(translateIntoUnderstand(boxes.get(i).getText().toString()));
            }
        }
        List<DBCard> list = filterByBank(filterBySrok(filterBySum(filterByPercents(getter.getMainArray(), numFromPer, numToPer), numFromSum, numToSum), numFromSrok, numToSrok), actualBanks);

        return list;

    }
    public  void buildDialog(final Getter getter){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        View view = (CardView) getLayoutInflater().inflate(R.layout.filter_dialog, null);

        final EditText fromPer = view.findViewById(R.id.fromPer);
        final EditText toPer = view.findViewById(R.id.toPer);
        final EditText fromSum = view.findViewById(R.id.fromRub);
        final EditText toSum = view.findViewById(R.id.toRub);
        final EditText fromSrok = view.findViewById(R.id.fromDay);
        final EditText toSrok = view.findViewById(R.id.toDay);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        HashSet<String> set = new HashSet<>();
        for (DBCard card:
                getter.getMainArray()) {
            set.add(card.bank);
        }
        final ResDialogAdapter adapter = new ResDialogAdapter(set.toArray(new String[set.size()]));
        recyclerView.setAdapter(adapter);

        Button ok = view.findViewById(R.id.ok);
        Button cancel = view.findViewById(R.id.cancell);

        builder.setView(view);
        final AlertDialog dialog = builder.create();

        ok.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                List<DBCard> list = filterProc(fromPer, toPer, fromSum, toSum, fromSrok, toSrok, adapter, getter);
                if (getter.getClass().isInstance(FragmentVklads.class)) {
                    getter.getRecyclerView().setAdapter(new ResAdapter(MainActivity.this, list, DataBaseHelper.TABLE_VKLADS, "vklads"));
                } else {
                    getter.getRecyclerView().setAdapter(new ResAdapter(MainActivity.this, list, DataBaseHelper.TABLE_VKLADS, "credits"));
                }


                dialog.dismiss();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
    }
}
