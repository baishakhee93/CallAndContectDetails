package com.shakhee.contentproviderexample;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SettingFragment extends Fragment {
    CardView out, privacy, update;
    ImageView imageView;
    String name, phone;
    TextView user_name, user_phone;
    FloatingActionButton share;
    Button t;

    public SettingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_setting, container, false);

        share = v.findViewById(R.id.share_app);
        update = v.findViewById(R.id.profile);
        out = v.findViewById(R.id.logout);
        privacy = v.findViewById(R.id.policy);
        user_name = v.findViewById(R.id.user_name);
        user_phone = v.findViewById(R.id.user_phone);
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        name = sharedPreferences.getString("user_name", "Test_name");
        phone = sharedPreferences.getString("user_phone", "0000000000");
        user_name.setText(name);
        user_phone.setText(phone);



        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                    String shareMessage = "\nLet me recommend you this application\n\n";
                    shareMessage = shareMessage + "https://play.google.com/store/apps" + BuildConfig.APPLICATION_ID + "\n\n";
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    startActivity(Intent.createChooser(shareIntent, "choose one"));
                } catch (Exception e) {
                    //e.toString();
                }
            }
        });





        return v;
    }


}
