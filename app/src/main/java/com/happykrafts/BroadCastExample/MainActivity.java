package com.happykrafts.BroadCastExample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private BroadcastReceiver broadCastReceiver;
    private Button mBtnTrigger;
    private static String BREAD_CRUMB = "Breadcrumb";
    private static final String TAG = MainActivity.class.getSimpleName();

    private Intent intentService;
    private Button mBtnStartService,mBtnStopService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intentService = new Intent(getApplicationContext(),MyService.class);

        mBtnTrigger = findViewById(R.id.tv_broadCast);
        mBtnStartService = findViewById(R.id.btn_startService);
        mBtnStopService = findViewById(R.id.btn_stopService);

        broadCastReceiver = new myBroadastReceiverFromOtherApp();
        mBtnTrigger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent();
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.setAction("com.br1");
                sendBroadcast(intent);
                sendOrderedBroadcast(intent, null, new BroadcastReceiver() {
                    @Override
                    public void onReceive(Context context, Intent intent) {
                        Bundle bundle = getResultExtras(true);
                        String breadcrumb = bundle.getString(BREAD_CRUMB);
                        breadcrumb = breadcrumb+"->"+TAG;
                        Log.i(TAG,"On Receive : "+breadcrumb);
                        Toast.makeText(getApplicationContext(),breadcrumb,Toast.LENGTH_LONG).show();

                    }
                },null,MainActivity.RESULT_OK,null,null);
            }
        });



        mBtnStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("MainActivity","Current Thread "+Thread.currentThread().getName()+" Thread Id "+Thread.currentThread().getId());
            startService(intentService);
            }
        });


        mBtnStopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(intentService);
            }
        });



    }
    @Override
    public void onStart(){
        super.onStart();

        // This code is used to trigger broadcast reciver and toast if the User get text msg.
        IntentFilter intentFilter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
        registerReceiver(broadCastReceiver,intentFilter);


    }
    @Override
    public void onStop(){
        super.onStop();
        //This is used to unregister the broadcast Receiver
        unregisterReceiver(broadCastReceiver);
    }



}

