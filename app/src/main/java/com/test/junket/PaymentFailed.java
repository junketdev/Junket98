package com.test.junket;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PaymentFailed extends BaseActivity {
    TextView hotel_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_failed);

        //Voice Assistant module
        final String toSpeak = "Payment Failed,  Please try again.";

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                assistance.speak(toSpeak);
            }
        },500);

        String name = getIntent().hasExtra("hotel_name") ? getIntent().getStringExtra("hotel_name") : "";

        hotel_name = (TextView)findViewById(R.id.hotel_name);

        hotel_name.setText(name);
    }

    public void clickTryAgain(View view){
        finish();
    }
}
