package com.example.serverapp;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Select extends AsyncTask<Void, Void, String> {
    @Override
    protected String doInBackground(Void... voids) {
        String url = "https://cehs0703.cafe24.com/domain2.php";
        try {
            HttpURLConnection conn = null;
            URL obj = new URL(url);
            conn = (HttpURLConnection) obj.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json");
            //int retCode = conn.getResponseCode();

            InputStream is = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = br.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            br.close();
            conn.disconnect();
            String res = response.toString();
            Log.d("seo", res);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONArray jsonArray = jsonObject.getJSONArray("response");
            int count = 0;
            while (count < jsonArray.length()) {
                JSONObject Object = jsonArray.getJSONObject(count);
                String str = Object.getString("url");
                //list.add(str);
                //adapter.notifyDataSetChanged();
                count++;

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}