package com.example.eworkers;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;

import API.FirebaseRepository;
import API.IslahManzil;
import API.Strings;
import ViewModels.UserViewModel;

public class AuthenticationService extends Service {
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    @Override
    public void onCreate() {
        super.onCreate();
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        Log.d("MyServiceTag", "Authenticate Service Started.");
        authenticate();
        return Service.START_REDELIVER_INTENT;
    }

    private void authenticate() {

        if (currentUser == null) {
            Intent myIntent = new Intent(this, SignUp.class);
            myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(myIntent);
            Toast.makeText(getApplicationContext(), "User was not signed in", Toast.LENGTH_SHORT);
        } else {
            //The following line will initialize API and hence all the required data
            IslahManzil.getIslah();
            FirebaseRepository.getFire().getUserDataAddress().get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {

                    if (documentSnapshot.getString(Strings.PHONE) == null) {
                        Toast.makeText(getApplicationContext(), "User was signed In but without phone, so logged him out", Toast.LENGTH_LONG).show();
                        IslahManzil.getIslah().logout();
                        Intent myIntent = new Intent(AuthenticationService.this, SignUp.class);
                        myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(myIntent);
                    } else if (documentSnapshot.getString(Strings.PHONE).equals(Strings.ADMINNUMBER)) { //current user is admin.
                        Intent myIntent = new Intent(AuthenticationService.this, AdminPanel.class);
                        myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(myIntent);
                        IslahManzil.getIslah().setUser(new UserViewModel().getCurrenUser(mAuth.getUid()).getValue());
                    } else {
                        Intent myIntent = new Intent(AuthenticationService.this, Homescr.class);
                        myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(myIntent);
                        IslahManzil.getIslah().setUser(new UserViewModel().getCurrenUser(mAuth.getUid()).getValue());
                    }
                }

            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(), "User was signed In but without phone document, so logged him out", Toast.LENGTH_LONG).show();
                    IslahManzil.getIslah().logout();
                    Intent myIntent = new Intent(AuthenticationService.this, SignUp.class);
                    myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(myIntent);
                }
            });
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
