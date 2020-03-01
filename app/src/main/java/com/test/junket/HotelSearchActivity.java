package com.test.junket;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.test.junket.Utils.AllSharedPrefernces;
import com.test.junket.Utils.Constants;
import com.test.junket.Utils.DataInterface;
import com.test.junket.Utils.Webservice_Volley;
import com.test.junket.adapters.AttractionAdapter;
import com.test.junket.models.AttractionVo;
import com.test.junket.models.HotelBookingInfo;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class HotelSearchActivity extends BaseActivity
        implements DataInterface, NavigationView.OnNavigationItemSelectedListener {

    Webservice_Volley Volley = null;

    TextView txt_search, txt_dateFrom, txt_dateTo, txt_bedsCount, txt_adultsCount, txt_childrenCount;

    TextView txt_name;
    ImageView iv_profileImage;

    ImageView iv_bedsMinus, iv_bedsPlus, iv_adultsPlus, iv_adultsMinus, iv_childrenMinus, iv_childrenPlus;

    DrawerLayout drawerLayout;

    NavigationView navigation_view;

    TextView toolbar_location_name;

    Toolbar toolbar_search;

    RecyclerView revAttraction;

    String dest_id, city;

    AllSharedPrefernces allSharedPrefernces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_search);

        Volley = new Webservice_Volley(this, this);

        allSharedPrefernces = new AllSharedPrefernces(this);

        city = allSharedPrefernces.getSeletedCity();

        dest_id = getIntent().hasExtra("dest_id") ? getIntent().getStringExtra("dest_id") : "";

        HashMap<String, String> params = new HashMap<>();
        params.put("city", city);
        String url = Constants.Webserive_Url + "get_attraction_from_destination.php";

        Volley.CallVolley(url, params, "get_attraction_from_destination");

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        toolbar_search = (Toolbar) findViewById(R.id.toolbar_search);

        setupNavigationDrawer();

        toolbar_location_name = (TextView) findViewById(R.id.toolbar_location_name);
        toolbar_location_name.setText(allSharedPrefernces.getSeletedCity());

        navigation_view = (NavigationView) findViewById(R.id.navigation_view);
        View drawerHeader = LayoutInflater.from(this).inflate(R.layout.layout_header_drawer, null, false);
        navigation_view.addHeaderView(drawerHeader);

        navigation_view.setNavigationItemSelectedListener(this);

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

        txt_name = (TextView) findViewById(R.id.txt_name);
        iv_profileImage = (ImageView) findViewById(R.id.iv_profileImage);

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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Boolean result = false;
        switch (menuItem.getItemId()) {
            case R.id.menu_item_my_bookings :

                Intent i = new Intent(this,MyBookingActivity.class);
                startActivity(i);


                result = true;
                break;

            case R.id.menu_item_profile :
                Toast.makeText(this, "Profile", Toast.LENGTH_LONG).show();
                result = true;
                break;

            case R.id.menu_item_logout :
                Toast.makeText(this, "Logout", Toast.LENGTH_LONG).show();
                result = true;
                break;
        }
        return result;
    }

    private void setupNavigationDrawer() {
        toolbar_search.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerOpen(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.START);
                else
                    drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void showHotelList(View v) {
        Date dateFrom = parseDate(txt_dateFrom.getText().toString());
        Date dateTo = parseDate(txt_dateTo.getText().toString());

        Intent i = new Intent(this, HotelListActivity.class);
        HotelBookingInfo bookingInfo = new HotelBookingInfo(
                dest_id,
                dateFrom,
                dateTo,
                Integer.parseInt(txt_bedsCount.getText().toString()),
                Integer.parseInt(txt_adultsCount.getText().toString()),
                Integer.parseInt(txt_childrenCount.getText().toString()),
                city
        );
        i.putExtra("booking_info", new Gson().toJson(bookingInfo));

        String toSpeak = "Searching hotels from " +
                txt_dateFrom.getText().toString() +
                " to " +
                txt_dateTo.getText().toString();

        assistance.speak(toSpeak);

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
