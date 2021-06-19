package com.example.islahmanzilagain;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import API.IslahManzil;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Map#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Map extends Fragment implements OnMapReadyCallback{

    public static GoogleMap map;
    SupportMapFragment mapFragment;
    SearchView searchView;
    String str;

    public static Address address = null;

    OnMapReadyCallback thisFrag = this;

    public MainCat myContext;

    private Map.OnFragmentInteractionListener mListener;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public static Address currLocation = null;

    public static FusedLocationProviderClient fusedLocationProviderClient;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Map() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Map.
     */
    // TODO: Rename and change types and number of parameters
    public static Map newInstance(String param1, String param2) {
        Map fragment = new Map();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        handleLocationTasks();


        //map.getUiSettings().setMyLocationButtonEnabled(true);
        map.getUiSettings().setAllGesturesEnabled(true);
        map.getUiSettings().setMyLocationButtonEnabled(true);
        map.getUiSettings().setCompassEnabled(true);
        map.getUiSettings().setMapToolbarEnabled(true);
        map.getUiSettings().setZoomControlsEnabled(true);
        map.getUiSettings().setIndoorLevelPickerEnabled(true);


    }

    private void handleLocationTasks() {
        if (ActivityCompat.checkSelfPermission(myContext, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getLocation();
        } else {
            ActivityCompat.requestPermissions(myContext, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
            if (ActivityCompat.checkSelfPermission(myContext, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                getLocation();
            }
        }
    }

    private void getLocation() {
        Map.fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {

                Location location = task.getResult();
                if (location != null) {

                    try {
                        Geocoder geocoder = new Geocoder(myContext, Locale.getDefault());
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


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);



    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.google_map);

        mapFragment.getMapAsync(thisFrag);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View root = inflater.inflate(R.layout.fragment_map, container, false);
        Button bt = (Button) myContext.findViewById(R.id.sb2);
        bt.setBackgroundResource(R.drawable.rounded_yellow);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(myContext);

        Button bt2 = (Button)  myContext.findViewById(R.id.sb1);
        bt2.setBackgroundResource(R.drawable.notsel);
        Button bt3 = (Button)  myContext.findViewById(R.id.sb3);
        bt3.setBackgroundResource(R.drawable.notsel);

        searchView = root.findViewById(R.id.sv_location);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String location = searchView.getQuery().toString();
                str=location;
                List<Address> addressList = null;


                if(location != null && !location.isEmpty()){
                    Geocoder geocoder = new Geocoder(getContext());
                    try {
                        addressList = geocoder.getFromLocationName(location, 1);
                        Address addr = addressList.get(0);

                        address = addr;
                        LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                        map.clear();
                        map.addMarker(new MarkerOptions().position(latLng).title(location));


                        map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                    } catch (Exception e) {

                        e.printStackTrace();
                    }

                }


                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });



        Button butt = root.findViewById(R.id.mapButt);

        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(address == null){
                    Toast.makeText(getContext(), "ERROR! Please Select a Location First.", Toast.LENGTH_SHORT).show();
                }
                else {

                    String newData = address.getLatitude()+"!@#"+address.getLongitude()+"!@#"+address.getAddressLine(0)+"^";
                    IslahManzil.getIslah().M[IslahManzil.screennum - 1].setSting(IslahManzil.getIslah().M[IslahManzil.screennum - 1].getSting() + newData);

                    Electrician2 mp = new Electrician2();
                    mp.myContext = myContext;

                    myContext.getSupportFragmentManager().beginTransaction().replace(R.id.fml, mp).commit();
                }
            }
        });


        return root;

    }
}
