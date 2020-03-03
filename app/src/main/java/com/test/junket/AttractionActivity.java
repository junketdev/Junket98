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
import com.squareup.picasso.Picasso;
import com.test.junket.Utils.Constants;
import com.test.junket.Utils.DataInterface;
import com.test.junket.Utils.Webservice_Volley;
import com.test.junket.adapters.AttractionAdapter;
import com.test.junket.adapters.DestinationListAdapter;
import com.test.junket.models.AttractionResultVo;
import com.test.junket.models.AttractionVo;
import com.test.junket.models.DestinationListVo;
import com.test.junket.models.DestinationResultVo;
import com.test.junket.models.QuestionnaireResultVo;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AttractionActivity extends BaseActivity implements DataInterface {

    Webservice_Volley Volley = null;


    List<AttractionResultVo> attractionResultVo = new ArrayList<>();

    String dest_id;

    RecyclerView revAttraction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attraction);


        Volley = new Webservice_Volley(this, this);

        dest_id = getIntent().getStringExtra("city");

        revAttraction = (RecyclerView)findViewById(R.id.revAttraction);

        revAttraction.setLayoutManager(new LinearLayoutManager(this));


        String url = Constants.Webserive_Url + "get_attraction_from_destination.php";

        HashMap<String, String> params = new HashMap<>();
        params.put("city", dest_id);

        Volley.CallVolley(url, params, "get_attraction_from_destination");

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
}



