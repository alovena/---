package com.example.serverapp;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Update extends AsyncTask<String,Void,String> {


    @Override
    protected String doInBackground(String... strings) {
        String target="http://cehs0703.cafe24.com/domain_update.php";
        HttpURLConnection urlConnection=null;
        URL url= null;
        Log.d("seo",strings[0]);
        Log.d("seo",strings[1]);
        try {
            url = new URL(target);
            urlConnection=(HttpURLConnection)url.openConnection();
            urlConnection.setReadTimeout(5000);
            urlConnection.setConnectTimeout(5000);
            urlConnection.setRequestMethod("POST");
            urlConnection.connect();
            //urlConnection.setRequestProperty("Content-Type", "application/json");
            String postParameters = "url1=" + strings[1]+"&"+"url2="+strings[0];
            OutputStream outputStream = urlConnection.getOutputStream();
            outputStream.write(postParameters.getBytes("UTF-8"));
            outputStream.flush();
            outputStream.close();

            InputStream is = urlConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer response = new StringBuffer();
            while((line = br.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            br.close();

            String res = response.toString();
            return res;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
