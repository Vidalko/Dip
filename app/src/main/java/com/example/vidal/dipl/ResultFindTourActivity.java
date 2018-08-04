package com.example.vidal.dipl;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.example.vidal.dipl.adapter.ResultFindAdapter;
import com.example.vidal.dipl.entities.ResultFindObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


/**
 * Created by Vidal on 01.03.2018.
 */

public class ResultFindTourActivity extends AppCompatActivity {
    final Context context = ResultFindTourActivity.this;
    RecyclerView playlisRecyclerView;
    ResultFindAdapter mAdapter;
    ArrayList<ResultFindObject> countryList = new ArrayList<>();
    String key = "";

    String tableName= "";

    Boolean boolSort = false;
    Boolean boolSortCity = true;

    private SQLiteDatabase mDb;
    DataBaseHelper updDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_find_tour);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbarResFind);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_chevron_left);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        key = intent.getStringExtra("key");
        updDB = new DataBaseHelper(getApplicationContext());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        playlisRecyclerView = (RecyclerView)findViewById(R.id.tourList);
        playlisRecyclerView.setLayoutManager(linearLayoutManager);
        playlisRecyclerView.setHasFixedSize(true);

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

        mAdapter = new ResultFindAdapter(this, getTestData());
        playlisRecyclerView.setAdapter(mAdapter);

        LinearLayout lnSortByPrice = (LinearLayout) findViewById (R.id.sortByPrice);
        lnSortByPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(boolSort) {
                    sortByPrice();
                    boolSort = false;
                }
                else {
                    sortByPrice2();
                    boolSort = true;
                }
            }
        });

        LinearLayout lnSortByCity = (LinearLayout) findViewById (R.id.sortByCity);
        lnSortByCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(boolSortCity) {
                    sortByCity();
                    boolSortCity = false;
                }
                else {
                    sortByCity2();
                    boolSortCity = true;
                }
            }
        });

        playlisRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(context,  playlisRecyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {

                        Intent intent = new Intent(context, SelectedTourActivity.class);
                        ResultFindObject resObject = countryList.get(position);

                        intent.putExtra("Pos", resObject.getTourID());
                        intent.putExtra("Date", resObject.getTourDate());
                        intent.putExtra("Duration", resObject.getTourDuration());
                        intent.putExtra("TableName", tableName);
                        mDb.close();

                        startActivity(intent);

                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // test.setText("");
                    }
                })
        );

    }

    private void sortByCity()
    {

        Collections.sort(countryList, new Comparator<ResultFindObject>() {
            public int compare(ResultFindObject result1, ResultFindObject result2) {
                return result1.getTourCity().compareTo(result2.getTourCity());
            }
        });
        mAdapter.notifyDataSetChanged();

    }
    private void sortByCity2()
    {

        Collections.sort(countryList, new Comparator<ResultFindObject>() {
            public int compare(ResultFindObject result1, ResultFindObject result2) {
                return result2.getTourCity().compareTo(result1.getTourCity());
            }
        });
        mAdapter.notifyDataSetChanged();

    }
    private void sortByPrice()
    {

        Collections.sort(countryList, new Comparator<ResultFindObject>() {
            public int compare(ResultFindObject result1, ResultFindObject result2) {
                return result1.getTourPrice().compareTo(result2.getTourPrice());
            }
        });
        mAdapter.notifyDataSetChanged();

    }
    private void sortByPrice2()
    {

        Collections.sort(countryList, new Comparator<ResultFindObject>() {
            public int compare(ResultFindObject result1, ResultFindObject result2) {
                return result2.getTourPrice().compareTo(result1.getTourPrice());
            }
        });
        mAdapter.notifyDataSetChanged();

    }

    public ArrayList<ResultFindObject> getTestData() {
        String kCountry = "";
        String kCountryDepart = "";
        String kDate = "";
        String isHot = "";
        String people = "";
        String food = "";
        tableName = "";
        Intent intent = getIntent();
        kCountry = intent.getStringExtra("Country");
        kCountryDepart = intent.getStringExtra("CountryDepart");
        kDate = intent.getStringExtra("Date");
        isHot = intent.getStringExtra("Empty");
        food = intent.getStringExtra("Food");
        people = intent.getStringExtra("People");
        tableName = intent.getStringExtra("TableName");

       // mDb.execSQL("CREATE TABLE hotTour (id INTEGER UNIQUE, country TEXT, withcity TEXT, city TEXT, resort TEXT, price INTEGER, date TEXT, duration INTEGER, rating REAL, images TEXT, PRIMARY KEY (id, country, withcity, city, resort, price, date, duration, rating, images))");
       // mDb.execSQL("insert into hotTour  select * from findtours");


        Cursor cursor = mDb.rawQuery("SELECT * FROM " +  tableName + "  WHERE country = ? AND withcity = ? AND date = ? AND people = ? AND food = ?", new String[] {kCountry, kCountryDepart, kDate, people, food});

        if(cursor==null || cursor.getCount()==0){
            AlertDialog.Builder alert = new AlertDialog.Builder(context);
            alert.setCancelable(false);
            alert.setTitle(context.getResources().getString(R.string.text_title_alert));
            alert.setMessage(context.getResources().getString(R.string.text_error_find_tour));
            alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                   finish();
                }
            });
            alert.show();
        }
        cursor.moveToFirst();
        String strCountry = "";
        String strImages = "";
        Double dRating;
        String strDuration = "";
        String strPrice = "";
        String strCity = "";
        String strResort = "";
        Integer ID = 0;
        int intCountry = cursor.getColumnIndex("country");
        int intImages = cursor.getColumnIndex("images");
        int intRating = cursor.getColumnIndex("rating");
        int intDuration = cursor.getColumnIndex("duration");
        int intPrice = cursor.getColumnIndex("price");
        int intCity = cursor.getColumnIndex("city");
        int intResort = cursor.getColumnIndex("resort");
        int intID = cursor.getColumnIndex("id");

        while (!cursor.isAfterLast()) {
            strCountry = cursor.getString(intCountry);
            strImages = cursor.getString(intImages);
            dRating = cursor.getDouble(intRating);
            strDuration = cursor.getString(intDuration);
            strPrice = cursor.getString(intPrice);
            strCity = cursor.getString(intCity);
            strResort = cursor.getString(intResort);
            ID = cursor.getInt(intID);

            String str = strImages;
            String[] subStr;
            String delimeter = ","; // Разделитель
            subStr = str.split(delimeter); // Разделения строки str с помощью метода split()

            ResultFindObject resultFindObject = new ResultFindObject(ID, strDuration, dRating, subStr[0], kDate, strPrice, strCity, isHot, strResort);
            countryList.add(resultFindObject);

            cursor.moveToNext();
        }
        getSupportActionBar().setTitle(strCountry);


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
