package com.test.junket;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.sasidhar.smaps.payumoney.MakePaymentActivity;
import com.sasidhar.smaps.payumoney.PayUMoney_Constants;
import com.test.junket.Utils.AllSharedPrefernces;
import com.test.junket.Utils.Constants;
import com.test.junket.Utils.DataInterface;
import com.test.junket.Utils.Webservice_Volley;
import com.test.junket.models.HotelBookingInfo;
import com.test.junket.models.HotelResultVo;
import com.test.junket.models.HotelRoomVo;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class  CheckoutActivity extends BaseActivity implements DataInterface {

    TextView txt_room, txt_roomprice, txt_charge, txt_chargeprice, txt_amount, txt_amountprice,txt_pay;
    EditText edt_name,edt_email,edt_contactno;

    HotelBookingInfo bookingInfo;

    HotelRoomVo roomData;
    HotelResultVo hotelData;

    Webservice_Volley Volley = null;
    AllSharedPrefernces allSharedPrefernces;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        allSharedPrefernces = new AllSharedPrefernces(this);

        txt_room = (TextView)findViewById(R.id.txt_room);
        txt_roomprice = (TextView)findViewById(R.id.txt_roomprice);
        txt_charge = (TextView)findViewById(R.id.txt_charge);
        txt_chargeprice = (TextView)findViewById(R.id.txt_chargeprice);
        txt_amount = (TextView)findViewById(R.id.txt_amount);
        txt_amountprice = (TextView)findViewById(R.id.txt_amountprice);
        txt_pay = (TextView)findViewById(R.id.txt_pay);

        edt_name = (EditText) findViewById(R.id.edt_name);
        edt_email = (EditText)findViewById(R.id.edt_email);
        edt_contactno = (EditText)findViewById(R.id.edt_contactno);


        String bookingInfoJson = getIntent().hasExtra("booking_info") ? getIntent().getStringExtra("booking_info") : "";
        bookingInfo = new Gson().fromJson(bookingInfoJson, HotelBookingInfo.class);
        roomData = bookingInfo.getRoomInfo();
        hotelData=bookingInfo.getHotelInfo();

        Volley = new Webservice_Volley(this, this);





        setData();

        txt_pay.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Voice Assistant module
                        String toSpeak = "Generating your Bill "
                                + edt_name.getText().toString();
                        assistance.speak(toSpeak);

                        initPayUMoney();

                    }
                }
        );
    }

    private void addBooking() {
        final String url = Constants.Webserive_Url + "add_booking.php";

        final HashMap<String, String> params = new HashMap<>();

        params.put("user_id",allSharedPrefernces.getCustomerNo());
        params.put("room_id",roomData.getRoomId());
        params.put("hotel_id",hotelData.getHotelierId());
        params.put("booking_id","B"+new SimpleDateFormat("HHmmssSSSS").format(new Date()));
        params.put("user_name",edt_name.getText().toString());
        params.put("destination",hotelData.getCity());
        params.put("contact_no",edt_contactno.getText().toString());
        params.put("email",edt_email.getText().toString());
        params.put("room_price",txt_roomprice.getTag().toString());
        params.put("addcharge_price",txt_chargeprice.getTag().toString());
        params.put("payamount_price",txt_amountprice.getTag().toString());
        params.put("checkin_date",new SimpleDateFormat("yyyy-MM-dd").format(bookingInfo.getDateFrom()));
        params.put("checkout_date",new SimpleDateFormat("yyyy-MM-dd").format(bookingInfo.getDateTo()));
        params.put("adults",""+bookingInfo.getAdults());
        params.put("kids",""+bookingInfo.getChildren());
        params.put("totalnight",""+bookingInfo.getDays());
        params.put("totalrooms",""+bookingInfo.getBeds());

        //Payment Parameters
        params.put("transaction_id",transaction_id);
        params.put("payment_date",new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        params.put("payment_time",new SimpleDateFormat("hh:mm aa").format(new Date()));
        params.put("payment_status","Paid");


        Volley.CallVolley(url, params, "add_booking");
    }

    private void setData() {

        totalCharge(bookingInfo.getDays(), bookingInfo.getBeds(), Integer.parseInt(roomData.getRoomPrice()));

    }

    public double totalCharge(int days, int beds, int roomprice) {
        double amount = days*beds*roomprice;
        double addcharge = (amount)*0.10;
        double payAmount =  amount + addcharge;

        int guests = bookingInfo.getAdults() + bookingInfo.getChildren();
        txt_room.setText(guests + " guest for " + bookingInfo.getDays() + " nights :");
        txt_amountprice.setText(Constants.rupee_code+payAmount);
        txt_amountprice.setTag(""+payAmount);
        txt_chargeprice.setText(Constants.rupee_code+addcharge);
        txt_chargeprice.setTag(""+addcharge);
        txt_roomprice.setText(Constants.rupee_code+amount);
        txt_roomprice.setTag(""+amount);

        return payAmount;
    }

    @Override
    public void getData(JSONObject jsonObject, String tag) {
        Log.d("getData()", "HELLO");

        try {
            if (jsonObject.getString("response").equalsIgnoreCase("1"))
            {
                Intent i = new Intent(CheckoutActivity.this,PaymentConfirmation.class);
                i.putExtra("hotel_name",hotelData.getHotelierName());
                i.putExtra("trans_id",transaction_id);
                startActivity(i);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    String transaction_id;
    public void initPayUMoney() {

        transaction_id = System.currentTimeMillis() + "_" + allSharedPrefernces.getCustomerNo();
        HashMap<String, String> params = new HashMap<>();
        params.put(PayUMoney_Constants.KEY, "rjQUPktU");
        params.put(PayUMoney_Constants.TXN_ID, transaction_id);
        params.put(PayUMoney_Constants.AMOUNT,"1");
        params.put(PayUMoney_Constants.PRODUCT_INFO, "Generate Pass");
        params.put(PayUMoney_Constants.FIRST_NAME, edt_name.getText().toString());
        params.put(PayUMoney_Constants.EMAIL, edt_email.getText().toString());
        params.put(PayUMoney_Constants.PHONE, edt_contactno.getText().toString());

/*        params.put(PayUMoney_Constants.SURL, "http://delta9.in/success.php");
        params.put(PayUMoney_Constants.FURL, "http://delta9.in/fail.php");*/

        params.put(PayUMoney_Constants.SURL, "https://www.payumoney.com/mobileapp/payumoney/success.php");
        params.put(PayUMoney_Constants.FURL, "https://www.payumoney.com/mobileapp/payumoney/failure.php");
        params.put(PayUMoney_Constants.UDF1, "");
        params.put(PayUMoney_Constants.UDF2, "");
        params.put(PayUMoney_Constants.UDF3, "");
        params.put(PayUMoney_Constants.UDF4, "");
        params.put(PayUMoney_Constants.UDF5, "");

        String hash = com.sasidhar.smaps.payumoney.Utils.generateHash(params, "e5iIg1jwi8");

        params.put(PayUMoney_Constants.HASH, hash);
        params.put(PayUMoney_Constants.SERVICE_PROVIDER, "payu_paisa");

        Intent intent = new Intent(this, MakePaymentActivity.class);
        intent.putExtra(PayUMoney_Constants.ENVIRONMENT, PayUMoney_Constants.ENV_DEV);
        intent.putExtra(PayUMoney_Constants.PARAMS, params);

        startActivityForResult(intent, PayUMoney_Constants.PAYMENT_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PayUMoney_Constants.PAYMENT_REQUEST) {
            switch (resultCode) {
                case RESULT_OK:
                    Toast.makeText(CheckoutActivity.this, "Payment Successful", Toast.LENGTH_SHORT).show();

                    //Call API here for adding payment details in DB
                    addBooking();

                    break;
                case RESULT_CANCELED:

                    Intent i = new Intent(CheckoutActivity.this,PaymentFailed.class);
                    i.putExtra("hotel_name",hotelData.getHotelierName());
                    startActivity(i);

                    break;
            }

        }
    }

    public void clickonback(View view) {
        finish();
    }
}
