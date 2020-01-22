package com.test.junket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.test.junket.Utils.DataInterface;
import com.test.junket.Utils.Webservice_Volley;

import org.json.JSONObject;

import java.util.HashMap;

public class HotelListActivity extends AppCompatActivity implements DataInterface {

    LinearLayout layout1;

    Webservice_Volley Volley = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_list);

        Volley = new Webservice_Volley(this,this);


        layout1 = (LinearLayout)findViewById(R.id.layout1);
        layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                HashMap<String,String> params = new HashMap<>();

               // params.put("email",edt_email.getText().toString());
                //params.put("user_password",edt_password.getText().toString());

//                Volley.CallVolley(url,params,"user_login");

                Intent i = new Intent(HotelListActivity.this,HotelviewActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void getData(JSONObject jsonObject, String tag) {

    }
}
