package com.happykrafts.BroadCastExample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class myBroadastReceiverFromOtherApp extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"BroadCastReceiver Triggered",Toast.LENGTH_LONG).show();
    }
}
