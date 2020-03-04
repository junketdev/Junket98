package com.test.junket;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.test.junket.Utils.Constants;
import com.test.junket.adapters.HotelRoomAdapter;
import com.test.junket.adapters.HotelRoomAdapter.HotelRoomListOnClickListener;
import com.test.junket.models.HotelBookingInfo;
import com.test.junket.models.HotelResultVo;
import com.test.junket.models.HotelRoomVo;

public class HotelviewActivity extends BaseActivity {
    TextView txt_hotelname, txt_hotelcity, txt_hotelrating, txt_hotelprice;
    LinearLayout linear_call, linear_inquiry, linear_location, linear_share;
    ImageView img_hotel;

    HotelBookingInfo bookingInfo;
    HotelResultVo hotelResultVo;

    RecyclerView recvRoomss;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotelview);

        txt_hotelname = (TextView) findViewById(R.id.txt_hotelname);
        txt_hotelcity = (TextView) findViewById(R.id.txt_hotelcity);
        txt_hotelrating = (TextView) findViewById(R.id.txt_hotelrating);
        txt_hotelprice = (TextView) findViewById(R.id.txt_hotelprice);

        linear_call = (LinearLayout) findViewById(R.id.linear_call);
        linear_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+ hotelResultVo.getContact()));
                startActivity(intent);
            }
        });

        linear_inquiry = (LinearLayout) findViewById(R.id.linear_inquiry);
        linear_inquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                String[] recipients = {hotelResultVo.getHotelierEmail()}; //Add multiple recipients here
                intent.putExtra(Intent.EXTRA_EMAIL, recipients);
                intent.putExtra(Intent.EXTRA_SUBJECT, "Inquiry"); //Add Mail Subject
                intent.setType("text/html");
                intent.setPackage("com.google.android.gm");//Added Gmail Package to forcefully open Gmail App
                startActivity(Intent.createChooser(intent, "Send mail"));
            }
        });

        linear_location = (LinearLayout) findViewById(R.id.linear_location);
        linear_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri mapUri = Uri.parse("geo:0,0?q=" + Uri.encode(hotelResultVo.getHotelierAddress()));
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, mapUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

        linear_share = (LinearLayout) findViewById(R.id.linear_share);
        linear_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "Hey checkout this hotel "+ hotelResultVo.getWebsite());
                startActivity(intent);
            }
        });

        recvRoomss = (RecyclerView) findViewById(R.id.recvRooms);
        recvRoomss.setLayoutManager(new LinearLayoutManager(this));

        ImageView img_hotel = (ImageView) findViewById(R.id.img_hotel);

        String bookingInfoJson = getIntent().hasExtra("booking_info") ? getIntent().getStringExtra("booking_info") : "";
        bookingInfo = new Gson().fromJson(bookingInfoJson, HotelBookingInfo.class);
        hotelResultVo = bookingInfo.getHotelInfo();

        if (hotelResultVo != null) {
            txt_hotelname.setText(hotelResultVo.getHotelierName());
            txt_hotelcity.setText(hotelResultVo.getCity());
            txt_hotelrating.setText(hotelResultVo.getRating());
            txt_hotelprice.setText(Constants.rupee_code + " " + hotelResultVo.getMinPrice() + "/Night");

            Picasso.get().load(hotelResultVo.getImage()).into(img_hotel);

            if (hotelResultVo.getRooms() != null) {
                if (hotelResultVo.getRooms().size() > 0) {

                    HotelRoomAdapter adapter = new HotelRoomAdapter(
                            this,
                            hotelResultVo.getRooms(),
                            new HotelRoomListOnClickListener() {
                                @Override
                                public void onRoomSelected(int position, HotelRoomVo data) {
                                    openCheckoutActivity(data);
                                }
                            }
                    );
                    recvRoomss.setAdapter(adapter);

                }
            }


        }

    }

    public void openCheckoutActivity(HotelRoomVo data) {
        Intent i = new Intent(this, CheckoutActivity.class);
        bookingInfo.setRoomInfo(data);
        i.putExtra("booking_info", new Gson().toJson(bookingInfo));

        //Voice Assistant module
        String toSpeak = "Booking " +
                data.getRoomType()
                + " size Room of "
                +  txt_hotelname.getText().toString();
        assistance.speak(toSpeak);

        startActivity(i);
    }
}
