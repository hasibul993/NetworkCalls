package com.networkcalls.NetworkMethod;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by PCCS-0007 on 28-Dec-17.
 */


public abstract class Get extends AsyncTask<String, Void, String> {

    private static String TAG = "Get";
    ProgressDialog progressDialog;
    private String url;
    Context context;
    boolean isWaiting;
    private PackageInfo info;


    public Get(Context context, String url, boolean isWaiting) {
        this.url = url;
        this.context = context;
        this.isWaiting = isWaiting;
    }

    @Override
    protected void onPreExecute() {
        // TODO Auto-generated method stub
        super.onPreExecute();
        try {
            if (isWaiting) {
                progressDialog = new ProgressDialog(context);
                progressDialog.setMessage("Loading");
                progressDialog.show();
                progressDialog.setCanceledOnTouchOutside(false);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //  QKLogs.e(TAG, "OnPreExecute");
    }

    @Override
    protected String doInBackground(String... urls) {
        // TODO Auto-generated method stub
        String result = "";
        try {
            result = GET_DATA();
            //   QKLogs.e(TAG, "doInBackground");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return result;
    }


    // onPostExecute displays the results of the AsyncTask.
    @Override
    protected void onPostExecute(String result) {
        try {
            if (progressDialog != null)
                progressDialog.dismiss();
            onResponseReceived(result);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    // Making POST request with given values
    public String GET_DATA() {
        URL url;
        HttpURLConnection conn = null;
        String response = "";
        PackageManager manager = context.getPackageManager();
        try {
            info = manager.getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        try {
            url = new URL(this.url);
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(10000);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");

            conn.connect();
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                String line;
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));
                while ((line = br.readLine()) != null) {
                    response += line;
                }
            } else {
                response = "";

            }
            // conn.disconnect();
        } catch (Exception ex) {
            ex.printStackTrace();
            try {
                Log.i(TAG, "doInBackground error" + ex.getMessage() + " " + ex.getCause() + " " + ex.toString());
                response = ex.toString();
            } catch (Exception er) {
                er.printStackTrace();
            }

        } finally {
            conn.disconnect();
        }

        return response;
    }


    public abstract void onResponseReceived(String result);
}