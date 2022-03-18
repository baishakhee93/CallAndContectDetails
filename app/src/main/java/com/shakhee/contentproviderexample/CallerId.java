package com.shakhee.contentproviderexample;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class CallerId  extends Service {


    int LAYOUT_FLAG;
    View mFloatingView;
    TextView Caller_name;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LAYOUT_FLAG = WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY;
        } else {
            LAYOUT_FLAG = WindowManager.LayoutParams.TYPE_PHONE;
        }

        // inflate the view
        mFloatingView = LayoutInflater.from(this).inflate(R.layout.id_viewer, null);

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT, 400, LAYOUT_FLAG, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        // initilize position
        layoutParams.gravity = Gravity.TOP | Gravity.RIGHT;
        layoutParams.x = 250;
        layoutParams.y = 350;

        // make ui for id viewer
        Caller_name = mFloatingView.findViewById(R.id.caller_person);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Caller_name.setText("Baishakhee");
            }
        }, 10);
        // drag moment for widget


        return START_STICKY;
    }


    @Override
    public void onDestroy() {

        super.onDestroy();
    }
}
