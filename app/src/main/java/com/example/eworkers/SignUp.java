package com.example.eworkers;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

import java.util.Arrays;
import java.util.List;


public class SignUp extends AppCompatActivity {

    private FirebaseAuth mAuth;

    int RC_SIGN_IN_FB = 1;
    int RC_SIGN_IN_GMAIL = 2;

    public void signInTapped(View view) {
        Intent myIntent = new Intent(SignUp.this,
                Login.class);

        finish();
        startActivity(myIntent);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN_FB || requestCode == RC_SIGN_IN_GMAIL) {
            final IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in

                Intent myIntent = new Intent(getApplicationContext(),
                        GetPhoneNumber.class);
                //Already finished splashscreen
                finish();
                startActivity(myIntent);

            } else {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...

                Toast.makeText(getApplicationContext(), "ERROR. Please check Internet Connection.", Toast.LENGTH_SHORT).show();

            }


        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up1);



        final EditText emailField = (EditText)findViewById(R.id.emailField);
        final EditText passField = (EditText)findViewById(R.id.passwordField);
        final EditText confirmPassField = (EditText)findViewById(R.id.confirmPassField);
        Button btSP = (Button) findViewById(R.id.btSP);
        mAuth = FirebaseAuth.getInstance();
        btSP.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view2) {
                String email = emailField.getText().toString().trim();
                String Password = passField.getText().toString().trim();
                String confirmPass = confirmPassField.getText().toString().trim();

                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                {
                    emailField.setError( "Enter a valid Email!" );
                    emailField.requestFocus();
                }
                else if(Password.length() < 6)
                {
                    passField.setError("Password should have minimum 6 character/numbers");
                    passField.requestFocus();
                }
                else if(!confirmPassField.getText().toString().equals(passField.getText().toString())){

                    confirmPassField.setError( "Passwords do not match!" );
                    confirmPassField.requestFocus();

                }
                else{
                    mAuth.createUserWithEmailAndPassword(email, Password)
                            .addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {

                                        Toast.makeText(SignUp.this, "Sign Up Successful", Toast.LENGTH_SHORT).show();
                                        Intent myIntent = new Intent(getApplicationContext(),
                                                GetPhoneNumber.class);
                                        //Already finished splashscreen
                                        finish();
                                        startActivity(myIntent);
                                    }
                                    else
                                    {
                                        if(task.getException() instanceof FirebaseAuthUserCollisionException)
                                            Toast.makeText(getApplicationContext(),"You are already Registered!",Toast.LENGTH_SHORT).show();
                                        else
                                            Toast.makeText(getApplicationContext(), "ERROR. Please check Internet Connection.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

            }}

        });


    }

    public void signUpFacebook(View view) {
        mAuth = FirebaseAuth.getInstance();
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.FacebookBuilder().build());
        //new AuthUI.IdpConfig.TwitterBuilder().build());
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(),
                RC_SIGN_IN_FB);

    }

    public void singUpGmail(View view) {
        mAuth = FirebaseAuth.getInstance();
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.GoogleBuilder().build());
        //new AuthUI.IdpConfig.TwitterBuilder().build());
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(),
                RC_SIGN_IN_GMAIL);
    }


}
