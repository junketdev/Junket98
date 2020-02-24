package com.test.junket;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.test.junket.Utils.Constants;
import com.test.junket.Utils.DataInterface;
import com.test.junket.Utils.Webservice_Volley;
import com.test.junket.adapters.HotelListAdapter;
import com.test.junket.adapters.HotelListAdapter.HotelListOnClickListener;
import com.test.junket.models.HotelBookingInfo;
import com.test.junket.models.HotelInfoVo;
import com.test.junket.models.HotelResultVo;

import org.json.JSONObject;

import java.util.HashMap;

public class HotelListActivity extends AppCompatActivity implements DataInterface {

    Webservice_Volley Volley = null;

    HotelBookingInfo bookingInfo;

    RecyclerView recyclerHotels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_list);

        Volley = new Webservice_Volley(this, this);

        recyclerHotels = (RecyclerView) findViewById(R.id.recyclerHotels);
        recyclerHotels.setLayoutManager(new LinearLayoutManager(this));


        String url = Constants.Webserive_Url + "get_hotels_with_room.php";

        HashMap<String, String> params = new HashMap<>();

        String bookingInfoJson = getIntent().hasExtra("booking_info") ? getIntent().getStringExtra("booking_info") : "";
        bookingInfo = new Gson().fromJson(bookingInfoJson, HotelBookingInfo.class);

        params.put("city", bookingInfo.getCity());
        Volley.CallVolley(url, params, "HotelListActivity");
    }

    @Override
    public void getData(JSONObject jsonObject, String tag) {

        try {

            HotelInfoVo hotelInfoVo = new Gson().fromJson(jsonObject.toString(), HotelInfoVo.class);

            if (hotelInfoVo != null) {

                if (hotelInfoVo.getResult() != null) {

                    if (hotelInfoVo.getResult().size() > 0) {

                        HotelListAdapter hotelListAdapter = new HotelListAdapter(
                            this,
                            hotelInfoVo.getResult(),
                            new HotelListOnClickListener() {
                                @Override
                                public void onHotelSelected(int position, HotelResultVo data) {
                                    openHotelDetail(data);
                                }
                            }
                        );
                        recyclerHotels.setAdapter(hotelListAdapter);

                    }


                }

            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void openHotelDetail(HotelResultVo data) {
        Intent i = new Intent(this, HotelviewActivity.class);
        bookingInfo.setHotelInfo(data);
        i.putExtra("booking_info", new Gson().toJson(bookingInfo));
        startActivity(i);
    }
}