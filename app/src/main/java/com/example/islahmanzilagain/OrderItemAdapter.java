package com.example.islahmanzilagain;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import Models.Mod;

public class OrderItemAdapter extends RecyclerView.Adapter<OrderItemAdapter.OrderItemViewHolder> {

    List<Mod> orderList;

    OnOrderClick click;

    public OrderItemAdapter(List<Mod> ls, OnOrderClick click){

        this.click = click;
        orderList = ls;
    }

    @NonNull
    @Override
    public OrderItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.order_item, parent, false);
        return new OrderItemViewHolder(view, click);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderItemViewHolder holder, int position) {

        String dataWhole = orderList.get(position).getSting();
        String dataBroken[] = dataWhole.split("\\^");


        // ****************************************** FIX THIS FROM THE UPLOADING PART ****************************************
        try {
            holder.txt1.setText("Order Specifications: " + dataBroken[0].replace("!@#", " "));
            holder.txt2.setText("Time and Details: " + dataBroken[2].replace("!@#", " "));
        }
        catch (Exception e){
            System.out.println(e);
        }

    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class OrderItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        TextView txt1;
        TextView txt2;
        OnOrderClick orderClick;
        public OrderItemViewHolder(@NonNull View itemView, OnOrderClick orderClick) {
            super(itemView);

            this.orderClick = orderClick;
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            txt1 = itemView.findViewById(R.id.locationText);
            txt2 = itemView.findViewById(R.id.dataText);
        }

        @Override
        public void onClick(View v) {
            orderClick.onOrderClick(getAdapterPosition(), orderList.get(getAdapterPosition()));
        }


        @Override
        public boolean onLongClick(View v) {
            orderClick.onOrderLongClick(getAdapterPosition(), orderList.get(getAdapterPosition()));
            return true;
        }
    }


    public interface OnOrderClick{
        void onOrderLongClick(int position, Mod selectedFood);
        void onOrderClick(int position, Mod selectedOrder);
    }
}
