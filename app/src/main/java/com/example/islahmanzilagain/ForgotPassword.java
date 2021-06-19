package com.example.islahmanzilagain;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;



public class ForgotPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
    }



    public void btn_reset(View view) {

            TextView tv = (TextView)findViewById(R.id.textView3);
            tv.setText("12345");


        AlertDialog alertDialog = new AlertDialog.Builder(ForgotPassword.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("Password has been changed to 12345");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();




    }
}
