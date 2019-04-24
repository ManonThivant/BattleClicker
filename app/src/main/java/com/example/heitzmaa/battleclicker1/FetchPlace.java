package com.example.heitzmaa.battleclicker1;

import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

public class FetchPlace extends AsyncTask<String, Void, String> {

    private WeakReference<TextView> mNameText;
    private WeakReference<TextView> mTypeText;

    FetchPlace() {
        //this.mNameText = new WeakReference<>(nameText);
        //this.mTypeText = new WeakReference<>(typeText);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONArray itemsArray = jsonObject.getJSONArray("items");
            int i = 0;
            String name = null;
            String types = null;

            while (i < itemsArray.length() &&
                    (types == null && name == null)) {
                // Get the current item information.
                JSONObject place = itemsArray.getJSONObject(i);
                JSONObject volumeInfo = place.getJSONObject("volumeInfo");

                // Try to get the author and title from the current item,
                // catch if either field is empty and move on.
                try {
                    name = volumeInfo.getString("name");
                    types = volumeInfo.getString("types");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // Move to the next item.
                i++;
            }

            if (name != null && types != null) {
                mNameText.get().setText(name);
                mTypeText.get().setText(types);
            } else {
                mNameText.get().setText("Error");
                mTypeText.get().setText("");
            }


        } catch (JSONException e) {
            // If onPostExecute does not receive a proper JSON string,
            // update the UI to show failed results.
            mNameText.get().setText("Error");
            mTypeText.get().setText("");
            e.printStackTrace();
        }
    }

    @Override
    protected String doInBackground(String... strings) {
        return NetworkUtils.getPlaceInfo(strings[0]);
    }
}