package com.test.junket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import com.test.junket.Utils.AllSharedPrefernces;

public class SplashscreenActivity extends BaseActivity {
    AllSharedPrefernces allSharedPrefernces;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        allSharedPrefernces = new AllSharedPrefernces(this);
        Thread background = new Thread() {
            public void run() {
                try {
                    // Thread will sleep for 2 seconds
                    sleep(2*1000);
                    Intent i;
                    // After 5 seconds redirect to another intent
                    if (allSharedPrefernces.isUserLogin()) {
                        if (TextUtils.isEmpty(allSharedPrefernces.getSeletedCity())) {
                            i = new Intent(getBaseContext(), SelectCityActivity.class);
                        } else {
                            i = new Intent(getBaseContext(), HotelSearchActivity.class);
                        }
                    } else {
                        i = new Intent(getBaseContext(), LoginActivity.class);
                    }

                    startActivity(i);
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        // start thread
        background.start();
    }
}
