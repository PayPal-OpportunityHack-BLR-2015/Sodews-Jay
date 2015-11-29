package com.example.kamal.donationapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.opengl.GLException;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Arrays;

import com.google.gson.Gson;
import com.loopj.android.http.*;
import com.sinch.android.rtc.Sinch;
import com.sinch.android.rtc.SinchClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by kamal on 28/11/15.
 */
public class GetChildInfo extends Activity{
    ImageButton b1;
    ImageButton b2;
    ImageButton b3;
    ImageButton b4;
    ImageButton foodb;ImageButton foodb2;ImageButton foodb3;ImageButton foodb4;
    private static final String APP_KEY = "";
    private static final String APP_SECRET = "";
    private static final String ENVIRONMENT = "sandbox.sinch.com";
    private static final String TEST_PHONE_NO =  "+919483709867";


    public void getData(String name) throws JSONException {
        AsyncClient.get(name, null, new TextHttpResponseHandler() {

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                System.out.println(responseString);
                Log.e("err",responseString.toString());
                try{
                    JSONObject obj = new JSONObject(responseString);
                    System.out.println(obj.get("name"));
                    Toast.makeText(GetChildInfo.this,obj.get("name").toString(),Toast.LENGTH_SHORT).show();
                }catch(Exception e){

                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_childinfo);

        final SinchClient sinchClient = Sinch.getSinchClientBuilder()
                .context(getApplicationContext())
                .userId("current-user-id")
                .applicationKey(APP_KEY)
                .applicationSecret(APP_SECRET)
                .environmentHost(ENVIRONMENT)
                .build();

        sinchClient.setSupportCalling(true);
        sinchClient.start();

        //2nd
        b1 = (ImageButton) findViewById(R.id.imageButton);
        b2 = (ImageButton) findViewById(R.id.imageButton2);
        b3 = (ImageButton) findViewById(R.id.imageButton5);

        //1st
        b4 = (ImageButton) findViewById(R.id.imageButton4);


        foodb = (ImageButton) findViewById(R.id.imageButton3);
        foodb2 = (ImageButton) findViewById(R.id.imageButton6);
        foodb3 = (ImageButton) findViewById(R.id.imageButton7);
        foodb4 = (ImageButton) findViewById(R.id.imageButton8);


        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                sinchClient.getCallClient().callPhoneNumber(TEST_PHONE_NO);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                sinchClient.getCallClient().callPhoneNumber("+");
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                sinchClient.getCallClient().callPhoneNumber("+");
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                sinchClient.getCallClient().callPhoneNumber("+");
            }
        });

        foodb.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent launch = new Intent(GetChildInfo.this,FoodCharts.class);
                startActivity(launch);
            }
        });
        foodb2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent launch = new Intent(GetChildInfo.this,FoodCharts.class);
                startActivity(launch);
            }
        });
        foodb3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent launch = new Intent(GetChildInfo.this,FoodCharts.class);
                startActivity(launch);
            }
        });
        foodb4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent launch = new Intent(GetChildInfo.this,FoodCharts.class);
                startActivity(launch);
            }
        });
    }

}


