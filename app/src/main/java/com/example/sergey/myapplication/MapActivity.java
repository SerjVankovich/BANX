package com.example.sergey.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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

import java.util.List;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {
    private DatabaseReference ref;
    private List<MyLatLng> main_array;

    private MapFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        ref = FirebaseDatabase.getInstance().getReference().child("all_coordinates");
        ref.child(getIntent().getStringExtra("bankName")).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GenericTypeIndicator<List<MyLatLng>> t = new GenericTypeIndicator<List<MyLatLng>>(){};
                main_array = dataSnapshot.getValue(t);
                fragment = (MapFragment) getFragmentManager().findFragmentById(R.id.mapView);
                fragment.getMapAsync(MapActivity.this);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        final LatLng VLADIVOSTOK = new LatLng(43.1056200, 131.8735300);

        for (MyLatLng latLng:
             main_array) {
            googleMap.addMarker(new MarkerOptions()
            .position(new LatLng(latLng.lat, latLng.lng))
                    .title(latLng.adress)
            );
        }
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(VLADIVOSTOK, 10));


    }
}
