package com.test.junket;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.test.junket.Utils.AllSharedPrefernces;
import com.test.junket.Utils.CommonFunctions;
import com.test.junket.Utils.Constants;
import com.test.junket.Utils.DataInterface;
import com.test.junket.Utils.Webservice_Volley;
import com.test.junket.models.ProfileResultVo;
import com.test.junket.models.ProfileVo;

import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;

public class ProfileActivity extends BaseActivity implements DataInterface {

    EditText uname, edt_email, edt_contactno, dob;
    Button btn_save;
    Calendar calendar;
    TextView pass1, edt_gender;
    ImageButton profileimg;
    Webservice_Volley Volley = null;
    boolean isImageFitToScreen;
    ProfileResultVo profileResultVo;

    AllSharedPrefernces allSharedPrefernces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        allSharedPrefernces = new AllSharedPrefernces(this);

        uname = (EditText) findViewById(R.id.uname);
        edt_email = (EditText) findViewById(R.id.edt_email);
        edt_contactno = (EditText) findViewById(R.id.edt_contactno);
        dob = (EditText) findViewById(R.id.dob);
        pass1 = (TextView) findViewById(R.id.pass1);
        edt_gender = (TextView) findViewById(R.id.edt_gender);
        profileimg=(ImageButton) findViewById(R.id.profileimg) ;
        btn_save = (Button) findViewById(R.id.btn_save);
        Volley = new Webservice_Volley(this, this);
        calendar = Calendar.getInstance();


        String url = Constants.Webserive_Url + "get_profile.php";

        HashMap<String, String> params = new HashMap<>();

        params.put("user_id", allSharedPrefernces.getCustomerNo());


        Volley.CallVolley(url, params, "get_profile");

        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(ProfileActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                dob.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                            }
                        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();

            }
        });


                profileimg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(isImageFitToScreen) {
                            isImageFitToScreen=false;
                            profileimg.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                            profileimg.setAdjustViewBounds(true);
                        }else{
                            isImageFitToScreen=true;
                            profileimg.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                            profileimg.setScaleType(ImageButton.ScaleType.FIT_XY);
                        }
                    }
                });



        btn_save.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {


                if (!CommonFunctions.checkString(uname.getText().toString())) {
                    uname.setError("Name cannot be empty.");
                    return;
                }

                if (!CommonFunctions.checkEmail(edt_email.getText().toString())) {
                    edt_email.setError("Enter proper email id.");
                    return;
                }

                if (!CommonFunctions.checkMobileNo(edt_contactno.getText().toString())) {
                    edt_contactno.setError("Enter valid 10 digit phone number.");
                    return;
                }

                if (!CommonFunctions.checkString(dob.getText().toString())) {
                    dob.setError("DOB cannot be empty.");
                    return;
                }


                if (!CommonFunctions.checkPassword(pass1.getText().toString())) {
                    pass1.setError("Password must be 6 char. long.");
                    return;
                }


                String url = Constants.Webserive_Url + "edit_profile.php";

                HashMap<String, String> params = new HashMap<>();

                params.put("user_id", allSharedPrefernces.getCustomerNo());
                params.put("user_name", uname.getText().toString());
                params.put("contact_no", edt_contactno.getText().toString());
                params.put("dob", dob.getText().toString());
                params.put("gender", edt_gender.getText().toString());
                params.put("profile_pic", "");  //  Pass url here to save image of the user.

                Volley.CallVolley(url, params, "edit_profile");
            }
        });

    }

    @Override
    public void getData(JSONObject jsonObject, String tag) {

        try {

           // Toast.makeText(this, jsonObject.toString(), Toast.LENGTH_SHORT).show();

            ProfileVo profileVo = new Gson().fromJson(jsonObject.toString(), ProfileVo.class);

            if (profileVo != null) {
                if (profileVo.getResult() != null) {
                    if (profileVo.getResult().size() > 0) {
                        profileResultVo = profileVo.getResult().get(0);

                        edt_email.setText(profileVo.getResult().get(0).getEmail());
                        uname.setText(profileVo.getResult().get(0).getUserName());
                        pass1.setText(profileVo.getResult().get(0).getUserPassword());
                        edt_contactno.setText(profileVo.getResult().get(0).getContactNo());
                        dob.setText(profileVo.getResult().get(0).getDob());
                        edt_gender.setText(profileVo.getResult().get(0).getGender());
                        Picasso.get().load(profileVo.getResult().get(0).getProfilePic()).into(profileimg);

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}