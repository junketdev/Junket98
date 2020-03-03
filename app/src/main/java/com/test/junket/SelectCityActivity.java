package com.test.junket;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.test.junket.Utils.AllSharedPrefernces;
import com.test.junket.Utils.GPSTracker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SelectCityActivity extends BaseActivity implements GPSTracker.GetLocationUpdateListener {

    LinearLayout linear_detectmylocation, linear_myinterest;
    EditText edt_search;
    ListView list_city;
    GPSTracker gpsTracker;

    AllSharedPrefernces allSharedPrefernces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_city);

        allSharedPrefernces = new AllSharedPrefernces(this);

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

        final ArrayAdapter<String> citiesAdapter = new ArrayAdapter<>(
                this, R.layout.simple_list_item_1_white, loadCitiesFromAsset()
        );
        list_city.setAdapter(citiesAdapter);

        list_city.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedCity = parent.getItemAtPosition(position).toString();
                allSharedPrefernces.setSeletedCity(selectedCity);

                Intent i = new Intent(SelectCityActivity.this, HotelSearchActivity.class);
                startActivity(i);

                finish();
            }
        });

        edt_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                if (citiesAdapter != null) {
                    citiesAdapter.getFilter().filter(s.toString());
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
        });
    }

    @Override
    public void getUpdatedLocation(Location location) {
        String city = getCity(location.getLatitude(), location.getLongitude());
        allSharedPrefernces.setSeletedCity(city);

        Intent i = new Intent(this, HotelSearchActivity.class);
        startActivity(i);

        finish();


    }

    public String getCity(double latitude, double longitude) {
        try {
            Geocoder geocoder;
            List<Address> addresses;
            geocoder = new Geocoder(this, Locale.getDefault());

            // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            addresses = geocoder.getFromLocation(latitude, longitude, 1);

            String city = addresses.get(0).getLocality();

//        String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
//        String state = addresses.get(0).getAdminArea();
//        String country = addresses.get(0).getCountryName();
//        String postalCode = addresses.get(0).getPostalCode();
//        String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL

            return city;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public List<String> loadCitiesFromAsset() {
        ArrayList<String> cities = new ArrayList<>();
        try {
            InputStream is = getBaseContext().getAssets().open("cities.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String jsonCities = new String(buffer, "UTF-8");
            JSONObject jsonObject = new JSONObject(jsonCities);
            JSONArray jArray = jsonObject.getJSONArray("cities");
            for (int i = 0; i< jArray.length(); i++) {
                cities.add(jArray.getString(i));
            }
        } catch (IOException | NullPointerException | JSONException e) {
            e.printStackTrace();
        }
        return cities;
    }
}
