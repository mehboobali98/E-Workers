package com.example.eworkers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

import Models.User;
import ViewModels.UserViewModel;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);




        UserViewModel uvm = new UserViewModel();
        uvm.getCurrenUser(FirebaseAuth.getInstance().getUid()).observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                EditText name = (EditText) findViewById(R.id.nameField);
                EditText phoneNumber = (EditText) findViewById(R.id.phoneField);
                name.setText(user.getName().toString());
                phoneNumber.setText(user.getPhoneNumber().toString());
            }
        });





    }
}
