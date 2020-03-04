package com.test.junket;

import android.content.Intent;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class PaymentConfirmation extends BaseActivity {
    TextView hotel_name,trans_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_successfull);

        //Voice Assistant module
        final String toSpeak = "Payment Successful";


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                assistance.speak(toSpeak);
            }
        },500);


        String name = getIntent().hasExtra("hotel_name") ? getIntent().getStringExtra("hotel_name") : "";
        String id = getIntent().hasExtra("trans_id") ? getIntent().getStringExtra("trans_id") : "";

        hotel_name = (TextView)findViewById(R.id.hotel_name);
        trans_id = (TextView)findViewById(R.id.trans_id);

        hotel_name.setText(name);
        trans_id.setText(id);
    }

    public void clickOnBack(View view) {
        Intent i = new Intent(this,HotelSearchActivity.class);
        //VA Module
        String toSpeak = "Directing to Home Page ";
        assistance.speak(toSpeak);

        startActivity(i);
        finishAffinity();
    }
}
