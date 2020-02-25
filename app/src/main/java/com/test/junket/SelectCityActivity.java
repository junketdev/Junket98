package com.test.junket;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.test.junket.Utils.GPSTracker;

public class SelectCityActivity extends AppCompatActivity implements GPSTracker.GetLocationUpdateListener {

    LinearLayout linear_detectmylocation, linear_myinterest;
    EditText edt_search;
    ListView list_city;
    GPSTracker gpsTracker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_city);

        linear_detectmylocation = (LinearLayout) findViewById(R.id.linear_detectmylocation);

        linear_detectmylocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gpsTracker = new GPSTracker(SelectCityActivity.this, SelectCityActivity.this);

            }
        });

        linear_myinterest = (LinearLayout) findViewById(R.id.linear_myinterest);
        linear_myinterest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), QuestionnaireActivity.class);
                startActivity(i);
            }
        });

        edt_search = (EditText) findViewById(R.id.edt_search);

        list_city = (ListView) findViewById(R.id.list_city);

        list_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void getUpdatedLocation(Location location) {
        Toast.makeText(
                this,
                "Lat: " + location.getLatitude() + " Long: " + location.getLongitude(),
                Toast.LENGTH_LONG
        ).show();
    }
}
