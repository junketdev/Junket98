package com.test.junket;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.test.junket.Utils.Constants;
import com.test.junket.Utils.DataInterface;
import com.test.junket.Utils.Webservice_Volley;
import com.test.junket.models.DestinationResultVo;
import com.test.junket.models.QuestionnaireResultVo;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Destination_List extends AppCompatActivity implements DataInterface {

    Webservice_Volley Volley = null;


    List<DestinationResultVo> destinationList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination__list);


        Volley = new Webservice_Volley(this,this);


        String url = Constants.Webserive_Url+ "get_destination_from_tag.php";

        HashMap<String,String> params = new HashMap<>();


        Volley.CallVolley(url,params,"get_destination_from_tag");

            }

    @Override
    public void getData(JSONObject jsonObject, String tag) {

    }
}



