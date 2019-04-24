package com.example.heitzmaa.battleclicker1;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class PageDeGarde extends AppCompatActivity {

    public LocalisationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            //------------------------------------------------------------------------------
            ActivityCompat.requestPermissions(PageDeGarde.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    123);

            return;
        }

        Activity a = PageDeGarde.this;
        this.locationManager = new LocalisationManager(this.getApplicationContext());

        setContentView(R.layout.activity_page_de_garde);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        if(this.locationManager.locationActu != null)
            Log.d("GPS2",this.locationManager.locationActu.getLatitude()+","+this.locationManager.locationActu.getLongitude());
        else
            Log.d("GPS2", "ERREUR LOCATION NULL DE PAGE");
        //new FetchPlace().execute(this.locationManager.locationActu.getLatitude()+","+this.locationManager.locationActu.getLongitude());


    }
    public void onButtonClick(View v){
        Intent myIntent = new Intent(getBaseContext(),   MainActivity.class);
        startActivity(myIntent);
    }
}
