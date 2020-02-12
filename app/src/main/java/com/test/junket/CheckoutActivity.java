package com.test.junket;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class CheckoutActivity extends AppCompatActivity {

    TextView txt_room, txt_roomprice, txt_charge, txt_chargeprice, txt_amount, txt_amountprice;

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





    }
}
