package com.test.junket;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.test.junket.Utils.CommonFunctions;
import com.test.junket.Utils.Constants;
import com.test.junket.Utils.DataInterface;
import com.test.junket.Utils.Webservice_Volley;

import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;

public class ProfileActivity extends AppCompatActivity implements DataInterface {

    EditText uname,edt_email,pass1,edt_contactno,dob,edt_gender;
    Button btn_save;
    Calendar calendar;

    Webservice_Volley Volley = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        uname=(EditText)findViewById(R.id.uname);
        edt_email=(EditText)findViewById(R.id.edt_email);
        edt_contactno=(EditText)findViewById(R.id.edt_contactno);
        dob=(EditText)findViewById(R.id.dob);
        pass1=(EditText)findViewById(R.id.pass1);
        edt_gender=(EditText)findViewById(R.id.edt_gender) ;

        btn_save = (Button)findViewById(R.id.btn_save);
        Volley = new Webservice_Volley(this,this);
        calendar=Calendar.getInstance();


        String url = Constants.Webserive_Url+ "get_profile.php";

        HashMap<String,String> params = new HashMap<>();

        params.put("user_id","3");


        Volley.CallVolley(url,params,"get_profile");

        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog= new DatePickerDialog(ProfileActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                dob.setText(year+"-"+(month+1)+"-"+dayOfMonth);
                            }
                        },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();

            }
        });



        btn_save.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){


                if(!CommonFunctions.checkString(uname.getText().toString())){
                    uname.setError("Name cannot be empty.");
                    return;
                }

                if(!CommonFunctions.checkEmail(edt_email.getText().toString())){
                    edt_email.setError("Enter proper email id.");
                    return;
                }

                if(!CommonFunctions.checkMobileNo(edt_contactno.getText().toString())){
                    edt_contactno.setError("Enter valid 10 digit phone number.");
                    return;
                }

                if(!CommonFunctions.checkString(dob.getText().toString())){
                    dob.setError("DOB cannot be empty.");
                    return;
                }


                if(!CommonFunctions.checkPassword(pass1.getText().toString())){
                    pass1.setError("Password must be 6 char. long.");
                    return;
                }


                String url = Constants.Webserive_Url+ "edit_profile.php";

                HashMap<String,String> params = new HashMap<>();

                params.put("user_name",uname.getText().toString());
                params.put("email",edt_email.getText().toString());
                params.put("contact_no",edt_contactno.getText().toString());
                params.put("dob",dob.getText().toString());
                params.put("gender",edt_gender.getText().toString());
                params.put("profile_pic","");
                params.put("user_password",pass1.getText().toString());

                Volley.CallVolley(url,params,"edit_profile");
            }
        });

    }

    public void clickonback(View view) {finish();
    }

    @Override
    public void getData(JSONObject jsonObject, String tag) {
        Toast.makeText(this, jsonObject.toString(), Toast.LENGTH_SHORT).show();
    }
}
