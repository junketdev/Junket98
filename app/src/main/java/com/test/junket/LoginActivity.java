package com.test.junket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.test.junket.Utils.CommonFunctions;
import com.test.junket.Utils.Constants;
import com.test.junket.Utils.DataInterface;
import com.test.junket.Utils.Webservice_Volley;

import org.json.JSONObject;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity implements DataInterface {

    EditText edt_email,edt_password;
    Button btn_login;

    Webservice_Volley Volley = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edt_email = (EditText)findViewById(R.id.edt_email);
        edt_password = (EditText)findViewById(R.id.edt_password);

        btn_login = (Button)findViewById(R.id.btn_login);

        Volley = new Webservice_Volley(this,this);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!CommonFunctions.checkEmail(edt_email.getText().toString())){
                    edt_email.setError("Please Enter Valid Email.");
                    return;
                }

                if (!CommonFunctions.checkPassword(edt_password.getText().toString())){
                    edt_password.setError("Password must be 6 char. long");
                    return;
                }

                String url = Constants.Webserive_Url+ "user_login.php";

                HashMap<String,String> params = new HashMap<>();

                params.put("email",edt_email.getText().toString());
                params.put("user_password",edt_password.getText().toString());

                Volley.CallVolley(url,params,"user_login");


            }
        });

    }

    public void ClickOnSignup(View view) {

        Intent i = new Intent(this,SignupActivity.class);
        startActivity(i);

    }

    @Override
    public void getData(JSONObject jsonObject, String tag) {
//        Log.d("Login", jsonObject.toString());
        try{
            if(jsonObject.getInt("response") == 1) {
                Intent i = new Intent(this,SelectCityActivity.class);
                startActivity(i);
            }
        }
        catch(Exception e)
        {
            Toast.makeText(this, "Login Failed, Try Again !", Toast.LENGTH_LONG).show();
        }

    }


    public void clickOnForgetPassword(View view) {
        Intent i = new Intent(this,Forgot_password.class);
        startActivity(i);
    }
}
