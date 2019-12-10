package com.test.junket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.test.junket.Utils.CommonFunctions;
import com.test.junket.Utils.Constants;
import com.test.junket.Utils.DataInterface;
import com.test.junket.Utils.Webservice_Volley;

import org.json.JSONObject;

public class Create_new_password extends AppCompatActivity implements DataInterface {
    EditText edt_newpassword,edt_confirmpassword;
    Button btn_resetmypassword;


    Webservice_Volley Volley = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_password);

        edt_newpassword = (EditText)findViewById(R.id.edt_newpassword);
        edt_confirmpassword = (EditText)findViewById(R.id.edt_confirmpassword);

        btn_resetmypassword = (Button)findViewById(R.id.btn_resetmypassword);

        Volley = new Webservice_Volley(this,this);

        btn_resetmypassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!CommonFunctions.checkPassword(edt_newpassword.getText().toString())){
                    edt_newpassword.setError("Password must be 6 char. long");
                    return;
                }


                if (!CommonFunctions.checkPassword(edt_confirmpassword.getText().toString())){
                    edt_confirmpassword.setError("Password must be same as new password");
                    return;
                }




            }
        });





    }

    public void clickonresetmypassword(View view) {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }

    @Override
    public void getData(JSONObject jsonObject, String tag) {

    }
}

