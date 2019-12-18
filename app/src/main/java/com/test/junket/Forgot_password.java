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

public class Forgot_password extends AppCompatActivity implements DataInterface {

    EditText verified_email;
    Button btn_submit;
    Webservice_Volley Volley = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        verified_email = (EditText)findViewById(R.id.verified_email);
        btn_submit = (Button)findViewById(R.id.btn_submit);
        Volley = new Webservice_Volley(this,this);

       btn_submit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (!CommonFunctions.checkEmail(verified_email.getText().toString())){
                   verified_email.setError("Please Enter Valid Email.");
                   return;
               }

               String url = Constants.Webserive_Url+ "forgotpsw.php";

               HashMap<String,String> params = new HashMap<>();

               params.put("email",verified_email.getText().toString());

               Volley.CallVolley(url,params,"forgotpsw");
           }
       });
    }
    public void clickonresetpassword(View view) {
        Intent i = new Intent(this, Reset_password.class);
        startActivity(i);
    }

    @Override
    public void getData(JSONObject jsonObject, String tag) {

        try
        {
            Toast.makeText(this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();

            if (jsonObject.getString("response").equalsIgnoreCase("1"))
            {
                String code = jsonObject.getString("verificationcode");
                String id = jsonObject.getString("id");

                Log.d("##MY_CODE","==> "+code);

                Intent i = new Intent(this, Reset_password.class);
                i.putExtra("code",code);
                i.putExtra("id",id);
                startActivity(i);

            }


        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }
}
