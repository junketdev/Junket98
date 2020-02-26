package com.test.junket.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.test.junket.HotelSearchActivity;
import com.test.junket.R;
import com.test.junket.Utils.AllSharedPrefernces;
import com.test.junket.models.BookingResultVo;
import com.test.junket.models.DestinationResultVo;

import java.util.ArrayList;
import java.util.List;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.MyViewHolder> {

    Context mContext;
    List<BookingResultVo> resultVos = new ArrayList<>();
    AllSharedPrefernces allSharedPrefernces;

    public BookingAdapter(Context context, List<BookingResultVo> resultVoList) {
        mContext = context;
        resultVos = resultVoList;
        allSharedPrefernces = new AllSharedPrefernces(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View vs = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout__bookingrow,viewGroup,false);

        return new MyViewHolder(vs);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int i) {

        final BookingResultVo bookingResultVo = resultVos.get(i);

        viewHolder.username.setText(bookingResultVo.getUsername());
        viewHolder.dest_name.setText(bookingResultVo.getDestination());
        viewHolder.checkin.setText(bookingResultVo.getCheckinDate());
        viewHolder.checkout.setText(bookingResultVo.getCheckoutDate());
        viewHolder.rooms_per_days.setText(bookingResultVo.getTotalrooms() +" rooms for " + bookingResultVo.getTotalnight() + "nights");


    }

    @Override
    public int getItemCount() {
        return resultVos.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView username,hotel_name,room_name,rooms_per_days,dest_name,checkin,checkout;
        ImageView hotelimg;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            username = (TextView)itemView.findViewById(R.id.username);
            hotel_name = (TextView)itemView.findViewById(R.id.hotel_name);
            room_name = (TextView) itemView.findViewById(R.id.room_name);
            rooms_per_days = (TextView) itemView.findViewById(R.id.rooms_per_days);
            dest_name = (TextView) itemView.findViewById(R.id.dest_name);
            checkin = (TextView) itemView.findViewById(R.id.checkin);
            checkout = (TextView) itemView.findViewById(R.id.checkout);
            hotelimg = (ImageView) itemView.findViewById(R.id.hotelimg);

        }
    }
}
