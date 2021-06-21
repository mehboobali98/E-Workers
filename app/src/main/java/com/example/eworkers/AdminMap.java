package com.example.eworkers;

import androidx.appcompat.app.AppCompatActivity;

import android.location.Address;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import Models.Mod;

public class AdminMap extends AppCompatActivity implements OnMapReadyCallback {
    GoogleMap map;
    SupportMapFragment mapFragment;
    Address address = null;
    OnMapReadyCallback thisFrag = this;
    public static Mod currentMod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_map);

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.google_map);

        mapFragment.getMapAsync(thisFrag);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        String data[] = currentMod.getSting().split("\\^");
        String location[] = data[1].split("!@#");
        double longitude = Double.valueOf(location[1]);
        double latitude = Double.valueOf(location[0]);

        LatLng latLng = new LatLng(latitude, longitude);

        map.addMarker(new MarkerOptions().position(latLng).title(location[2]));
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 20));

        map.getUiSettings().setAllGesturesEnabled(true);
        map.getUiSettings().setMyLocationButtonEnabled(true);
        map.getUiSettings().setCompassEnabled(true);
        map.getUiSettings().setMapToolbarEnabled(true);
        map.getUiSettings().setZoomControlsEnabled(true);
        map.getUiSettings().setIndoorLevelPickerEnabled(true);
    }
}
