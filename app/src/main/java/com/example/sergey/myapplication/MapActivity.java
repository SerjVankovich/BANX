package com.example.sergey.myapplication;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sergey.myapplication.DataBase.BankCard;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {
    private DatabaseReference ref;
    private List<MyLatLng> main_array;

    private MapFragment fragment;
    private GoogleMap map;
    private String bankName;
    private String fullBankName;
    private TextView toolbarName;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        bankName = getIntent().getStringExtra("bankName");
        fullBankName = toFull(bankName);
        toolbarName = findViewById(R.id.bankName);
        toolbarName.setText("Офисы банка " + "\"" + fullBankName + "\"");
        fragment = (MapFragment) getFragmentManager().findFragmentById(R.id.mapView);

        ref = FirebaseDatabase.getInstance().getReference().child("all_coordinates");
        ref.child(bankName).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GenericTypeIndicator<List<MyLatLng>> t = new GenericTypeIndicator<List<MyLatLng>>() {
                };
                main_array = dataSnapshot.getValue(t);
                Log.d("MapLOG", MapActivity.this.toString());
                fragment.getMapAsync(MapActivity.this);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        final LatLng VLADIVOSTOK = new LatLng(43.1056200, 131.8735300);
            for (MyLatLng latLng :
                    main_array) {
                map.addMarker(new MarkerOptions()
                        .position(new LatLng(latLng.getLat(), latLng.getLng()))
                        .title(fullBankName + ", " + latLng.getAdress())
                );
            }
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(VLADIVOSTOK, 12));
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        map.setMyLocationEnabled(true);




    }
    public String toFull(String bank) {
        String bankName = "";
        switch (bank) {
            case "alfabank":
                bankName = "Альфабанк";
                break;
            case "atb":
                bankName = "АТБ";
                break;
            case "baikalinvestbank":
                bankName = "Байкал Инвест Банк";
                break;
            case "bbrbank":
                bankName = "ББР Банк";
                break;
            case "binbank":
                bankName = "Бинбанк";
                break;
            case "dalnevostochny":
                bankName = "Дальневосточный";
                break;
            case "gazprombank":
                bankName = "Газпромбанк";
                break;
            case "homecreditbank":
                bankName = "Банк Хоум Кредит";
                break;
            case "mosoblbank":
                bankName = "Мособлбанк";
                break;
            case "mts-bank":
                bankName = "МТС-Банк";
                break;
            case "otkritie":
                bankName = "ФК Открытие";
                break;
            case "pochtabank":
                bankName = "Почтабанк";
                break;
            case "primorye":
                bankName = "Банк Приморье";
                break;
            case "primsotsbank":
                bankName = "Примсоцбанк";
                break;
            case "promsvyazbank":
                bankName = "Промсвязьбанк";
                break;
            case "ptkb":
                bankName = "Примтеркомбанк";
                break;
            case "rgsbank":
                bankName = "Росгосстрах Банк";
                break;
            case "rosbank":
                bankName = "Росбанк";
                break;
            case "roscap":
                bankName = "Банк Россиийский Капитал";
                break;
            case "rsb":
                bankName = "Банк Русский Стандарт";
                break;
            case "rshb":
                bankName = "Россельхозбанк";
                break;
            case "rusfinancebank":
                bankName = "Русфинанс Банк";
                break;
            case "sberbank":
                bankName = "Сбербанк";
                break;
            case "skb-bank":
                bankName = "СКБ-Банк";
                break;
            case "sovcombank":
                bankName = "Совкомбанк";
                break;
            case "sviaz-bank":
                bankName = "Связьбанк";
                break;
            case "tinkoff":
                bankName = "Тинькофф";
                break;
            case "ussury":
                bankName = "Банк Уссури";
                break;
            case "v-express-bank":
                bankName = "Восточный экспресс банк";
                break;
            case "vtb":
                bankName = "Банк ВТБ";
                break;
        }
        return bankName;
    }
}
