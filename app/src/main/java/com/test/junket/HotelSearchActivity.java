package com.test.junket;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.test.junket.Utils.Constants;
import com.test.junket.Utils.DataInterface;
import com.test.junket.Utils.Webservice_Volley;
import com.test.junket.adapters.AttractionAdapter;
import com.test.junket.models.AttractionVo;

import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class HotelSearchActivity extends AppCompatActivity implements DataInterface {
    Webservice_Volley Volley = null;

    TextView search,dateFrom,dateTo,bedsCount,adultsCount,childrenCount;

    ImageView bedsMinus,bedsPlus,adultsPlus,adultsMinus,childrenMinus,childrenPlus;

    RecyclerView revAttraction;

    String dest_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_search);

        Volley = new Webservice_Volley(this, this);

        dest_id = getIntent().getStringExtra("dest_id");

        HashMap<String, String> params = new HashMap<>();
        params.put("dest_id", dest_id);
        String url = Constants.Webserive_Url + "get_attraction_from_destination.php";

        Volley.CallVolley(url, params, "get_attraction_from_destination");

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

        revAttraction = (RecyclerView)findViewById(R.id.revAttraction);

        revAttraction.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));


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

    @Override
    public void getData(JSONObject jsonObject, String tag) {
        try {

            AttractionVo attractionVo = new Gson().fromJson(jsonObject.toString(),AttractionVo.class);

            if (attractionVo != null) {

                if (attractionVo.getResult() != null) {

                    if (attractionVo.getResult().size() > 0) {

                        AttractionAdapter adapter = new AttractionAdapter(this,attractionVo.getResult());
                        revAttraction.setAdapter(adapter);


                    }

                }

            }


        }
        catch (Exception e) {
            e.printStackTrace();
        }
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
