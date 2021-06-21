package com.example.eworkers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import API.EWorkers;

public class MainCat extends AppCompatActivity implements LocationListener, FurnitureTransfer.OnFragmentInteractionListener,PestControl.OnFragmentInteractionListener, Beautician.OnFragmentInteractionListener,Refrigerator.OnFragmentInteractionListener,WashingMachine.OnFragmentInteractionListener,HairDresses.OnFragmentInteractionListener, Laundary.OnFragmentInteractionListener, Plumber1.OnFragmentInteractionListener, AcMaintainence.OnFragmentInteractionListener, Electrician1.OnFragmentInteractionListener {
    private void handleLocationTasks() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getLocation();
        }
        else{
            LatLng latLng = new LatLng(25, 51);
            Map.map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 8));
        }
    }

    private void getLocation() {
        Map.fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {

                Location location = task.getResult();
                if (location != null) {

                    try {
                        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                        Map.currLocation = addresses.get(0);


                        if (Map.currLocation == null) {
                            LatLng latLng = new LatLng(25, 51);
                            Map.map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 8));

                        } else {
                            LatLng latLng = new LatLng(Map.currLocation.getLatitude(), Map.currLocation.getLongitude());
                            Marker pos_Marker = Map.map.addMarker(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.fromResource(R.drawable.currentlocation)).title("Current Location").draggable(false));
                            //pos_Marker.showInfoWindow();

                            Map.address = Map.currLocation;
                            Map.map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));

                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    LatLng latLng = new LatLng(25, 51);
                    Map.map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 8));

                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            String[] permissions,
            int[] grantResults
    ){

        handleLocationTasks();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cat);

        ImageView back = (ImageView) findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainCat.this, Homescr.class);
                //Already finished splashscreen
                finish();
                startActivity(myIntent);
            }
        });

        if(EWorkers.screennum == 3) {
            Electrician1 firstFrag = new Electrician1();
            firstFrag.myContext = this;
            Button bt = (Button) findViewById(R.id.sb1);
            bt.setBackgroundResource(R.drawable.rounded_yellow);
            Button bt2 = (Button) findViewById(R.id.sb2);
            bt2.setBackgroundResource(R.drawable.notsel);
            Button bt3 = (Button) findViewById(R.id.sb3);
            bt3.setBackgroundResource(R.drawable.notsel);
            // add fragment to the fragment container layout

            getSupportFragmentManager().beginTransaction().add(R.id.fml, firstFrag).commit();
        }

        else if(EWorkers.screennum == 1) {
            Button bt = (Button) findViewById(R.id.sb1);
            bt.setBackgroundResource(R.drawable.rounded_yellow);
            Button bt2 = (Button) findViewById(R.id.sb2);
            bt2.setBackgroundResource(R.drawable.notsel);
            Button bt3 = (Button) findViewById(R.id.sb3);
            bt3.setBackgroundResource(R.drawable.notsel);

            AcMaintainence firstFrag = new AcMaintainence();
            firstFrag.myContext = this;
            // add fragment to the fragment container layout

            getSupportFragmentManager().beginTransaction().add(R.id.fml, firstFrag).commit();
        }
        else if(EWorkers.screennum == 2)
        {
            Button bt = (Button) findViewById(R.id.sb1);
            bt.setBackgroundResource(R.drawable.rounded_yellow);

            Button bt2 = (Button) findViewById(R.id.sb2);
            bt2.setBackgroundResource(R.drawable.notsel);
            Button bt3 = (Button) findViewById(R.id.sb3);
            bt3.setBackgroundResource(R.drawable.notsel);

            Plumber1 firstFrag = new Plumber1();
            firstFrag.myContext = this;
            // add fragment to the fragment container layout

            getSupportFragmentManager().beginTransaction().add(R.id.fml, firstFrag).commit();

        }
        else if(EWorkers.screennum == 4)
        {
            Button bt = (Button) findViewById(R.id.sb1);
            bt.setBackgroundResource(R.drawable.rounded_yellow);

            Button bt2 = (Button) findViewById(R.id.sb2);
            bt2.setBackgroundResource(R.drawable.notsel);
            Button bt3 = (Button) findViewById(R.id.sb3);
            bt3.setBackgroundResource(R.drawable.notsel);

            WashingMachine firstFrag = new WashingMachine();
            firstFrag.myContext = this;
            // add fragment to the fragment container layout

            getSupportFragmentManager().beginTransaction().add(R.id.fml, firstFrag).commit();

        }
        else if(EWorkers.screennum == 5)
        {
            Button bt = (Button) findViewById(R.id.sb1);
            bt.setBackgroundResource(R.drawable.rounded_yellow);

            Button bt2 = (Button) findViewById(R.id.sb2);
            bt2.setBackgroundResource(R.drawable.notsel);
            Button bt3 = (Button) findViewById(R.id.sb3);
            bt3.setBackgroundResource(R.drawable.notsel);

            Refrigerator firstFrag = new Refrigerator();
            firstFrag.myContext = this;
            // add fragment to the fragment container layout

            getSupportFragmentManager().beginTransaction().add(R.id.fml, firstFrag).commit();

        }
        else if(EWorkers.screennum == 6)
        {
            Button bt = (Button) findViewById(R.id.sb1);
            bt.setBackgroundResource(R.drawable.rounded_yellow);

            Button bt2 = (Button) findViewById(R.id.sb2);
            bt2.setBackgroundResource(R.drawable.notsel);
            Button bt3 = (Button) findViewById(R.id.sb3);
            bt3.setBackgroundResource(R.drawable.notsel);

            HairDresses firstFrag = new HairDresses();
            firstFrag.myContext = this;
            // add fragment to the fragment container layout

            getSupportFragmentManager().beginTransaction().add(R.id.fml, firstFrag).commit();

        }
        else if(EWorkers.screennum == 7)
        {
            Button bt = (Button) findViewById(R.id.sb1);
            bt.setBackgroundResource(R.drawable.rounded_yellow);

            Button bt2 = (Button) findViewById(R.id.sb2);
            bt2.setBackgroundResource(R.drawable.notsel);
            Button bt3 = (Button) findViewById(R.id.sb3);
            bt3.setBackgroundResource(R.drawable.notsel);

            Beautician firstFrag = new Beautician();
            firstFrag.myContext = this;
            // add fragment to the fragment container layout

            getSupportFragmentManager().beginTransaction().add(R.id.fml, firstFrag).commit();

        }
        else if(EWorkers.screennum == 8)
        {
            Button bt = (Button) findViewById(R.id.sb1);
            bt.setBackgroundResource(R.drawable.rounded_yellow);

            Button bt2 = (Button) findViewById(R.id.sb2);
            bt2.setBackgroundResource(R.drawable.notsel);
            Button bt3 = (Button) findViewById(R.id.sb3);
            bt3.setBackgroundResource(R.drawable.notsel);

            Laundary firstFrag = new Laundary();
            firstFrag.myContext = this;
            // add fragment to the fragment container layout

            getSupportFragmentManager().beginTransaction().add(R.id.fml, firstFrag).commit();

        }
        else if(EWorkers.screennum == 9)
        {
            Button bt = (Button) findViewById(R.id.sb1);
            bt.setBackgroundResource(R.drawable.rounded_yellow);

            Button bt2 = (Button) findViewById(R.id.sb2);
            bt2.setBackgroundResource(R.drawable.notsel);
            Button bt3 = (Button) findViewById(R.id.sb3);
            bt3.setBackgroundResource(R.drawable.notsel);

            PestControl firstFrag = new PestControl();
            firstFrag.myContext = this;
            // add fragment to the fragment container layout

            getSupportFragmentManager().beginTransaction().add(R.id.fml, firstFrag).commit();

        }
        else if(EWorkers.screennum ==10)
        {
            Button bt = (Button) findViewById(R.id.sb1);
            bt.setBackgroundResource(R.drawable.rounded_yellow);

            Button bt2 = (Button) findViewById(R.id.sb2);
            bt2.setBackgroundResource(R.drawable.notsel);
            Button bt3 = (Button) findViewById(R.id.sb3);
            bt3.setBackgroundResource(R.drawable.notsel);

            FurnitureTransfer firstFrag = new FurnitureTransfer();
            firstFrag.myContext = this;
            // add fragment to the fragment container layout

            getSupportFragmentManager().beginTransaction().add(R.id.fml, firstFrag).commit();

        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
