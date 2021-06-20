package com.example.eworkers;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.firestore.DocumentSnapshot;

import API.FirebaseRepository;
import API.IslahManzil;
import API.Strings;
import ViewModels.UserViewModel;


public class Login extends AppCompatActivity {

    private EditText emailField;
    EditText passwordField;
    TextView receive;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in1);






    }

    public void signInTapped(View view) {
        emailField = (EditText) findViewById(R.id.emailField);
        passwordField = (EditText) findViewById(R.id.passwordField);

        String email;
        String password;
        email = emailField.getText().toString();

        password = passwordField.getText().toString();

        boolean fieldsEmpty = false;

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            emailField.setError( "Enter a valid Email!" );
            emailField.requestFocus();
        }
        else if(password.isEmpty()){
            passwordField.setError("Please Enter Password");
            fieldsEmpty = true;
        }

        if(!fieldsEmpty){

            signIn(email, password, this);

        }

    }

    public void signUpTapped(View view) {
        Intent myIntent = new Intent(Login.this,
                SignUp.class);
        finish();
        startActivity(myIntent);
    }

    public void signIn(String email, String password, final Login ViewRef){

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            /*
                            Intent myIntent = new Intent(ViewRef,
                                    Home.class);
                            ViewRef.finish();
                            ViewRef.startActivity(myIntent);
                            */
                            FirebaseRepository.getFire().getUserDataAddress().get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                @Override
                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                    if(documentSnapshot.getString(Strings.PHONE) == null){

                                        Intent myIntent = new Intent(Login.this,
                                                GetPhoneNumber.class);
                                        finish();
                                        startActivity(myIntent);
                                    }
                                    else if(documentSnapshot.getString(Strings.PHONE).equals(Strings.ADMINNUMBER)){
                                        Intent myIntent = new Intent(Login.this,
                                                AdminPanel.class);
                                        finish();
                                        startActivity(myIntent);
                                        IslahManzil.getIslah().setUser(new UserViewModel().getCurrenUser(FirebaseAuth.getInstance().getCurrentUser().getUid()).getValue());
                                    }
                                    else{
                                        Intent myIntent = new Intent(Login.this,
                                                Homescr.class);
                                        finish();
                                        startActivity(myIntent);

                                        IslahManzil.getIslah().setUser(new UserViewModel().getCurrenUser(FirebaseAuth.getInstance().getUid()).getValue());
                                    }
                                }

                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Intent myIntent = new Intent(Login.this,
                                            GetPhoneNumber.class);
                                    finish();
                                    startActivity(myIntent);
                                }
                            });



                        } else {
                            try {
                                throw task.getException();
                            }
                            catch (FirebaseAuthInvalidCredentialsException e) {
                                Toast.makeText(ViewRef.getApplicationContext(), "Email Or Password is Incorrect", Toast.LENGTH_SHORT).show();
                            } catch (Exception e) {
                                Toast.makeText(ViewRef.getApplicationContext(), "ERROR! Email or Password is Incorrect.", Toast.LENGTH_SHORT).show();
                                //Toast.makeText(ViewRef.getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                });
    }

}
