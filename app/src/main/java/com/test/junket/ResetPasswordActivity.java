package com.test.junket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.test.junket.Utils.CommonFunctions;
import com.test.junket.Utils.DataInterface;
import com.test.junket.Utils.Webservice_Volley;

import org.json.JSONObject;

public class ResetPasswordActivity extends AppCompatActivity implements DataInterface {

    EditText edt_code;
    Button btn_verify;
    String id;
    String code;

    Webservice_Volley Volley = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        id = getIntent().getStringExtra("id");
        code=getIntent().getStringExtra("code");

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

                if (code.equals(edt_code.getText().toString()))
                {
                    Intent i = new Intent(ResetPasswordActivity.this, CreateNewPasswordActivity.class);
                    i.putExtra("id",id);
                    startActivity(i);
                }

                else{
                    Toast.makeText(ResetPasswordActivity.this, "Invalid verification code", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    public void clickonverify(View view) {
        Intent i = new Intent(this, CreateNewPasswordActivity.class);
        startActivity(i);
    }

    @Override
    public void getData(JSONObject jsonObject, String tag) {

    }
}
