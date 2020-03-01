package com.test.junket;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.test.junket.Utils.CommonFunctions;
import com.test.junket.Utils.Constants;
import com.test.junket.Utils.DataInterface;
import com.test.junket.Utils.Webservice_Volley;

import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;

public class SignupActivity extends BaseActivity implements DataInterface {

    EditText edt_name, edt_email, edt_contactno,edt_dob,edt_password,edt_confirmpassword;
    Button btn_signup;
    RadioGroup rg_gender;
    Calendar calendar;

    String gender="";
    Webservice_Volley Volley = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        edt_name=(EditText)findViewById(R.id.edt_name);
        edt_email=(EditText)findViewById(R.id.edt_email);
        edt_contactno=(EditText)findViewById(R.id.edt_contactno);
        edt_dob=(EditText)findViewById(R.id.edt_dob);
        edt_password=(EditText)findViewById(R.id.edt_password);
        edt_confirmpassword=(EditText)findViewById(R.id.edt_confirmpassword);
        rg_gender=(RadioGroup)findViewById(R.id.rg_gender);


        btn_signup = (Button)findViewById(R.id.btn_signup);
        Volley = new Webservice_Volley(this,this);
        calendar=Calendar.getInstance();
        edt_dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog= new DatePickerDialog(SignupActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                             edt_dob.setText(year+"-"+(month+1)+"-"+dayOfMonth);
                            }
                        },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();

            }
        });

        rg_gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb=(RadioButton)findViewById(checkedId);
                gender=rb.getText().toString();
            }
        });

        btn_signup.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){


                if(!CommonFunctions.checkString(edt_name.getText().toString())){
                    edt_name.setError("Name cannot be empty.");
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

                if(!CommonFunctions.checkString(edt_dob.getText().toString())){
                    edt_dob.setError("DOB cannot be empty.");
                    return;
                }

                if(!CommonFunctions.checkString(gender)){
                    Toast.makeText(SignupActivity.this, "Please select gender.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!CommonFunctions.checkPassword(edt_password.getText().toString())){
                    edt_password.setError("Password must be 6 char. long.");
                    return;
                }

                if(!edt_password.getText().toString().equals(edt_confirmpassword.getText().toString())){
                    edt_confirmpassword.setError("Both Password does not match.");
                    return;
                }

                String url = Constants.Webserive_Url+ "user_signup.php";

                HashMap<String,String> params = new HashMap<>();

                params.put("user_name",edt_name.getText().toString());
                params.put("email",edt_email.getText().toString());
                params.put("contact_no",edt_contactno.getText().toString());
                params.put("dob",edt_dob.getText().toString());
                params.put("gender",gender);
                params.put("profile_pic","");
                params.put("user_password",edt_password.getText().toString());

                Volley.CallVolley(url,params,"user_signup");
            }
        });

    }

    public void clickonback(View view) {
        finish();
    }

    @Override
    public void getData(JSONObject jsonObject, String tag) {
        Toast.makeText(this, jsonObject.toString(), Toast.LENGTH_SHORT).show();
    }
}
