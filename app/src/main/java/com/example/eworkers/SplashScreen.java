package com.example.eworkers;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
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

        Intent intent = new Intent(this, AuthenticationService.class);
        startService(intent);
    }
}
