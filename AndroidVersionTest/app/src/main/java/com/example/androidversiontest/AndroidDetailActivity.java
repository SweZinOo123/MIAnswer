package com.example.androidversiontest;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;

/**
 * Created by tagitdev on 3/11/2015.
 */
public class AndroidDetailActivity extends Activity {


    AndroidVersionDetail androiddetail;
    TextView name,version,codename,target,distribution;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showdetail);
        name = (TextView) findViewById(R.id.textView3);
        version = (TextView) findViewById(R.id.textView6);
        codename = (TextView) findViewById(R.id.textView8);
        target = (TextView) findViewById(R.id.textView10);
        distribution = (TextView) findViewById(R.id.textView12);
        final String id;
        Bundle b = getIntent().getExtras();
        HashMap<String,String> android = (HashMap<String,String>) b.get("android");

        id = android.get("id");

        new AsyncTask<Void, Void, AndroidVersionDetail>()
        {
            @Override
            protected AndroidVersionDetail doInBackground(Void... params) {
                androiddetail =AndroidVersionDetail.CreateAndroidVersionDetail(id);
                return androiddetail;
            }

            @Override
            protected void onPostExecute(final AndroidVersionDetail android)
            {
                name.setText(android.get("name"));
                version.setText(android.get("version"));
                codename.setText(android.get("codename"));
                target.setText(android.get("target"));
                distribution.setText(android.get("distribution"));
            }
        }.execute();

    }
}