package com.test.junket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Reset_password extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
    }
    public void clickonverify(View view) {
        Intent i = new Intent(this, Create_new_password.class);
        startActivity(i);
    }
}
