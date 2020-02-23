package com.test.junket;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.test.junket.Utils.Constants;
import com.test.junket.adapters.HotelRoomAdapter;
import com.test.junket.adapters.HotelRoomAdapter.HotelRoomListOnClickListener;
import com.test.junket.models.HotelResultVo;
import com.test.junket.models.HotelRoomVo;

public class HotelviewActivity extends AppCompatActivity {
    TextView txt_hotelname, txt_hotelcity, txt_hotelrating, txt_hotelprice;
    LinearLayout linear_call, linear_inquiry, linear_location, linear_share, linear_more;
    ImageView img_hotel;

    HotelResultVo hotelResultVo;

    // Intent Extra data
    String hotel_data, city, days, beds;

    RecyclerView recvRoomss;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotelview);

        TextView txt_hotelname = (TextView) findViewById(R.id.txt_hotelname);
        TextView txt_hotelcity = (TextView) findViewById(R.id.txt_hotelcity);
        TextView txt_hotelrating = (TextView) findViewById(R.id.txt_hotelrating);
        TextView txt_hotelprice = (TextView) findViewById(R.id.txt_hotelprice);
        TextView txt_pay = (TextView) findViewById(R.id.txt_pay);

        LinearLayout linear_call = (LinearLayout) findViewById(R.id.linear_call);
        LinearLayout linear_inquiry = (LinearLayout) findViewById(R.id.linear_inquiry);
        LinearLayout linear_location = (LinearLayout) findViewById(R.id.linear_location);
        LinearLayout linear_share = (LinearLayout) findViewById(R.id.linear_share);
        LinearLayout linear_more = (LinearLayout) findViewById(R.id.linear_more);

        recvRoomss = (RecyclerView) findViewById(R.id.recvRooms);
        recvRoomss.setLayoutManager(new LinearLayoutManager(this));

        ImageView img_hotel=(ImageView)findViewById(R.id.img_hotel);

        hotel_data = getIntent().hasExtra("hotel_data")?getIntent().getStringExtra("hotel_data"):"";
        days = getIntent().hasExtra("days")?getIntent().getStringExtra("days"):"";
        city = getIntent().hasExtra("city")?getIntent().getStringExtra("city"):"";
        beds = getIntent().hasExtra("beds")?getIntent().getStringExtra("beds"):"";

        if (!TextUtils.isEmpty(hotel_data))
        {
            hotelResultVo=new Gson().fromJson(hotel_data,HotelResultVo.class);
                if (hotelResultVo!=null)
                {
                    txt_hotelname.setText(hotelResultVo.getHotelierName());
                    txt_hotelcity.setText(hotelResultVo.getCity());
                    txt_hotelrating.setText(hotelResultVo.getRating());
                    txt_hotelprice.setText(Constants.rupee_code +" "+ hotelResultVo.getMinPrice()+ "/Night");

                    Picasso.get().load(hotelResultVo.getImage()).into(img_hotel);

                    if (hotelResultVo.getRooms() != null) {
                        if (hotelResultVo.getRooms().size() > 0) {

                            HotelRoomAdapter adapter = new HotelRoomAdapter(
                                this,
                                hotelResultVo.getRooms(),
                                new HotelRoomListOnClickListener() {
                                    @Override
                                    public void onRoomSelected(int position, HotelRoomVo data) {
                                        openCheckoutActivity(data);
                                    }
                                }
                            );
                            recvRoomss.setAdapter(adapter);

                        }
                    }


                }
        }

    }

    public void openCheckoutActivity(HotelRoomVo data) {
        Intent i = new Intent(this, CheckoutActivity.class);
        i.putExtra("room_data", new Gson().toJson(data));
        i.putExtra("days", days);
        i.putExtra("beds", beds);
        i.putExtra("city", city);
        startActivity(i);
    }
}
