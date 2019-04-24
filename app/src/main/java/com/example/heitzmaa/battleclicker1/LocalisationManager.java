package com.example.heitzmaa.battleclicker1;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import java.util.ArrayList;

public class LocalisationManager {

    LocationManager locationManager;
    private Context context;
    private ArrayList<LocationProvider> providers;
    public Location locationActu;
    static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;

    public boolean testLoca = false;

    public LocalisationManager(Context c) {

        Log.d("test","testtest1");
        this.context = c.getApplicationContext();
        this.locationManager = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
        Log.d("test","testtest2");
        this.providers = new ArrayList<LocationProvider>();

        ArrayList<String> names = (ArrayList<String>) locationManager.getProviders(true);

        for (String name : names)
            providers.add(locationManager.getProvider(name));
        Log.d("test","testtest3");
        Criteria critere = new Criteria();

        // Pour indiquer la précision voulue
        // On peut mettre ACCURACY_FINE pour une haute précision ou ACCURACY_COARSE pour une moins bonne précision
        critere.setAccuracy(Criteria.ACCURACY_FINE);
        // Est-ce que le fournisseur doit être capable de donner une altitude ?
        critere.setAltitudeRequired(true);
        // Est-ce que le fournisseur doit être capable de donner une direction ?
        critere.setBearingRequired(true);
        // Est-ce que le fournisseur peut être payant ?
        critere.setCostAllowed(false);
        // Pour indiquer la consommation d'énergie demandée
        // Criteria.POWER_HIGH pour une haute consommation, Criteria.POWER_MEDIUM pour une consommation moyenne et Criteria.POWER_LOW pour une basse consommation
        critere.setPowerRequirement(Criteria.POWER_HIGH);
        // Est-ce que le fournisseur doit être capable de donner une vitesse ?
        critere.setSpeedRequired(true);

        if (ActivityCompat.checkSelfPermission(this.context, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this.context, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            //------------------------------------------------------------------------------


            return;
        }
        else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 60000, 150, new LocationListener() {


                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {


                }


                @Override
                public void onProviderEnabled(String provider) {


                }


                @Override
                public void onProviderDisabled(String provider) {


                }


                @Override
                public void onLocationChanged(Location location) {

                    Log.d("GPS", "Latitude " + location.getLatitude() + " et longitude " + location.getLongitude());
                    locationActu = location;
                    testLoca = true;
                    //Log.d("GPS2",locationActu.getLatitude()+","+locationActu.getLongitude());
                }

            });

        }


    }


    //Requete
    //https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=45.9192501,6.1598421&radius=1500&key=AIzaSyCCILyqPJAPGG9Py-0gg0e_JUJacXxzRPo

}
