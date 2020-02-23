package com.test.junket;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.gson.Gson;
import com.test.junket.Utils.Constants;
import com.test.junket.Utils.DataInterface;
import com.test.junket.Utils.Webservice_Volley;
import com.test.junket.adapters.AttractionAdapter;
import com.test.junket.models.AttractionVo;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import org.json.JSONObject;

public class HotelSearchActivity extends AppCompatActivity implements DataInterface {
    Webservice_Volley Volley = null;

    TextView txt_search, txt_dateFrom, txt_dateTo, txt_bedsCount, txt_adultsCount, txt_childrenCount;

    ImageView iv_bedsMinus, iv_bedsPlus, iv_adultsPlus, iv_adultsMinus, iv_childrenMinus, iv_childrenPlus;

    RecyclerView revAttraction;

    String dest_id, city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_search);

        Volley = new Webservice_Volley(this, this);

        dest_id = getIntent().getStringExtra("dest_id");
        city = getIntent().getStringExtra("city");

        HashMap<String, String> params = new HashMap<>();
        params.put("dest_id", dest_id);
        String url = Constants.Webserive_Url + "get_attraction_from_destination.php";

        Volley.CallVolley(url, params, "get_attraction_from_destination");

        txt_search = (TextView) findViewById(R.id.btn_search);
        txt_dateTo = (TextView) findViewById(R.id.textView_dateTo);
        txt_dateFrom = (TextView) findViewById(R.id.textView_dateFrom);
        iv_bedsMinus = (ImageView) findViewById(R.id.imageView_bedsMinus);
        iv_bedsPlus = (ImageView) findViewById(R.id.imageView_bedsPlus);
        txt_bedsCount = (TextView) findViewById(R.id.textView_bedsCount);
        iv_adultsPlus = (ImageView) findViewById(R.id.imageView_adultsPlus);
        iv_adultsMinus = (ImageView) findViewById(R.id.imageView_adultsMinus);
        txt_adultsCount = (TextView) findViewById(R.id.textView_adultsCount);
        iv_childrenMinus = (ImageView) findViewById(R.id.imageView_childrenMinus);
        iv_childrenPlus = (ImageView) findViewById(R.id.imageView_childrenPlus);
        txt_childrenCount = (TextView) findViewById(R.id.textView_childrenCount);

        revAttraction = (RecyclerView)findViewById(R.id.revAttraction);

        revAttraction.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));


        txt_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHotelList(v);
            }
        });

        txt_dateTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker(txt_dateTo);
            }
        });

        txt_dateFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker(txt_dateFrom);
            }
        });

        iv_bedsMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrementer(txt_bedsCount);
            }
        });

        iv_bedsPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementer(txt_bedsCount);
            }
        });

        iv_adultsPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementer(txt_adultsCount);
            }
        });

        iv_adultsMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrementer(txt_adultsCount);
            }
        });

        iv_childrenMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrementer(txt_childrenCount);
            }
        });

        iv_childrenPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementer(txt_childrenCount);
            }
        });
    }

    private void showHotelList(View v) {

        Date dateFrom = parseDate(txt_dateFrom.getText().toString());
        Date dateTo = parseDate(txt_dateTo.getText().toString());

        long diff = (long) dateTo.getTime() - dateFrom.getTime();
        int numOfDays = (int) (diff /  (1000 * 60 * 60 * 24));

        Intent i = new Intent(this, HotelListActivity.class);
        i.putExtra("city", city);
        i.putExtra("days", "" + numOfDays);
        i.putExtra("beds", txt_bedsCount.getText().toString());

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

    public Date parseDate(String dateString) {
        try {

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date date = sdf.parse(dateString);
            return date;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
