package com.example.islahmanzilagain.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.islahmanzilagain.AdminMap;
import com.example.islahmanzilagain.AdminPanel;
import com.example.islahmanzilagain.OrderItemAdapter;
import com.example.islahmanzilagain.OrderItemUserAdapter;
import com.example.islahmanzilagain.R;

import java.util.List;

import Models.Mod;
import ViewModels.OrderViewModel;
import ViewModels.UserOrderViewModel;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    OrderItemUserAdapter.OnOrderClick click = new OrderItemUserAdapter.OnOrderClick() {
        @Override
        public void onOrderLongClick(int position, Mod selectedFood) {

        }

        @Override
        public void onOrderClick(int position, Mod selectedOrder) {
            Intent myIntent = new Intent(getContext(),
                    AdminMap.class);
            //No need to finish current activity as user can get back using back


            AdminMap.currentMod = selectedOrder;

            startActivity(myIntent);
        }
    };
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText("My Orders");
            }
        });

        UserOrderViewModel vm = new UserOrderViewModel();

        final DashboardFragment owner = this;
        vm.getOrders().observe(owner.getActivity(), new Observer<List<Mod>>() {
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


                try {
                    if (orders.isEmpty()) {
                        TextView txt = (TextView) getActivity().findViewById(R.id.textOrder);

                        txt.setVisibility(View.VISIBLE);
                    } else {
                        TextView txt = (TextView) getActivity().findViewById(R.id.textOrder);
                        txt.setVisibility(View.INVISIBLE);
                    }
                }
                catch (Exception e){

                }
                RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.recyclerManager);
                OrderItemUserAdapter adapter = new OrderItemUserAdapter(orders, click);
                recyclerView.setAdapter(adapter);

            }
        });


        return root;
    }
}
