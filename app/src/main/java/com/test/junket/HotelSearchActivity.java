package com.test.junket;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class HotelSearchActivity extends AppCompatActivity {

    TextView search;

    TextView dateFrom;

    TextView dateTo;

    ImageView bedsMinus;

    ImageView bedsPlus;

    TextView bedsCount;

    ImageView adultsPlus;

    ImageView adultsMinus;

    TextView adultsCount;

    ImageView childrenMinus;

    ImageView childrenPlus;

    TextView childrenCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_search);

        search = (TextView) findViewById(R.id.btn_search);
        dateTo = (TextView) findViewById(R.id.textView_dateTo);
        dateFrom = (TextView) findViewById(R.id.textView_dateFrom);
        bedsMinus= (ImageView) findViewById(R.id.imageView_bedsMinus);
        bedsPlus = (ImageView) findViewById(R.id.imageView_bedsPlus);
        bedsCount = (TextView) findViewById(R.id.textView_bedsCount);
        adultsPlus = (ImageView) findViewById(R.id.imageView_adultsPlus);
        adultsMinus = (ImageView) findViewById(R.id.imageView_adultsMinus);
        adultsCount = (TextView) findViewById(R.id.textView_adultsCount);
        childrenMinus = (ImageView) findViewById(R.id.imageView_childrenMinus);
        childrenPlus = (ImageView) findViewById(R.id.imageView_childrenPlus);
        childrenCount = (TextView) findViewById(R.id.textView_childrenCount);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHotelDetails(v);
            }
        });

        dateTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker(dateTo);
            }
        });

        dateFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker(dateFrom);
            }
        });

        bedsMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrementer(bedsCount);
            }
        });

        bedsPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementer(bedsCount);
            }
        });

        adultsPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementer(adultsCount);
            }
        });

        adultsMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrementer(adultsCount);
            }
        });

        childrenMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrementer(childrenCount);
            }
        });

        childrenPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementer(childrenCount);
            }
        });
    }

    private void showHotelDetails(View v) {
        Intent i = new Intent(this, HotelListActivity.class);
        startActivity(i);
    }

    private void incrementer(TextView countView) {
        int currentCount = Integer.parseInt(countView.getText().toString());
        currentCount += 1;
        countView.setText(String.valueOf(currentCount));
    }

    private void decrementer(TextView countView) {
        int currentCount = Integer.parseInt(countView.getText().toString());
        if (currentCount <= 0) return;
        currentCount -= 1;
        countView.setText(String.valueOf(currentCount));
    }

    private void showDatePicker(final TextView date) {
        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);
        // date picker dialog
        DatePickerDialog picker = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }, year, month, day);

        picker.show();
    }
}
