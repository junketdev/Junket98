package com.test.junket.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.test.junket.HotelviewActivity;
import com.test.junket.R;
import com.test.junket.models.HotelResultVo;

import java.util.ArrayList;
import java.util.List;

public class HotelListAdapter extends RecyclerView.Adapter<HotelListAdapter.MyViewHolder> {

    Context mContext;
    List<HotelResultVo> resultVos = new ArrayList<>();

    public HotelListAdapter(Context context, List<HotelResultVo> resultVoList) {
        mContext = context;
        resultVos = resultVoList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View vs = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_hotel_list_row,viewGroup,false);

        return new MyViewHolder(vs);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int i) {

        try {
            final HotelResultVo hotelResultVo = resultVos.get(i);

            viewHolder.txt_hotelname.setText(hotelResultVo.getHotelierName());
            viewHolder.txt_hotelprice.setText(hotelResultVo.getMinPrice() + "/Night");
            viewHolder.txt_hotelrating.setText(hotelResultVo.getRating());

            Picasso.get().load(hotelResultVo.getImage()).into(viewHolder.img_hotel);


            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent i = new Intent(mContext, HotelviewActivity.class);
                    i.putExtra("data", new Gson().toJson(hotelResultVo));
                    mContext.startActivity(i);

                }
            });
        }
        catch (Exception e) {e.printStackTrace();}

    }

    @Override
    public int getItemCount() {
        return resultVos.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txt_hotelname,txt_hotelrating,txt_hotelprice;
        ImageView img_hotel;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_hotelprice = (TextView)itemView.findViewById(R.id.txt_hotelprice);
            txt_hotelname = (TextView)itemView.findViewById(R.id.txt_hotelname);
            txt_hotelrating = (TextView)itemView.findViewById(R.id.txt_hotelrating);
            img_hotel = (ImageView) itemView.findViewById(R.id.img_hotel);


        }
    }
 }
