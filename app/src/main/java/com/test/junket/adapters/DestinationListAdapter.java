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

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.test.junket.Destination_List;
import com.test.junket.HotelviewActivity;
import com.test.junket.R;
import com.test.junket.models.DestinationResultVo;
import com.test.junket.models.HotelResultVo;

import java.util.ArrayList;
import java.util.List;

public class DestinationListAdapter extends RecyclerView.Adapter<DestinationListAdapter.MyViewHolder> {

    Context mContext;
    List<DestinationResultVo> resultVos = new ArrayList<>();

    public DestinationListAdapter(Context context, List<DestinationResultVo> resultVoList) {
        mContext = context;
        resultVos = resultVoList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View vs = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_destination_row,viewGroup,false);

        return new MyViewHolder(vs);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int i) {

        final DestinationResultVo destinationResultVo = resultVos.get(i);

        viewHolder.txt_destinationname.setText(destinationResultVo.getDestName());
        viewHolder.txt_description.setText(destinationResultVo.getDescription());

        if (!TextUtils.isEmpty(destinationResultVo.getImages())) {

            Picasso.get().load(destinationResultVo.getImages()).into(viewHolder.img_destination);
        }


        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return resultVos.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txt_destinationname,txt_description;
        ImageView img_destination;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_destinationname = (TextView)itemView.findViewById(R.id.destname);
            txt_description = (TextView)itemView.findViewById(R.id.description);
            img_destination = (ImageView) itemView.findViewById(R.id.destination);


        }
    }
}
