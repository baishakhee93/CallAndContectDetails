package com.shakhee.contentproviderexample;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.CallLog;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class MainFragment extends Fragment {

    static CallRecieveAdapter callRvAdapter;
    private RecyclerView recyclerView;
    FloatingActionButton dial;


    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        dial = v.findViewById(R.id.dial);
        recyclerView = v.findViewById(R.id.rv_calls);

        dial.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {

            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        RecyclerView.LayoutManager layoutManager = linearLayoutManager;
        recyclerView.setLayoutManager(layoutManager);
        callRvAdapter = new CallRecieveAdapter(getContext(), getCallLogs());
        recyclerView.setAdapter(callRvAdapter);

        return v;
    }

    private List<CallLogModel> getCallLogs() {
        List<CallLogModel> list = new ArrayList<>();

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_CALL_LOG}, 1);
        }
        Cursor cursor = getContext().getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null, null, CallLog.Calls.DATE + " ASC");
        int name = cursor.getColumnIndex(CallLog.Calls.CACHED_NAME);
        int number = cursor.getColumnIndex(CallLog.Calls.NUMBER);
        int duration = cursor.getColumnIndex(CallLog.Calls.DURATION);
        int date = cursor.getColumnIndex(CallLog.Calls.DATE);
        cursor.moveToFirst();
        while (cursor.moveToNext()) {
            String Name = cursor.getString(name);
            Date date1 = new Date(Long.valueOf(cursor.getString(date)));
            long duration1 = 4 * 60 * 60 * 1000;
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss ", Locale.getDefault());
            String dur = sdf.format(new Date(duration1 - TimeZone.getDefault().getRawOffset()));
            Log.d("Duration: " + sdf.format(new Date(duration1 - TimeZone.getDefault().getRawOffset())), cursor.getString(duration));

            list.add(new CallLogModel(Name, cursor.getString(number), dur, date1.toString()));
            Log.d("Mic ::", cursor.getString(number));
        }
        return list;
    }

}