package com.xapo.xapogithubtest.view.activity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Check the network connectivity
     *
     * @param context
     * @return
     */
    public boolean isConnected(Context context) {
        ConnectivityManager mgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = mgr.getActiveNetworkInfo();

        if (netInfo != null) {
            if (netInfo.isConnected()) {
                // Internet Available
                Log.e("Connectivity >> ", netInfo.getDetailedState().toString());

                if (!netInfo.getDetailedState().equals(NetworkInfo.DetailedState.CONNECTED)) {
                    return false;

                } else {
                    return true;
                }
            } else {
                //No internet
                return false;
            }
        } else {
            return false;
        }
    }

}
