package com.test.junket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.test.junket.Utils.CommonFunctions;
import com.test.junket.Utils.DataInterface;
import com.test.junket.Utils.Webservice_Volley;

import org.json.JSONObject;

public class Reset_password extends AppCompatActivity implements DataInterface {

    EditText edt_code;
    Button btn_verify;

    Webservice_Volley Volley = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        edt_code = (EditText)findViewById(R.id.edt_code);
        btn_verify = (Button)findViewById(R.id.btn_verify);

        Volley = new Webservice_Volley(this,this);

        btn_verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!CommonFunctions.checkPassword(edt_code.getText().toString())){
                    edt_code.setError("Code must be 6 char. long");
                    return;
                }
            }
        });


    }
    public void clickonverify(View view) {
        Intent i = new Intent(this, Create_new_password.class);
        startActivity(i);
    }

    @Override
    public void getData(JSONObject jsonObject, String tag) {

    }
}
