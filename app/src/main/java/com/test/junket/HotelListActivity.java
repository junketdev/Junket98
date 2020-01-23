package com.test.junket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.test.junket.Utils.Constants;
import com.test.junket.Utils.DataInterface;
import com.test.junket.Utils.Webservice_Volley;
import com.test.junket.adapters.HotelListAdapter;
import com.test.junket.models.HotelInfoVo;

import org.json.JSONObject;

import java.util.HashMap;

public class HotelListActivity extends AppCompatActivity implements DataInterface {

    Webservice_Volley Volley = null;

    RecyclerView recyclerHotels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_list);

        Volley = new Webservice_Volley(this,this);

        recyclerHotels = (RecyclerView)findViewById(R.id.recyclerHotels);
        recyclerHotels.setLayoutManager(new LinearLayoutManager(this));


       String url = Constants.Webserive_Url+ "get_hotels.php";

        HashMap<String,String> params = new HashMap<>();

        params.put("city","vadodara");

        Volley.CallVolley(url,params,"HotelListActivity");

    }

    @Override
    public void getData(JSONObject jsonObject, String tag) {

        try {

            HotelInfoVo hotelInfoVo = new Gson().fromJson(jsonObject.toString(),HotelInfoVo.class);

            if (hotelInfoVo != null) {

                if (hotelInfoVo.getResult() != null) {

                    if (hotelInfoVo.getResult().size() > 0) {

                        HotelListAdapter hotelListAdapter = new HotelListAdapter(HotelListActivity.this,hotelInfoVo.getResult());
                        recyclerHotels.setAdapter(hotelListAdapter);

                    }


                }

            }


        }
        catch (Exception e){
            e.printStackTrace();
        }



    }
}
