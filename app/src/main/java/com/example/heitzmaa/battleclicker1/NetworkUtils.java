package com.example.heitzmaa.battleclicker1;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkUtils {
    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();


    //https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=45.9192501,6.1598421&radius=1500&key=AIzaSyCCILyqPJAPGG9Py-0gg0e_JUJacXxzRPo

    // Base URL for Books API.
    private static final String PLACE_BASE_URL =  "https://maps.googleapis.com/maps/api/place/nearbysearch/json?";
    // Parameter for the search string.
    private static final String QUERY_PARAM = "location";
    // Parameter that limits search results.
    private static final String MAX_RESULTS = "radius";
    // Parameter to filter by print type.
    private static final String PRINT_TYPE = "key";

    private static final String API_KEY = "AIzaSyCCILyqPJAPGG9Py-0gg0e_JUJacXxzRPo";




    static String getPlaceInfo(String queryString){
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String placeJSONString = null;
        Uri builtURI = Uri.parse(PLACE_BASE_URL).buildUpon()
                .appendQueryParameter(QUERY_PARAM, queryString)         //Ajoute le parametre du livre
                .appendQueryParameter(MAX_RESULTS, "10")
                .appendQueryParameter(PRINT_TYPE, "books")
                .build();

        try {
            URL requestURL = new URL(builtURI.toString());

            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Get the InputStream.
            InputStream inputStream = urlConnection.getInputStream();

            // Create a buffered reader from that input stream.
            reader = new BufferedReader(new InputStreamReader(inputStream));

            // Use a StringBuilder to hold the incoming response.
            StringBuilder builder = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                // Since it's JSON, adding a newline isn't necessary (it won't
                // affect parsing) but it does make debugging a *lot* easier
                // if you print out the completed buffer for debugging.
                builder.append("\n");
            }

            if (builder.length() == 0) {
                // Stream was empty. No point in parsing.
                return null;
            }
            placeJSONString = builder.toString();



        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        Log.d(LOG_TAG, placeJSONString);
        return placeJSONString;
    }

}
