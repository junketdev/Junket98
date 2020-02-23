package com.test.junket;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.test.junket.Utils.Constants;
import com.test.junket.Utils.DataInterface;
import com.test.junket.Utils.Webservice_Volley;
import com.test.junket.adapters.HotelListAdapter;
import com.test.junket.adapters.HotelListAdapter.HotelListOnClickListener;
import com.test.junket.models.HotelInfoVo;
import com.test.junket.models.HotelResultVo;
import java.util.HashMap;
import org.json.JSONObject;

public class HotelListActivity extends AppCompatActivity implements DataInterface {

    Webservice_Volley Volley = null;

    String city, days, beds;

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

        city = getIntent().getStringExtra("city");
        days = getIntent().getStringExtra("days");
        beds = getIntent().getStringExtra("beds");

        if (city != null) {
            params.put("city", city);
            Volley.CallVolley(url, params, "HotelListActivity");
        } else {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
            finish();
        }
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
        i.putExtra("hotel_data", new Gson().toJson(data));
        i.putExtra("city", city);
        i.putExtra("days", days);
        i.putExtra("beds", beds);
        startActivity(i);
    }
}