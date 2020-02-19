package com.test.junket;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.test.junket.Utils.Constants;
import com.test.junket.Utils.DataInterface;
import com.test.junket.Utils.Webservice_Volley;
import com.test.junket.adapters.DestinationListAdapter;
import com.test.junket.models.DestinationListVo;
import com.test.junket.models.DestinationResultVo;
import com.test.junket.models.QuestionnaireResultVo;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Destination_List extends AppCompatActivity implements DataInterface {

    Webservice_Volley Volley = null;


    List<DestinationResultVo> destinationList = new ArrayList<>();

    String tags;

    RecyclerView revDestination;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination__list);


        Volley = new Webservice_Volley(this, this);

        tags = getIntent().getStringExtra("tags");

        revDestination = (RecyclerView)findViewById(R.id.revDestination);

        revDestination.setLayoutManager(new LinearLayoutManager(this));


        String url = Constants.Webserive_Url + "get_destination_from_tag.php";

        HashMap<String, String> params = new HashMap<>();
        params.put("tag", tags);

        Volley.CallVolley(url, params, "get_destination_from_tag");

    }

    @Override
    public void getData(JSONObject jsonObject, String tag) {

        try {

            DestinationListVo destinationListVo = new Gson().fromJson(jsonObject.toString(),DestinationListVo.class);

            if (destinationListVo != null) {

                if (destinationListVo.getResult() != null) {

                    if (destinationListVo.getResult().size() > 0) {

                        DestinationListAdapter adapter = new DestinationListAdapter(this, destinationListVo.getResult());
                        revDestination.setAdapter(adapter);


                    }

                }

            }


        }
        catch (Exception e) {
            e.printStackTrace();
        }



    }
}



