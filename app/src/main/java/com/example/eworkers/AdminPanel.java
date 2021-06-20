package com.example.eworkers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;

import java.util.List;

import API.FirebaseRepository;
import API.IslahManzil;
import Models.Mod;
import ViewModels.OrderViewModel;

public class AdminPanel extends AppCompatActivity {

    AdminPanel context = this;
    OrderItemAdapter.OnOrderClick click = new OrderItemAdapter.OnOrderClick() {
        @Override
        public void onOrderLongClick(int position, final Mod selectedFood) {
            final ProgressDialog progress = new ProgressDialog(context);
            progress.setMessage("Deleting Item...");
            progress.setCancelable(false);
            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progress.setIndeterminate(true);
            progress.show();
            new AlertDialog.Builder(context)
                    .setTitle("Delete entry")
                    .setMessage("Are you sure you want to delete this entry?")

                    // Specifying a listener allows you to take an action before dismissing the dialog.
                    // The dialog is automatically dismissed when a dialog button is clicked.
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Continue with delete operation
                            DocumentReference documentReference = FirebaseRepository.getFire().getOrderDataAddress().document(selectedFood.getID());
                            documentReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {

                                    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerManager);
                                    recyclerView.getAdapter().notifyDataSetChanged();
                                    progress.dismiss();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getApplicationContext(), "ERROR! Could not delete.", Toast.LENGTH_SHORT);
                                    progress.dismiss();
                                }
                            });
                        }
                    })

                    // A null listener allows the button to dismiss the dialog and take no further action.
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            progress.dismiss();
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();

        }

        @Override
        public void onOrderClick(int position, Mod selectedOrder) {
            Intent myIntent = new Intent(getApplicationContext(),
                    AdminMap.class);
            //No need to finish current activity as user can get back using back


            AdminMap.currentMod = selectedOrder;

            startActivity(myIntent);
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);







        OrderViewModel vm = new OrderViewModel();

        final AdminPanel owner = this;
        vm.getOrders().observe(this, new Observer<List<Mod>>() {
            @Override
            public void onChanged(List<Mod> orders) {



                int i = 0;
                final int size = orders.size();

                /*final ProgressDialog progress = new ProgressDialog(owner);
                if(size != 0) {

                    progress.setMessage("Loading Orders...");
                    progress.setCancelable(false);
                    progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progress.setIndeterminate(true);
                    progress.show();
                }*/


                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerManager);
                OrderItemAdapter adapter = new OrderItemAdapter(orders, click);
                recyclerView.setAdapter(adapter);

            }
        });








    }

    public void logoutClicked(View view) {
        IslahManzil.getIslah().logout();
        Intent myIntent = new Intent(getApplicationContext(),
                SplashScreen.class);
        finish();
        startActivity(myIntent);
    }
}
