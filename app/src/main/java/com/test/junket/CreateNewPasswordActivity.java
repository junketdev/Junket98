package com.test.junket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class CreateNewPasswordActivity extends BaseActivity implements DataInterface {
    EditText edt_newpassword,edt_confirmpassword;
    Button btn_resetmypassword;
    String user_id;


    Webservice_Volley Volley = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_password);

        user_id=getIntent().getStringExtra("id");

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

                String url = Constants.Webserive_Url+ "resetpsw.php";

                HashMap<String,String> params = new HashMap<>();

                params.put("user_password",edt_newpassword.getText().toString());
                params.put("user_id",user_id);

                Volley.CallVolley(url,params,"resetpsw");



            }
        });





    }

    public void clickonresetmypassword(View view) {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }

    @Override
    public void getData(JSONObject jsonObject, String tag) {

        try
        {
            Toast.makeText(this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();

            if (jsonObject.getString("response").equalsIgnoreCase("1"))
            {


                Intent i = new Intent(this, LoginActivity.class);
                startActivity(i);
                finishAffinity();
            }


        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }
}

