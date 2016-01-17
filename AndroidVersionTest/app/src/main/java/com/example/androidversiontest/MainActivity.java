package com.example.androidversiontest;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import com.example.androidversiontest.RecyclerViewClickListener;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerViewClickListener{

    public static List<AndroidVersion> androidlist=null;
    DummyCardAdapter adapter;
    RecyclerView mRecyclerView;
    Button btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = (Button) findViewById(R.id.btnadd);
        //get data from server and show data in recycler view
        mRecyclerView = (RecyclerView) findViewById(R.id.rvDummyCards);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        new AsyncTask<Void, Void, List<AndroidVersion>>()
        {
            @Override
            protected List<AndroidVersion> doInBackground(Void... params) {
                androidlist =AndroidVersion.createAndroidVersionList();
                return androidlist;
            }

            @Override
            protected void onPostExecute(final List<AndroidVersion> androidlist)
            {
                adapter = new DummyCardAdapter(androidlist);
                mRecyclerView.setAdapter(adapter);

            }
        }.execute();

        adapter = new DummyCardAdapter(MainActivity.this, this);
        //add new android
        btnAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),AddAndroid.class);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void recyclerViewListClicked(View v, int position) {

    }
}
