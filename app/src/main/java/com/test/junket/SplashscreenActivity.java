package com.test.junket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.test.junket.Utils.AllSharedPrefernces;

public class SplashscreenActivity extends AppCompatActivity {

    AllSharedPrefernces allSharedPrefernces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        allSharedPrefernces = new AllSharedPrefernces(this);

        Thread background = new Thread() {
            public void run() {
                try {
                    // Thread will sleep for 5 seconds
                    sleep(2*1000);

                    // After 5 seconds redirect to another intent
                    if (allSharedPrefernces.isUserLogin()) {
                        Intent i = new Intent(getBaseContext(), SelectCityActivity.class);
                        startActivity(i);
                    } else {
                        Intent i = new Intent(getBaseContext(), LoginActivity.class);
                        startActivity(i);
                    }

                    //Remove activity
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
