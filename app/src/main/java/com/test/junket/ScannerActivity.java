package com.test.junket;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.zxing.Result;
import com.squareup.picasso.Picasso;
import com.test.junket.Utils.Constants;
import com.test.junket.Utils.DataInterface;
import com.test.junket.Utils.Webservice_Volley;
import com.test.junket.models.HotelBookingInfo;
import com.test.junket.models.ObjectDetailsResultVo;
import com.test.junket.models.ObjectDetailsVo;

import org.json.JSONObject;

import java.util.HashMap;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScannerActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler, DataInterface {

    private ZXingScannerView mScannerView;
    private String TAG = "SCANNER_TAG";

    Webservice_Volley Volley = null;
    private static final int PERMISSION_REQUEST_CODE = 111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView);

        Volley = new Webservice_Volley(this,this);

    }

    @Override
    public void onResume() {
        super.onResume();

        if (checkPermission()) {
            mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
            mScannerView.startCamera();          // Start camera on resume
        }
        else {
            requestPermission();
        }


    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(Result rawResult) {
        // Do something with the result here
        Log.v(TAG, rawResult.getText()); // Prints scan results
        Log.v(TAG, rawResult.getBarcodeFormat().toString()); // Prints the scan format (qrcode, pdf417 etc.)

        loadData(rawResult.getText());


    }

    public void loadData(String text) {

        String url = Constants.Webserive_Url + "get_object_details.php";
        HashMap<String, String> params = new HashMap<>();
        params.put("object_qr", text);
        Volley.CallVolley(url, params, "get_object_details");

    }

    private boolean checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            return false;
        }
        return true;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.CAMERA},
                PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
                    mScannerView.startCamera();          // Start camera on resume

                    // main logic
                } else {
                    Toast.makeText(getApplicationContext(), "Permission Denied", Toast.LENGTH_SHORT).show();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                                != PackageManager.PERMISSION_GRANTED) {
                            showMessageOKCancel("You need to allow access permissions",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                requestPermission();
                                            }
                                        }
                                    });
                        }
                    }
                }
                break;
        }
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    @Override
    public void getData(JSONObject jsonObject, String tag) {

        try {

            ObjectDetailsVo objectDetailsVo = new Gson().fromJson(jsonObject.toString(), ObjectDetailsVo.class);

            if (objectDetailsVo != null) {

                if (objectDetailsVo.getResult() != null) {

                    if (objectDetailsVo.getResult().size() > 0) {

                        showObjectDialog(objectDetailsVo.getResult().get(0));

                    }
                    else {
                        // If you would like to resume scanning, call this method below:
                        mScannerView.resumeCameraPreview(this);
                    }


                }
                else {
                    // If you would like to resume scanning, call this method below:
                    mScannerView.resumeCameraPreview(this);
                }

            }
            else {
                // If you would like to resume scanning, call this method below:
                mScannerView.resumeCameraPreview(this);
            }

        }
        catch (Exception e) {
            e.printStackTrace();

            mScannerView.resumeCameraPreview(this);
        }

    }

    public void showObjectDialog(ObjectDetailsResultVo resultVo) {

        View vs = LayoutInflater.from(this).inflate(R.layout.dialog_object_details,null);

        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setView(vs);

        ImageView img_object = (ImageView)vs.findViewById(R.id.img_object);
        TextView txt_title = (TextView)vs.findViewById(R.id.txt_title);
        TextView txt_details = (TextView)vs.findViewById(R.id.txt_details);

        if (TextUtils.isEmpty(resultVo.getObjectImage())) {
            img_object.setVisibility(View.GONE);
        }
        else {
            Picasso.get().load(resultVo.getObjectImage()).placeholder(R.drawable.placeholder).into(img_object);
            img_object.setVisibility(View.VISIBLE);
        }

        txt_title.setText(resultVo.getObjectTitle());
        txt_details.setText(resultVo.getObjectDetails());

        AlertDialog ad = adb.create();

        ad.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                mScannerView.resumeCameraPreview(ScannerActivity.this);
            }
        });
        ad.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                mScannerView.resumeCameraPreview(ScannerActivity.this);
            }
        });

        ad.show();

    }
}
