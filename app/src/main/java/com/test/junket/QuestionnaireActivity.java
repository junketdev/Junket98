package com.test.junket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.test.junket.Utils.Constants;
import com.test.junket.Utils.DataInterface;
import com.test.junket.Utils.Webservice_Volley;
import com.test.junket.adapters.HotelListAdapter;
import com.test.junket.models.HotelInfoVo;
import com.test.junket.models.QuestionListVo;
import com.test.junket.models.QuestionnaireResultVo;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class QuestionnaireActivity extends AppCompatActivity implements DataInterface {

    Webservice_Volley Volley = null;

    TextView txt_question,txt_questionNumber;
    Button btn_continue;
    RadioGroup rg_questions;
    RadioButton rb_option1 ,rb_option2 ,rb_option3 ,rb_option4;

    int count=0;

    List<QuestionnaireResultVo> questionList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);

        txt_question = findViewById(R.id.txt_question);
        txt_questionNumber  = findViewById(R.id.txt_questionNumber);
        btn_continue = findViewById(R.id.btn_continue);
        rg_questions = findViewById(R.id.rg_questions);
        rb_option1 = findViewById(R.id.rb_option1);
        rb_option2 = findViewById(R.id.rb_option2);
        rb_option3 = findViewById(R.id.rb_option3);
        rb_option4 = findViewById(R.id.rb_option4);

        Volley = new Webservice_Volley(this,this);


        String url = Constants.Webserive_Url+ "get_questionnair.php";

        HashMap<String,String> params = new HashMap<>();


        Volley.CallVolley(url,params,"get_questionnair");
        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                setData();
            }
        });

    }

    @Override
    public void getData(JSONObject jsonObject, String tag) {

        try {

            QuestionListVo questionListVo = new Gson().fromJson(jsonObject.toString(),QuestionListVo.class);

            if (questionListVo != null) {

                if (questionListVo.getResult() != null) {

                    if (questionListVo.getResult().size() > 0) {

                    questionList.clear();
                    questionList.addAll(questionListVo.getResult());

                    count=0;
                    setData();

                    }


                }

            }


        }
        catch (Exception e){
            e.printStackTrace();
        }



    }
public void setData()  {

        if ( count >= questionList.size())
        {
            return;
        }

        txt_questionNumber.setText("Question " + (count +1)+ " of " + questionList.size());

        txt_question.setText(questionList.get(count).getQuestion());

        rg_questions.clearCheck();

        if (!TextUtils.isEmpty(questionList.get(count).getOption1())){
            rb_option1.setText(questionList.get(count).getOption1());
            rb_option1.setVisibility(View.VISIBLE);
        }else {
            rb_option1.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(questionList.get(count).getOption2())){
              rb_option2.setText(questionList.get(count).getOption2());
              rb_option2.setVisibility(View.VISIBLE);
        }else {
              rb_option2.setVisibility(View.GONE);
    }

        if (!TextUtils.isEmpty(questionList.get(count).getOption3())){
            rb_option3.setText(questionList.get(count).getOption3());
            rb_option3.setVisibility(View.VISIBLE);
        }else {
            rb_option3.setVisibility(View.GONE);
    }
        if (!TextUtils.isEmpty(questionList.get(count).getOption4())){
            rb_option4.setText(questionList.get(count).getOption4());
            rb_option4.setVisibility(View.VISIBLE);
        }else {
            rb_option4.setVisibility(View.GONE);
    }
    }
}
