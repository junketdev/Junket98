package com.test.junket;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import com.google.gson.Gson;
import com.test.junket.Utils.Constants;
import com.test.junket.models.HotelRoomVo;

public class CheckoutActivity extends AppCompatActivity {

    TextView txt_room, txt_roomprice, txt_charge, txt_chargeprice, txt_amount, txt_amountprice;

    // Intent Extra data
    String hotel_room_data, city, days, beds;

    HotelRoomVo roomData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        txt_room = (TextView)findViewById(R.id.txt_room);
        txt_roomprice = (TextView)findViewById(R.id.txt_roomprice);
        txt_charge = (TextView)findViewById(R.id.txt_charge);
        txt_chargeprice = (TextView)findViewById(R.id.txt_chargeprice);
        txt_amount = (TextView)findViewById(R.id.txt_amount);
        txt_amountprice = (TextView)findViewById(R.id.txt_amountprice);

        hotel_room_data = getIntent().hasExtra("room_data") ? getIntent().getStringExtra("room_data") : "";
        days = getIntent().hasExtra("days")?getIntent().getStringExtra("days") : "";
        city = getIntent().hasExtra("city")?getIntent().getStringExtra("city") : "";
        beds = getIntent().hasExtra("beds")?getIntent().getStringExtra("beds") : "";
        roomData = new Gson().fromJson(hotel_room_data, HotelRoomVo.class);

        setData();
    }

    private void setData() {

        totalCharge(Integer.parseInt(days),Integer.parseInt(beds),Integer.parseInt(roomData.getRoomPrice()));


    }

    public double totalCharge(int days, int beds, int roomprice) {
        double amount = days*beds*roomprice;
        double addcharge = (amount)*0.10;
        double payAmount =  amount + addcharge;

        txt_amountprice.setText(Constants.rupee_code+payAmount);
        txt_chargeprice.setText(Constants.rupee_code+addcharge);
        txt_roomprice.setText(Constants.rupee_code+amount);


        return payAmount;



    }
}
