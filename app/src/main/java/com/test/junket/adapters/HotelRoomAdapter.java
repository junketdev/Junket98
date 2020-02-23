package com.test.junket.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.test.junket.R;
import com.test.junket.models.HotelRoomVo;
import java.util.ArrayList;
import java.util.List;

public class HotelRoomAdapter extends RecyclerView.Adapter<HotelRoomAdapter.MyViewHolder> {

    Context mContext;
    List<HotelRoomVo> roomVos = new ArrayList<>();
    HotelRoomListOnClickListener listener;

    public HotelRoomAdapter(Context context, List<HotelRoomVo> roomVoList, HotelRoomListOnClickListener listener) {
        mContext = context;
        roomVos = roomVoList;
        this.listener = listener;
    }
//this is sample
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View vs = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_roomrow,viewGroup,false);

        return new MyViewHolder(vs);
    }
//this is sample
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, final int i) {

        final HotelRoomVo hotelRoomVo = roomVos.get(i);

        //viewHolder.img_roompic.setText(hotelRoomVo).getroom_images());
        viewHolder.txt_roomtype.setText(hotelRoomVo.getRoomType());
        viewHolder.txt_descrip.setText(hotelRoomVo.getRoomDescription());
        viewHolder.txt_roomprice.setText(hotelRoomVo.getRoomPrice() + "/Night");
        //viewHolder.btn_bookroom.setText(hotelRoomVo.getRoomDescription());

//        Picasso.get().load(hotelRoomVo.getRoomImages()).into(viewHolder.img_roompic);


        viewHolder.btn_bookroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            listener.onRoomSelected(i, hotelRoomVo);
            }
        });

    }

    @Override
    public int getItemCount() {
        return roomVos.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        //public BreakIterator img_roompic;
        ImageView img_roompic;
        TextView txt_roomtype,txt_descrip,txt_roomprice;
        Button btn_bookroom;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            img_roompic = (ImageView)itemView.findViewById(R.id.img_roompic);
            txt_roomtype = (TextView)itemView.findViewById(R.id.txt_roomtype);
            txt_descrip = (TextView)itemView.findViewById(R.id.txt_descrip);
            txt_roomprice = (TextView) itemView.findViewById(R.id.txt_roomprice);
            btn_bookroom = (Button) itemView.findViewById(R.id.btn_bookroom);


        }
    }

    public interface HotelRoomListOnClickListener {
        void onRoomSelected(int position, HotelRoomVo data);
    }
 }
