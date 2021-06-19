package com.example.islahmanzilagain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.islahmanzilagain.ui.home.HomeFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import API.FirebaseRepository;
import API.IslahManzil;
import API.Strings;
import ViewModels.UserViewModel;

public class GetPhoneNumber extends AppCompatActivity {
    boolean mVerificationInProgress = false;
    //For handling phone number verification
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private final Context context = this;
    EditText name;
    EditText phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_phone_number);

        name = (EditText) findViewById(R.id.nameField);
        phone = (EditText) findViewById(R.id.phoneField);

        FirebaseRepository.getFire().getUserDataAddress().get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.getString(Strings.PHONE) == null){

                    /*Intent myIntent = new Intent(GetPhoneNumber.this,
                            HomeFragment.class);
                    finish();
                    startActivity(myIntent);*/
                }
                else if(documentSnapshot.getString(Strings.PHONE).equals(Strings.ADMINNUMBER)){
                    Intent myIntent = new Intent(GetPhoneNumber.this,
                            AdminPanel.class);
                    finish();
                    startActivity(myIntent);
                    IslahManzil.getIslah().setUser(new UserViewModel().getCurrenUser(FirebaseAuth.getInstance().getCurrentUser().getUid()).getValue());
                }
                else{
                    Intent myIntent = new Intent(GetPhoneNumber.this,
                            Homescr.class);
                    finish();
                    startActivity(myIntent);

                    IslahManzil.getIslah().setUser(new UserViewModel().getCurrenUser(FirebaseAuth.getInstance().getUid()).getValue());
                }
            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                /*Intent myIntent = new Intent(GetPhoneNumber.this,
                        GetPhoneNumber.class);
                finish();
                startActivity(myIntent);*/
            }
        });

        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(final PhoneAuthCredential credential) {
                // This callback will be invoked in two situations:
                // 1 - Instant verification. In some cases the phone number can be instantly
                //     verified without needing to send or enter a verification code.
                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                //     detect the incoming verification SMS and perform verification without
                //     user action.
                //Log.d(TAG, "onVerificationCompleted:" + credential);
                // [START_EXCLUDE silent]

                // [END_EXCLUDE]

                // [START_EXCLUDE silent]
                // Update the UI and attempt sign in with the phone credential
                //updateUI(STATE_VERIFY_SUCCESS, credential);
                // [END_EXCLUDE]
                //signInWithPhoneAuthCredential(credential);

                //mVerificationInProgress = false;
                Toast.makeText(getApplicationContext(), "Verified", Toast.LENGTH_SHORT).show();
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                final FirebaseUser currentUser = mAuth.getCurrentUser();

                currentUser.linkWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Map<String, Object> userData = new HashMap<>();
                            userData.put(Strings.NAME, name.getText().toString());
                            userData.put(Strings.PHONE, phone.getText().toString());

                            FirebaseRepository.getFire().getUserDataAddress().set(userData).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    //FirebaseFirestore.getInstance().collection(Strings.USERDATA).add(userData);
                                    Intent myIntent = new Intent(GetPhoneNumber.this,
                                            Homescr.class);
                                    finish();
                                    startActivity(myIntent);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    System.out.println(e.toString());
                                    Intent myIntent = new Intent(GetPhoneNumber.this,
                                            Homescr.class);
                                    finish();
                                    startActivity(myIntent);


                                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                        else{
                            Toast.makeText(context, "The verification code is incorrect. Please Try Again!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                //Address ad = new Address(address);
                //Customer cust = new Customer(name, phone, email, ad);
                //cust.addAddress(ad);

                //FirestoreRepository fire = API.FirestoreRepository.getInstance();

                //fire.UserDataSave(cust, password, ViewRef);


            }



            @Override
            public void onVerificationFailed(FirebaseException e) {
                // This callback is invoked in an invalid request for verification is made,
                // for instance if the the phone number format is not valid.
                /*Log.w(TAG, "onVerificationFailed", e);
                // [START_EXCLUDE silent]
                mVerificationInProgress = false;
                // [END_EXCLUDE]

                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                    // [START_EXCLUDE]
                    mPhoneNumberField.setError("Invalid phone number.");
                    // [END_EXCLUDE]
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                    // [START_EXCLUDE]
                    Snackbar.make(findViewById(android.R.id.content), "Quota exceeded.",
                            Snackbar.LENGTH_SHORT).show();
                    // [END_EXCLUDE]
                }

                // Show a message and update the UI
                // [START_EXCLUDE]
                updateUI(STATE_VERIFY_FAILED);
                // [END_EXCLUDE]

                 */
                Toast.makeText(context, "ERROR. Please Try Again!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCodeSent(@NonNull final String verificationId,
                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                /*Log.d(TAG, "onCodeSent:" + verificationId);

                // Save verification ID and resending token so we can use them later


                // [START_EXCLUDE]
                // Update UI

                // [END_EXCLUDE]


                 */
                //mVerificationId = verificationId;
                //mResendToken = token;

                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.verificationcode, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);

                final EditText userInput = (EditText) promptsView
                        .findViewById(R.id.verCode);

                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        // get user input and set it to result
                                        // edit text
                                        //result.setText(userInput.getText());
                                        String verificationCode = userInput.getText().toString();
                                        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, verificationCode);
                                        FirebaseAuth mAuth = FirebaseAuth.getInstance();
                                        final FirebaseUser currentUser = mAuth.getCurrentUser();

                                        currentUser.linkWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                                if(task.isSuccessful()){
                                                    Map<String, Object> userData = new HashMap<>();
                                                    userData.put(Strings.NAME, name.getText().toString());
                                                    userData.put(Strings.PHONE, phone.getText().toString());

                                                            FirebaseRepository.getFire().getUserDataAddress().set(userData).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                @Override
                                                                public void onSuccess(Void aVoid) {
                                                                    //FirebaseFirestore.getInstance().collection(Strings.USERDATA).add(userData);
                                                            Intent myIntent = new Intent(GetPhoneNumber.this,
                                                                    Homescr.class);
                                                            finish();
                                                            startActivity(myIntent);
                                                        }
                                                    }).addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {
                                                            System.out.println(e.toString());
                                                            Intent myIntent = new Intent(GetPhoneNumber.this,
                                                                    Homescr.class);
                                                            finish();
                                                            startActivity(myIntent);


                                                            Toast.makeText(context, "ERROR. Please Try Again!", Toast.LENGTH_SHORT).show();
                                                        }
                                                    });


                                                }
                                                else{
                                                    Toast.makeText(context, "The verification code is incorrect. Please Try Again!", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();

                Toast.makeText(getApplicationContext(), "Code Sent", Toast.LENGTH_SHORT).show();
            }
        };

    }

    public void verifyClick(View view) {

        if(name.getText().toString().isEmpty()){
            name.setError( "Please Enter Your Name!" );
            name.requestFocus();
        }
        else if(phone.getText().toString().isEmpty()){
            phone.setError( "Please Enter Your Phone Number!" );
            phone.requestFocus();
        }

        else {
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                    "+974" + phone.getText().toString(),        // Phone number to verify
                    60,                 // Timeout duration
                    TimeUnit.SECONDS,   // Unit of timeout
                    this,               // Activity (for callback binding)
                    mCallbacks);
        }
    }
}