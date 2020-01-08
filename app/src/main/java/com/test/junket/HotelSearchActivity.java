package com.test.junket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HotelSearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_search);

        TextView search = (TextView) findViewById(R.id.btn_search);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHotelDetails(v);
            }
        });
    }

    private void showHotelDetails(View v) {
        Intent i = new Intent(this, HotelviewActivity.class);
        startActivity(i);

    }
}
