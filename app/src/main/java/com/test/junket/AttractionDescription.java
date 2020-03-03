package com.test.junket;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.test.junket.Utils.AllSharedPrefernces;
import com.test.junket.Utils.CommonFunctions;
import com.test.junket.Utils.Constants;
import com.test.junket.Utils.DataInterface;
import com.test.junket.Utils.Webservice_Volley;
import com.test.junket.models.AttractionResultVo;
import com.test.junket.models.AttractionVo;
import com.test.junket.models.ProfileVo;

import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;

public class AttractionDescription extends BaseActivity {

    ImageView img_attractionDescription;
    TextView attraction_name, description;

    AllSharedPrefernces allSharedPrefernces;

    AttractionResultVo attraction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attraction_description);

        allSharedPrefernces = new AllSharedPrefernces(this);

        String jsonAttractionVo = getIntent().hasExtra("data") ? getIntent().getStringExtra("data") : "";
        attraction = new Gson().fromJson(jsonAttractionVo, AttractionResultVo.class);
        img_attractionDescription = (ImageView) findViewById(R.id.img_attractionDescription);
        Picasso.get().load(attraction.getAttractionImages()).into(img_attractionDescription);
        attraction_name = (TextView) findViewById(R.id.attraction_name);
        attraction_name.setText(attraction.getAttractionName());
        description = (TextView) findViewById(R.id.description);
        description.setText(attraction.getAttractionDescription());

    }
}