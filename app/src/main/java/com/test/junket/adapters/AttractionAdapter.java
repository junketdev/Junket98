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
import com.test.junket.AttractionDescription;
import com.test.junket.HotelSearchActivity;
import com.test.junket.HotelviewActivity;
import com.test.junket.R;
import com.test.junket.models.AttractionResultVo;
import com.test.junket.models.AttractionVo;
import com.test.junket.models.HotelResultVo;

import java.util.ArrayList;
import java.util.List;

public class AttractionAdapter extends RecyclerView.Adapter<AttractionAdapter.MyViewHolder>
{
    Context mContext;
    List<AttractionResultVo> resultVos = new ArrayList<>();

    public AttractionAdapter(Context context, List<AttractionResultVo> resultVoList) {
        mContext = context;
        resultVos = resultVoList;
    }

    @NonNull
    @Override
    public AttractionAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View vs = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_attraction_row,viewGroup,false);

        return new AttractionAdapter.MyViewHolder(vs);
    }

    @Override
    public void onBindViewHolder(@NonNull AttractionAdapter.MyViewHolder viewHolder, int i) {

        try {
            final AttractionResultVo attractionResultVo = resultVos.get(i);

            viewHolder.txt_attr_name.setText(attractionResultVo.getAttractionName());
            viewHolder.txt_attr_description.setText(attractionResultVo.getAttractionDescription());
            //viewHolder.txt_hotelrating.setText(attractionResultVo.getAttractionImages());
            //viewHolder.txt_hotelrating.setText(attractionResultVo.getAttractionDestId());

            Picasso.get().load(attractionResultVo.getAttractionImages()).into(viewHolder.img_attraction);


            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // OPEN ATTRACTION DETAIL PAGE !!

                    Intent i = new Intent(mContext, AttractionDescription.class);
                    i.putExtra("data", new Gson().toJson(attractionResultVo));
                    mContext.startActivity(i);

                }
            });
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return resultVos.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txt_attr_name,txt_attr_description;
        ImageView img_attraction;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_attr_name = (TextView)itemView.findViewById(R.id.txt_attr_name);
            txt_attr_description = (TextView)itemView.findViewById(R.id.txt_attr_description);
            img_attraction = (ImageView) itemView.findViewById(R.id.img_attraction);


        }
    }
}
