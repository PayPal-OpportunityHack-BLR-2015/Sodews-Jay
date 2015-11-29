package com.example.kamal.donationapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.sinch.android.rtc.Sinch;
import com.sinch.android.rtc.SinchClient;
import com.sinch.android.rtc.messaging.Message;
import com.sinch.android.rtc.messaging.WritableMessage;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    private static final String APP_KEY = "";
    private static final String APP_SECRET = "";
    private static final String ENVIRONMENT = "sandbox.sinch.com";
    private static final String TEST_PHONE_NO =  "+91dummy";

    ImageButton button;ImageButton button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final SinchClient sinchClient = Sinch.getSinchClientBuilder()
                .context(getApplicationContext())
                .userId("current-user-id")
                .applicationKey(APP_KEY)
                .applicationSecret(APP_SECRET)
                .environmentHost(ENVIRONMENT)
                .build();
        sinchClient.setSupportCalling(true);
        sinchClient.start();

        button = (ImageButton) findViewById(R.id.text);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

//                Intent sendIntent = new Intent();
//                sendIntent.putExtra(Intent.EXTRA_TEXT, "Hi, how are doing in school");
//                sendIntent.setType("text/plain");
//                sendIntent.setPackage("com.whatsapp");
//                startActivity(sendIntent);

                Uri uri = Uri.parse("smsto:" + TEST_PHONE_NO);
                Intent i = new Intent(Intent.ACTION_SENDTO, uri);
                i.setPackage("com.whatsapp");
                startActivity(Intent.createChooser(i, ""));
            }
        });


        button = (ImageButton) findViewById(R.id.call);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

//                Intent sendIntent = new Intent();
//                sendIntent.setAction(Intent.ACTION_CALL);
//                //sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
//                //sendIntent.setType("text/plain");
//                sendIntent.setData(Uri.parse("tel:1234"));
//                //sendIntent.setPackage("com.whatsapp");
//                startActivity(sendIntent);

                sinchClient.getCallClient().callPhoneNumber(TEST_PHONE_NO);
                /*sinchClient.getMessageClient().send(new WritableMessage(new Message() {
                    @Override
                    public String getMessageId() {
                        return null;
                    }

                    @Override
                    public Map<String, String> getHeaders() {
                        return null;
                    }

                    @Override
                    public String getTextBody() {
                        return null;
                    }

                    @Override
                    public List<String> getRecipientIds() {
                        return null;
                    }

                    @Override
                    public String getSenderId() {
                        return null;
                    }

                    @Override
                    public Date getTimestamp() {
                        return null;
                    }
                }));*/
            }
        });

        button1 = (ImageButton) findViewById(R.id.info);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent intent = new Intent(MainActivity.this,GetChildInfo.class);
                startActivity(intent);
            }
        });

    }
}
