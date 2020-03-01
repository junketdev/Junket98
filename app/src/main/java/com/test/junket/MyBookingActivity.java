package com.test.junket;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.test.junket.Utils.AllSharedPrefernces;
import com.test.junket.Utils.Constants;
import com.test.junket.Utils.DataInterface;
import com.test.junket.Utils.Webservice_Volley;
import com.test.junket.adapters.BookingAdapter;
import com.test.junket.adapters.HotelListAdapter;
import com.test.junket.adapters.HotelListAdapter.HotelListOnClickListener;
import com.test.junket.models.BookingVo;
import com.test.junket.models.HotelBookingInfo;
import com.test.junket.models.HotelInfoVo;
import com.test.junket.models.HotelResultVo;

import org.json.JSONObject;

import java.util.HashMap;

public class MyBookingActivity extends AppCompatActivity implements DataInterface {

    Webservice_Volley Volley = null;

    AllSharedPrefernces allSharedPrefernces;
    RecyclerView recyclerBookings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_booking);

        Volley = new Webservice_Volley(this, this);

        recyclerBookings = (RecyclerView) findViewById(R.id.recyclerBookings);
        recyclerBookings.setLayoutManager(new LinearLayoutManager(this));
        allSharedPrefernces = new AllSharedPrefernces(this);


        String url = Constants.Webserive_Url + "get_booking.php";

        HashMap<String, String> params = new HashMap<>();
        params.put("user_id",allSharedPrefernces.getCustomerNo());

        Volley.CallVolley(url, params, "MyBookingActivity");
    }

    @Override
    public void getData(JSONObject jsonObject, String tag) {
        try {

            BookingVo bookingVo = new Gson().fromJson(jsonObject.toString(), BookingVo.class);

            if (bookingVo != null) {

                if (bookingVo.getResult() != null) {

                    if (bookingVo.getResult().size() > 0) {

                        BookingAdapter bookingAdapter = new BookingAdapter(
                                this,
                                bookingVo.getResult());
                        recyclerBookings.setAdapter(bookingAdapter);

                    }


                }

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}