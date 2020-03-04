package com.test.junket;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class PaymentFailed extends BaseActivity {
    TextView hotel_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_failed);

        //Voice Assistant module
        String toSpeak = "Transaction Failed " + " Please try again ";

        assistance.speak(toSpeak);

        String name = getIntent().hasExtra("hotel_name") ? getIntent().getStringExtra("hotel_name") : "";

        hotel_name = (TextView)findViewById(R.id.hotel_name);

        hotel_name.setText(name);
    }
}
