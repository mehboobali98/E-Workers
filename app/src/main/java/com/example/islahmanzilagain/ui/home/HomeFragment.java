package com.example.islahmanzilagain.ui.home;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.islahmanzilagain.Login;
import com.example.islahmanzilagain.MainCat;
import com.example.islahmanzilagain.R;
import com.example.islahmanzilagain.SignUp;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import API.FirebaseRepository;
import API.IslahManzil;
import API.SliderAdapterExample;
import API.SliderItem;
import API.Strings;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    SliderView sliderView;
    private SliderAdapterExample adapter;
    private AdView mAdView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_home, container, false);

        Log.d("MyAd", "Before initializing ad.");
        MobileAds.initialize(getContext(), "ca-app-pub-3940256099942544~3347511713");
        mAdView = (AdView) root.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        sliderView = root.findViewById(R.id.imageSlider);
        adapter = new SliderAdapterExample(getActivity());
        sliderView.setSliderAdapter(adapter);

        sliderView.setIndicatorAnimation(IndicatorAnimations.THIN_WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_RIGHT);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(3);
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();
        addNewItem(sliderView,1);
        addNewItem(sliderView,2);
        addNewItem(sliderView,3);
        addNewItem(sliderView,4);
        addNewItem(sliderView,5);
        addNewItem(sliderView,6);
        addNewItem(sliderView,7);

        sliderView.setOnIndicatorClickListener(new DrawController.ClickListener() {
            @Override
            public void onIndicatorClicked(int position) {
            }
        });


        final TextView textView = root.findViewById(R.id.wel);

       ImageView img = (ImageView)  root.findViewById(R.id.elecID);

        final RelativeLayout rlBt = (RelativeLayout) root.findViewById(R.id.rlbt);
        final RelativeLayout rll = (RelativeLayout) root.findViewById(R.id.rlbt);
        final RelativeLayout rlpc = (RelativeLayout) root.findViewById(R.id.rlbt);
        final RelativeLayout rlft = (RelativeLayout) root.findViewById(R.id.rlbt);


        final Button buttonBT = (Button) root.findViewById(R.id.buttonBT);
        final Button buttonL = (Button) root.findViewById(R.id.buttonL);
        final Button buttonPC = (Button) root.findViewById(R.id.buttonPC);
        final Button buttonFT = (Button) root.findViewById(R.id.buttonFT);

        final TextView textViewBT = (TextView) root.findViewById(R.id.textViewBT);
        final TextView textViewL = (TextView) root.findViewById(R.id.textViewL);
        final TextView textViewPC = (TextView) root.findViewById(R.id.textViewPC);
        final TextView textViewFT = (TextView) root.findViewById(R.id.textViewFT);


        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(),
                        MainCat.class);
                IslahManzil.screennum = 3;
                startActivity(myIntent);
            }
        });
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText("Welcome to ISLAH MANZIL");
            }
        });

        ImageView img2 = (ImageView)  root.findViewById(R.id.ac);
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(),
                        MainCat.class);
                IslahManzil.screennum = 1;
                startActivity(myIntent);
            }
        });
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        ImageView img3 = (ImageView)  root.findViewById(R.id.plumbing);
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(),
                        MainCat.class);
                IslahManzil.screennum = 2;
                startActivity(myIntent);
            }
        });
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        ImageView img4 = (ImageView)  root.findViewById(R.id.washingid);
        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(),
                        MainCat.class);
                IslahManzil.screennum = 4;
                startActivity(myIntent);
            }
        });

        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        ImageView img5 = (ImageView)  root.findViewById(R.id.refrigid);
        img5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(),
                        MainCat.class);
                IslahManzil.screennum = 5;
                startActivity(myIntent);
            }
        });
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });


        ImageView img6 = (ImageView)  root.findViewById(R.id.hairid);
        img6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(),
                        MainCat.class);
                IslahManzil.screennum = 6;
                startActivity(myIntent);
            }
        });
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });





        FirebaseRepository.getFire().getIsEnabledAddress(Strings.Beautician).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                final ImageView img7 = (ImageView) root.findViewById(R.id.beautyid);
                if(documentSnapshot.getBoolean(Strings.Enabled)) {
                    img7.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent myIntent = new Intent(getActivity(),
                                    MainCat.class);
                            IslahManzil.screennum = 7;
                            startActivity(myIntent);
                        }
                    });
                    textViewBT.setVisibility(View.INVISIBLE);
                    buttonBT.setVisibility(View.INVISIBLE);
                }
                else{


                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                final ImageView img7 = (ImageView) root.findViewById(R.id.beautyid);
                img7.setColorFilter(Color.argb(150,200,200,200));
            }
        });

        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });


        FirebaseRepository.getFire().getIsEnabledAddress(Strings.Laundary).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                ImageView img8 = (ImageView) root.findViewById(R.id.laundaryid);
                if (documentSnapshot.getBoolean(Strings.Enabled)) {
                    img8.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent myIntent = new Intent(getActivity(),
                                    MainCat.class);
                            IslahManzil.screennum = 8;
                            startActivity(myIntent);
                        }

                    });
                    textViewL.setVisibility(View.INVISIBLE);
                    buttonL.setVisibility(View.INVISIBLE);
                }
                else{
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                ImageView img8 = (ImageView) root.findViewById(R.id.laundaryid);
                img8.setColorFilter(Color.argb(150,200,200,200));
            }
        });
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });


        FirebaseRepository.getFire().getIsEnabledAddress(Strings.PestControl).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                ImageView img9 = (ImageView) root.findViewById(R.id.pestcontrolid);
                if (documentSnapshot.getBoolean(Strings.Enabled)) {

                    img9.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent myIntent = new Intent(getActivity(),
                                    MainCat.class);
                            IslahManzil.screennum = 9;
                            startActivity(myIntent);
                        }
                    });
                    textViewPC.setVisibility(View.INVISIBLE);
                    buttonPC.setVisibility(View.INVISIBLE);
                }
                else{
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                ImageView img10 = (ImageView) root.findViewById(R.id.pestcontrolid);
                img10.setColorFilter(Color.argb(150,200,200,200));
            }
        });
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });


        FirebaseRepository.getFire().getIsEnabledAddress(Strings.FurnitureTransfer).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                ImageView img10 = (ImageView) root.findViewById(R.id.Furnitureid);
                if (documentSnapshot.getBoolean(Strings.Enabled)) {

                    img10.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent myIntent = new Intent(getActivity(),
                                    MainCat.class);
                            IslahManzil.screennum = 10;
                            startActivity(myIntent);
                        }
                    });
                    textViewFT.setVisibility(View.INVISIBLE);
                    buttonFT.setVisibility(View.INVISIBLE);
                }
                else{
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                ImageView img10 = (ImageView) root.findViewById(R.id.Furnitureid);
                img10.setColorFilter(Color.argb(150,200,200,200));
            }
        });
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });


        return root;
    }

    public void renewItems(View view) {
        List<SliderItem> sliderItemList = new ArrayList<>();
        //dummy data
        for (int i = 0; i < 5; i++) {
            SliderItem sliderItem = new SliderItem();
            sliderItem.setDescription("Slider Item " + i);
            if (i % 2 == 0) {
                int drawid = R.drawable.a11;
                sliderItem.setImageUrl(drawid);
            } else {
                int drawid = R.drawable.a22;
                sliderItem.setImageUrl(drawid);
            }
            sliderItemList.add(sliderItem);
        }
        adapter.renewItems(sliderItemList);
    }

    public void removeLastItem(View view) {
        if (adapter.getCount() - 1 >= 0)
            adapter.deleteItem(adapter.getCount() - 1);
    }

    public void addNewItem(View view,int a) {
        SliderItem sliderItem = new SliderItem();
        sliderItem.setDescription(".");
        int drawid=0;
        if(a==1)
            drawid = R.drawable.a11;
       else if (a==2)
            drawid = R.drawable.a22;
       else if(a==3)
            drawid = R.drawable.a33;
        else if(a==4)
            drawid = R.drawable.a44;
        else if(a==5)
            drawid = R.drawable.a55;
        else if(a==6)
            drawid = R.drawable.a66;
        else if(a==7)
            drawid = R.drawable.a77;
        else if(a==88)
            drawid = R.drawable.a88;
        else if(a==3)
            drawid = R.drawable.a99;


        sliderItem.setImageUrl(drawid);
        adapter.addItem(sliderItem);
    }
}


