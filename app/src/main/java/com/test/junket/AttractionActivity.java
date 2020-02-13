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

public class AttractionActivity extends AppCompatActivity implements DataInterface {

    Webservice_Volley Volley = null;


    List<AttractionResultVo> attractionResultVo = new ArrayList<>();

    String dest_id;

    RecyclerView revAttraction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attraction);


        Volley = new Webservice_Volley(this, this);

        dest_id = getIntent().getStringExtra("dest_id");

        revAttraction = (RecyclerView)findViewById(R.id.revAttraction);

        revAttraction.setLayoutManager(new LinearLayoutManager(this));


        String url = Constants.Webserive_Url + "get_attraction_from_destination.php";

        HashMap<String, String> params = new HashMap<>();
        params.put("dest_id", dest_id);

        Volley.CallVolley(url, params, "get_attraction_from_destination");

    }

    @Override
    public void getData(JSONObject jsonObject, String tag) {

        try {

            DestinationListVo destinationListVo = new Gson().fromJson(jsonObject.toString(),DestinationListVo.class);

            if (destinationListVo != null) {

                if (destinationListVo.getResult() != null) {

                    if (destinationListVo.getResult().size() > 0) {

                        DestinationListAdapter adapter = new AttractionAdapter(this,attractionResultVo.getResult());
                        revAttraction.setAdapter(adapter);


                    }

                }

            }


        }
        catch (Exception e) {
            e.printStackTrace();
        }



    }
}


