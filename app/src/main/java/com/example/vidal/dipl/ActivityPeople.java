package com.example.vidal.dipl;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.vidal.dipl.adapter.CountryAdapter;
import com.example.vidal.dipl.entities.CountryObject;

import java.io.IOException;
import java.util.ArrayList;

public class ActivityPeople  extends AppCompatActivity {
    final Context context = ActivityPeople.this;
    RecyclerView countryRecyclerView;
    CountryAdapter mAdapter;
    ArrayList<CountryObject> countryList = new ArrayList<>();
    String key = "";

    private SQLiteDatabase mDb;
    DataBaseHelper updDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbarCC);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_chevron_left);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        key = intent.getStringExtra("key");
        updDB = new DataBaseHelper(getApplicationContext());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        countryRecyclerView = (RecyclerView)findViewById(R.id.countryCityInfo);
        countryRecyclerView.setLayoutManager(linearLayoutManager);
        countryRecyclerView.setHasFixedSize(true);

        mAdapter = new CountryAdapter(this, getTestData());
        countryRecyclerView.setAdapter(mAdapter);

        getSupportActionBar().setTitle("Колличество людей");

        countryRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(context,  countryRecyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Intent res = new Intent();
                        res.putExtra("key", key);
                        res.putExtra("value", countryList.get(position).getCountrylistTitle());
                        setResult(RESULT_OK, res);
                        finish();

                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // test.setText("");
                    }
                })
        );

    }

    public ArrayList<CountryObject> getTestData() {

        try {
            updDB.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }
        try {
            mDb = updDB.getWritableDatabase();
        } catch (SQLException mSQLException) {
            throw mSQLException;
        }
        //String query = "SELECT country FROM findtours";
        Cursor cursor = mDb.query("people",
                new String[] {"person"},
                null, null, null, null, null);
        cursor.moveToFirst();
        String strPerson = "";
        //String strID = "";
        int intPerson = cursor.getColumnIndex("person");
        //int intID = cursor.getColumnIndex("id");

        while (!cursor.isAfterLast()) {
            strPerson = cursor.getString(intPerson);
            CountryObject countryObject = new CountryObject(strPerson);
            countryList.add(countryObject);
            cursor.moveToNext();
        }

        return countryList;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.ac_bar_country_city, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
