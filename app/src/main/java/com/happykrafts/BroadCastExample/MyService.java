package com.happykrafts.BroadCastExample;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("TAG","Current Thread "+Thread.currentThread().getName()+" Id is "+Thread.currentThread().getId());
        Toast.makeText(getBaseContext(),"Current Thread "+Thread.currentThread().getName()+" Id is "+Thread.currentThread().getId(),Toast.LENGTH_LONG).show();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("TAG", "Current Service is Stoped");
        Toast.makeText(getBaseContext(),"Current Thread Stopped",Toast.LENGTH_LONG).show();
    }
}
