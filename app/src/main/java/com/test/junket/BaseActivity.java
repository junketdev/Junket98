package com.test.junket;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.test.junket.Utils.Assistance;

abstract class BaseActivity extends AppCompatActivity {

    protected Assistance assistance;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        assistance = new Assistance(getApplicationContext());
    }
}
