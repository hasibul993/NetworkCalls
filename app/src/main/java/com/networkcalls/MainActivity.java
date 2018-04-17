package com.networkcalls;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.networkcalls.Model.RequestModel;
import com.networkcalls.Model.Sample;
import com.networkcalls.NetworkMethod.Get;
import com.networkcalls.NetworkMethod.Post;
import com.networkcalls.Utils.AppConstants;
import com.networkcalls.Utils.Utility;

import static com.networkcalls.Utils.AppConstants.CONNECTION_GONE;
import static com.networkcalls.Utils.AppConstants.INVALID_HOSTNAME;
import static com.networkcalls.Utils.AppConstants.SOCKET_TIMEOUT;

public class MainActivity extends AppCompatActivity implements AppConstants {

    Utility utility = Utility.getInstance();
    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gson = new Gson();

        TextView textV = (TextView) findViewById(R.id.textV);


        textV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    PostMethod(MainActivity.this, false);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

    }


    public void PostMethod(final Context context, final boolean isWaiting) {
        String json, url;
        try {

            RequestModel requestModel = new RequestModel();
            requestModel.userId = "123";
            json = gson.toJson(requestModel);

            url = AppConstants.URL + AppConstants.GET_ACT_DETAILS_URL;

            if (utility.IsInternetConnected(context)) {
                new Post(context, url, json, isWaiting) {
                    @Override
                    public void onResponseReceived(String response) {
                        Sample result;
                        boolean isNeedScreenRewfresh = false;
                        try {
                            if (response.contains(SOCKET_TIMEOUT)) {
                                //String messageBody = context.getString(R.string.internet_slow);
                                //ErrorDialog(context, activityGuid, messageBody, screenName, false);
                            } else if (response.contains(INVALID_HOSTNAME) || response.contains(CONNECTION_GONE)) {
                                // String messageBody = context.getString(R.string.internet_gone);
                                // ErrorDialog(context, activityGuid, messageBody, screenName, false);
                            } else {
                                result = gson.fromJson(response, Sample.class);

                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }.execute();
            } else
                Toast.makeText(context, "Check your internet", Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void GetMethod(final Context context) {
        String json, url;
        PackageInfo info = null;

        try {

            try {
                PackageManager manager = context.getPackageManager();
                info = manager.getPackageInfo(context.getPackageName(), 0);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }

            url = AppConstants.URL + "?versionNumber=" + info.versionCode + "&deviceType=Android" + "&lastReadTimeInTics=0";

            new Get(context, url, false) {

                @Override
                public void onResponseReceived(String response) {
                    try {
                        Sample result = new Sample();

                        if (response.contains(SOCKET_TIMEOUT)) {
                            //String messageBody = context.getString(R.string.internet_slow);
                            //ErrorDialog(context, activityGuid, messageBody, screenName, false);
                        } else if (response.contains(INVALID_HOSTNAME) || response.contains(CONNECTION_GONE)) {
                            // String messageBody = context.getString(R.string.internet_gone);
                            // ErrorDialog(context, activityGuid, messageBody, screenName, false);
                        } else {
                            result = gson.fromJson(response, Sample.class);

                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }


                }
            }.execute();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
