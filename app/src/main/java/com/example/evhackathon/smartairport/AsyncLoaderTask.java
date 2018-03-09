package com.example.evhackathon.smartairport.Services;

import android.content.ContentValues;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.example.evhackathon.smartairport.MainActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Varsha on 3/9/2018.
 */

public class AsyncLoaderTask extends AsyncTask<String, Void, String> {

    private static final String TAG = "AsyncLoaderTask";
    private MainActivity mainActivity;
    private HashMap<String, String> wData = new HashMap<>();
    private Bitmap bitmap;

    private final String beaconURL = "http://10.64.99.81:8888/createSession";

    public AsyncLoaderTask(MainActivity ma) {
        mainActivity = ma;
    }

    @Override
    protected void onPostExecute(String s) {
        System.out.print("In post Execute");
//        mainActivity.updateData(wData, bitmap);
    }

    @Override
    protected String doInBackground(String... params) { // 0 == city, 1 == fshrenheit

//        boolean fahrenheit = Boolean.parseBoolean(params[1]);

        Uri.Builder buildURL = Uri.parse(beaconURL).buildUpon();

        buildURL.appendQueryParameter("uuid", "123");
        buildURL.appendQueryParameter("namespace", "1234");
        buildURL.appendQueryParameter("beacon_id", "12345");
        String urlToUse = buildURL.build().toString();
        Log.d(TAG, "doInBackground: " + urlToUse);

//        StringBuilder sb = new StringBuilder();
        try {

            URL url = new URL(beaconURL);
            JSONObject postDataParams = new JSONObject();
            postDataParams.put("uuid", "abc");
            postDataParams.put("namespace", "abc@gmail.com");
            postDataParams.put("beacon_id", "123456");
            Log.e("params",postDataParams.toString());

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

            writer.write(getPostDataString(postDataParams));
            writer.flush();
            writer.close();
            os.close();
            int responseCode=conn.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK) {

                BufferedReader in=new BufferedReader(
                        new InputStreamReader(
                                conn.getInputStream()));
                StringBuffer sb = new StringBuffer("");
                String line="";

                while((line = in.readLine()) != null) {

                    sb.append(line);
                    break;
                }

                in.close();
                System.out.print(sb.toString());

            }
            else {
                Log.d(TAG, "doInBackground: false " + responseCode);
            }


            //GET
//            URL url = new URL(urlToUse);
//
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("POST");
//            InputStream is = conn.getInputStream();
//            BufferedReader reader = new BufferedReader((new InputStreamReader(is)));
//
//            String line;
//            while ((line = reader.readLine()) != null) {
//                sb.append(line).append('\n');
//            }
//
//            Log.d(TAG, "doInBackground: " + sb.toString());


        } catch (Exception e) {
            Log.e(TAG, "exception doInBackground: ", e);
            return null;
        }

//        parseJSON(sb.toString());


        return null;
    }

    public String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;

        Iterator<String> itr = params.keys();

        while(itr.hasNext()){

            String key= itr.next();
            Object value = params.get(key);

            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));

        }
        return result.toString();
    }

    private void parseJSON(String s) {

        try {
            JSONObject jObjMain = new JSONObject(s);

            JSONArray weather = jObjMain.getJSONArray("weather");
            JSONObject jWeather = (JSONObject) weather.get(0);
            wData.put("COND", jWeather.getString("main"));
            wData.put("DESC", jWeather.getString("description"));
            String icon = jWeather.getString("icon");

            JSONObject jMain = jObjMain.getJSONObject("main");
            wData.put("TEMP", jMain.getString("temp"));
            wData.put("HUMID", jMain.getString("humidity"));

            JSONObject jWind = jObjMain.getJSONObject("wind");
            wData.put("WIND", jWind.getString("speed"));

            wData.put("CITY", jObjMain.getString("name"));

            JSONObject jSys = jObjMain.getJSONObject("sys");
            wData.put("COUNTRY", jSys.getString("country"));

            long dt = jObjMain.getLong("dt");
            String date = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a").format(new Date(dt * 1000));
            wData.put("DATE", date);

//            InputStream input = new java.net.URL(iconUrl + icon + ".png").openStream();
//            bitmap = BitmapFactory.decodeStream(input);

            Log.d(TAG, "onPostExecute: " + date);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

