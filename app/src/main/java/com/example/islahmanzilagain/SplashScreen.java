package com.example.islahmanzilagain;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;

import API.FirebaseRepository;
import API.IslahManzil;
import API.Strings;
import ViewModels.UserViewModel;

public class SplashScreen extends AppCompatActivity {

    private static int TIME = 2000;
    final int RC_SIGN_IN = 123;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //IslahManzil.getIslah().logout();
        //API.getInstance().fireRepo.logout();
        /*
        ProgressDialog progress = new ProgressDialog(this);
        progress.setTitle("WELCOME");
        progress.setMessage("Loading...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.show();*/




        final FirebaseAuth mAuth = FirebaseAuth.getInstance();
        final FirebaseUser currentUser = mAuth.getCurrentUser();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(currentUser == null){

                    Intent myIntent = new Intent(SplashScreen.this,
                            SignUp.class);
                    finish();
                    startActivity(myIntent);
                    Toast.makeText(getApplicationContext(), "User was not signed in", Toast.LENGTH_SHORT);
                }
                else{
                    //The following line will initialize API and hence all the required data
                    IslahManzil.getIslah();


                    FirebaseRepository.getFire().getUserDataAddress().get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {

                            if(documentSnapshot.getString(Strings.PHONE) == null){
                                Toast.makeText(getApplicationContext(),"User was signed In but without phone, so logged him out",Toast.LENGTH_LONG).show();
                                IslahManzil.getIslah().logout();
                                Intent myIntent = new Intent(SplashScreen.this,
                                        SignUp.class);
                                finish();
                                startActivity(myIntent);
                            }
                            else if(documentSnapshot.getString(Strings.PHONE).equals(Strings.ADMINNUMBER)){
                                Intent myIntent = new Intent(SplashScreen.this,
                                        AdminPanel.class);
                                finish();
                                startActivity(myIntent);
                                IslahManzil.getIslah().setUser(new UserViewModel().getCurrenUser(mAuth.getUid()).getValue());
                            }
                            else{
                                Intent myIntent = new Intent(SplashScreen.this,
                                        Homescr.class);
                                finish();
                                startActivity(myIntent);
                                IslahManzil.getIslah().setUser(new UserViewModel().getCurrenUser(mAuth.getUid()).getValue());
                            }
                        }

                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(),"User was signed In but without phone document, so logged him out",Toast.LENGTH_LONG).show();
                            IslahManzil.getIslah().logout();
                            Intent myIntent = new Intent(SplashScreen.this,
                                    SignUp.class);
                            finish();
                            startActivity(myIntent);
                        }
                    });


                    /*FirebaseFirestore firestore = FirebaseFirestore.getInstance();
                    firestore.collection("MANAGER").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                            boolean isManager = false;
                            for(DocumentSnapshot d: queryDocumentSnapshots.getDocuments()) {

                                if(d.getString("ID").equals(currentUser.getUid())){
                                    //Is a manager

                                    isManager = true;
                                }

                            }



                            if(isManager){
                                //Is a manager
                                Intent myIntent = new Intent(SplashScreen.this,
                                        ManagerScreen.class);
                                finish();
                                startActivity(myIntent);
                            }
                            else{
                                //Not a manager

                                Intent myIntent = new Intent(SplashScreen.this,
                                        Home.class);
                                finish();
                                startActivity(myIntent);
                            }

                        }
                    });*/


                }
            }
        },TIME);

    }





}
