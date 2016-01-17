package com.example.androidversiontest;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tagitdev on 15/1/2016.
 */
public class AddAndroid extends Activity {

    EditText name,url,snippet,carrier;
    String androidname,androidurl,androidsnippet,androidcarrier;
    Button btnAdd;
    Button btnCancel;
    AndroidVersion detail;
    Spinner androidnamespinner;
    String androidid;
    int arraysize =0;
    AndroidVersion androidVersion;
    List<AndroidVersion> androidVersionList;
    String deviceid;
    Integer deviceidInt;
    String deviceidString;
    List<AndroidVersionDetail> allandroiddetaillist = new ArrayList<AndroidVersionDetail>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_android);

        url = (EditText) findViewById(R.id.imageURL);
        name = (EditText) findViewById(R.id.name);
        snippet = (EditText) findViewById(R.id.snippet);
        carrier = (EditText) findViewById(R.id.carrier);
        androidnamespinner = (Spinner) findViewById(R.id.spinner);
        btnAdd = (Button) findViewById(R.id.BtnAdd);
        btnCancel = (Button) findViewById(R.id.btnCancel);

        new AsyncTask<Void, Void, List<AndroidVersionDetail>>()
        {
            @Override
            protected List<AndroidVersionDetail> doInBackground(Void... params) {
                allandroiddetaillist = AndroidVersionDetail.CreateAllAndroidVersionDetail();
                androidVersionList = AndroidVersion.createAndroidVersionList();
                arraysize = AndroidVersion.createAndroidVersionList().size();
                deviceid = String.valueOf(androidVersionList.get(arraysize-1).get("id"));
                return allandroiddetaillist;
            }

            @Override
            protected void onPostExecute(List<AndroidVersionDetail> allandroiddetaillist)
            {
                deviceidInt = Integer.valueOf(deviceid)+1;
                deviceidString = deviceidInt.toString();
                SimpleAdapter simpleAdapter = new SimpleAdapter(getApplicationContext(), allandroiddetaillist, R.layout.androidnamelist,
                        new String[]{"name"}
                        , new int[]{R.id.textView});
                androidnamespinner.setAdapter(simpleAdapter);

                androidnamespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
                    {
                        AndroidVersionDetail androidVersionDetail = (AndroidVersionDetail) parent.getItemAtPosition(pos);
                        androidid = androidVersionDetail.get("id");
                    }


                    //lv.setOnItemClickListener(this);
                    @Override

                    public void onNothingSelected(AdapterView<?> arg0) {
                        // TODO Auto-generated method stub
                    }

                });
            }
        }.execute();



        btnAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                androidname = url.getText().toString();
                androidurl = name.getText().toString();
                androidsnippet = snippet.getText().toString();
                androidcarrier = carrier.getText().toString();
                detail = new AndroidVersion(deviceidString, androidid, androidname, androidurl, androidsnippet, androidcarrier);
                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... params) {

                        AndroidVersion.AddAndroidVersion(detail);
                        return null;
                    }

                }.execute();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                url.setText("");
                name.setText("");
                snippet.setText("");
                carrier.setText("");;
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);

            }
        });

    }
}
