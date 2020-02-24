package com.test.junket;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import com.google.gson.Gson;
import com.test.junket.Utils.Constants;
import com.test.junket.models.HotelBookingInfo;
import com.test.junket.models.HotelRoomVo;

public class  CheckoutActivity extends AppCompatActivity {

    TextView txt_room, txt_roomprice, txt_charge, txt_chargeprice, txt_amount, txt_amountprice;

    HotelBookingInfo bookingInfo;

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

        String bookingInfoJson = getIntent().hasExtra("booking_info") ? getIntent().getStringExtra("booking_info") : "";
        bookingInfo = new Gson().fromJson(bookingInfoJson, HotelBookingInfo.class);
        roomData = bookingInfo.getRoomInfo();

        setData();
    }

    private void setData() {

        totalCharge(bookingInfo.getDays(), bookingInfo.getBeds(), Integer.parseInt(roomData.getRoomPrice()));

    }

    public double totalCharge(int days, int beds, int roomprice) {
        double amount = days*beds*roomprice;
        double addcharge = (amount)*0.10;
        double payAmount =  amount + addcharge;

        int guests = bookingInfo.getAdults() + bookingInfo.getChildren();
        txt_room.setText(guests + " guest for " + bookingInfo.getDays() + " nights :");
        txt_amountprice.setText(Constants.rupee_code+payAmount);
        txt_chargeprice.setText(Constants.rupee_code+addcharge);
        txt_roomprice.setText(Constants.rupee_code+amount);

        return payAmount;
    }
}
