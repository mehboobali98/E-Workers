package com.example.islahmanzilagain;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import Models.Mod;

public class OrderItemUserAdapter extends RecyclerView.Adapter<OrderItemUserAdapter.OrderItemUserViewHolder> {

    List<Mod> orderList;

    OnOrderClick click;

    public OrderItemUserAdapter(List<Mod> ls, OnOrderClick click){

        this.click = click;
        orderList = ls;
    }

    @NonNull
    @Override
    public OrderItemUserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.order_item, parent, false);
        return new OrderItemUserViewHolder(view, click);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderItemUserViewHolder holder, int position) {

        String dataWhole = orderList.get(position).getSting();
        String dataBroken[] = dataWhole.split("\\^");

        String lastScreenBroken[] = dataBroken[2].replace("!@#", " ").split("!@#");


        int mon = orderList.get(position).getTimestamp().toDate().getMonth() + 1;
        int year = orderList.get(position).getTimestamp().toDate().getYear() + 1900;
        String time = orderList.get(position).getTimestamp().toDate().getDate() +  "/" + mon  + "/"
                + year;

        // ****************************************** FIX THIS FROM THE UPLOADING PART ****************************************
        try {
            holder.txt1.setText("Order Date: " + time);
            holder.txt2.setText("Service Type: " + dataBroken[0].split("!@#")[0]);
        }
        catch (Exception e){
            System.out.println(e);
        }

    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class OrderItemUserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        TextView txt1;
        TextView txt2;
        OnOrderClick orderClick;
        public OrderItemUserViewHolder(@NonNull View itemView, OnOrderClick orderClick) {
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
