package com.shakhee.contentproviderexample;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;


import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

public class MainActivity extends AppCompatActivity {

    // initilize the variable
    MeowBottomNavigation bottomNavigation;
    @SuppressLint("StaticFieldLeak")
    static Activity MA;
    static final int REQUEST_CODE =123;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MA=this;
        getPermission();

        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_PHONE_STATE) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                    Manifest.permission.READ_PHONE_STATE}, 1);
        }
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)){
            Intent intent= new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:"+getPackageName()));
            startActivityForResult(intent,1);
        }

        //assign variable
        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_baseline_call_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.ic_baseline_contacts_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.ic_baseline_settings_24));

        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                //initilize fragment
                Fragment fragment = null;

                switch (item.getId()) {
                    case 1:
                        fragment = new MainFragment();
                        break;
                    case 2:
                        fragment = new ContactFragment();
                        break;
                    case 3:
                        fragment = new SettingFragment();
                        break;
                }
                loadFragment(fragment);
            }
        });
        //set home fragment selected initially
        bottomNavigation.show(1, true);

        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                //Toast.makeText(getApplicationContext(),"you clicked"+item.getId(),Toast.LENGTH_LONG).show();
            }
        });
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, fragment).commit();
    }


    private void getPermission() {

        if (ContextCompat.checkSelfPermission(MA, Manifest.permission.CAMERA)+
                ContextCompat.checkSelfPermission(MA, Manifest.permission.CALL_PHONE)+
                ContextCompat.checkSelfPermission(MA, Manifest.permission.READ_CONTACTS)+
                ContextCompat.checkSelfPermission(MA, Manifest.permission.READ_SMS)+
                ContextCompat.checkSelfPermission(MA, Manifest.permission.READ_PHONE_NUMBERS)+
                ContextCompat.checkSelfPermission(MA, Manifest.permission.READ_PHONE_STATE)+
                ContextCompat.checkSelfPermission(MA, Manifest.permission.READ_CALL_LOG)+
                ContextCompat.checkSelfPermission(MA, Manifest.permission.READ_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {
            //when Permission not granted

            if (ActivityCompat.shouldShowRequestPermissionRationale(MA, Manifest.permission.CAMERA) ||
                    ActivityCompat.shouldShowRequestPermissionRationale(MA, Manifest.permission.READ_CONTACTS) ||
                    ActivityCompat.shouldShowRequestPermissionRationale(MA, Manifest.permission.READ_SMS) ||
                    ActivityCompat.shouldShowRequestPermissionRationale(MA, Manifest.permission.READ_PHONE_NUMBERS) ||
                    ActivityCompat.shouldShowRequestPermissionRationale(MA, Manifest.permission.READ_PHONE_STATE) ||
                    ActivityCompat.shouldShowRequestPermissionRationale(MA, Manifest.permission.READ_CALL_LOG) ||
                    ActivityCompat.shouldShowRequestPermissionRationale(MA, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MA);
                builder.setTitle("Grand Those Permission");
                builder.setMessage("Camera, Read Contect, Read Phone Number, Read SMS and Read Storage");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        ActivityCompat.requestPermissions(MA, new String[]{
                                        Manifest.permission.CAMERA,
                                        Manifest.permission.READ_CONTACTS,
                                        Manifest.permission.CALL_PHONE,
                                        Manifest.permission.READ_SMS,
                                        Manifest.permission.READ_PHONE_NUMBERS,
                                        Manifest.permission.READ_PHONE_STATE,
                                        Manifest.permission.READ_EXTERNAL_STORAGE,
                                        Manifest.permission.READ_CALL_LOG
                                },
                                REQUEST_CODE
                        );

                    }
                });
                builder.setNegativeButton("Cancel", null);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            } else {
                ActivityCompat.requestPermissions(MA, new String[]{
                                Manifest.permission.CAMERA,
                                Manifest.permission.READ_CONTACTS,
                                Manifest.permission.READ_SMS,
                                Manifest.permission.CALL_PHONE,
                                Manifest.permission.READ_PHONE_NUMBERS,
                                Manifest.permission.READ_PHONE_STATE,
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.READ_CALL_LOG
                        },
                        REQUEST_CODE
                );
            }

        }else {
            // when permission are already granted
            Toast.makeText(getApplicationContext(),"Permission Already Ganted...",Toast.LENGTH_SHORT);
        }
    }

    private void checkPermission() {
        Intent intent=new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri=Uri.fromParts("package",getPackageName(),null);
        intent.setData(uri);
        startActivity(intent);
    }
}