package com.test.junket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PaymentConfirmation extends BaseActivity {
    TextView hotel_name,trans_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_successfull);

        String name = getIntent().hasExtra("hotel_name") ? getIntent().getStringExtra("hotel_name") : "";
        String id = getIntent().hasExtra("trans_id") ? getIntent().getStringExtra("trans_id") : "";

        hotel_name = (TextView)findViewById(R.id.hotel_name);
        trans_id = (TextView)findViewById(R.id.trans_id);

        hotel_name.setText(name);
        trans_id.setText(id);

    }

    public void clickonback(View view) {
        Intent i = new Intent(this,HotelSearchActivity.class);
        startActivity(i);

        finishAffinity();
    }

    @Override
    public void onBackPressed() {

    }
}
