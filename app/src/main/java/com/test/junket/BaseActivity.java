package com.test.junket;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.test.junket.Utils.Assistance;

abstract class BaseActivity extends AppCompatActivity {

    protected Assistance assistance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assistance = new Assistance(getApplicationContext());
    }
}
