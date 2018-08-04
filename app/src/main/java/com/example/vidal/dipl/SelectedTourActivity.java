package com.example.vidal.dipl;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vidal.dipl.entities.ResultFindObject;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;

public class SelectedTourActivity extends AppCompatActivity implements GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener{

    String textEmail;

    int currentImage=0;
    ArrayList<String> images;
    ImageView imageView;
    ImageView imLike;
    ImageButton imBtnNext;
    ImageButton imBtnPrev;

    TextView tvNameResort, tvDateDurality, nameView, tvInPrice, tvPlacement, tvNomer, tvFood;

    Button btnResPrice;
    Button btnSend;
    EditText eTextName, eTextMail, eTextNomer;

    GestureDetectorCompat mDetector;
    public RatingBar rBar;

    private SQLiteDatabase mDb;
    DataBaseHelper updDB;

    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_tour);
        currentImage=0;

        images=new ArrayList<String>();

        imageView = ((ImageView)findViewById(R.id.imGallery));
        imLike = ((ImageView)findViewById(R.id.imLike));
        imBtnPrev = (ImageButton) findViewById(R.id.imBtnPrev);
        imBtnNext = (ImageButton) findViewById(R.id.imBtnNext);
        rBar = (RatingBar)findViewById(R.id.ratBar);

        tvNameResort = (TextView)findViewById(R.id.tvNameResort);
        tvDateDurality = (TextView)findViewById(R.id.tvDateDurality);
        tvInPrice = (TextView)findViewById(R.id.tvInPrice);
        tvPlacement = (TextView)findViewById(R.id.tvPlacement);
        tvNomer = (TextView)findViewById(R.id.tvNomer);
        tvFood = (TextView)findViewById(R.id.tvFood);
        btnResPrice = (Button) findViewById(R.id.btnResPrice);
        btnSend = (Button) findViewById(R.id.btnSend);
        eTextName = (EditText) findViewById(R.id.eTextName);
        eTextMail = (EditText) findViewById(R.id.eTextMail);
        eTextNomer = (EditText) findViewById(R.id.eTextNomer);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.tbSelectedTour);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_chevron_left);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        updDB = new DataBaseHelper(getApplicationContext());
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

        imBtnPrev.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                onPrevImage();
            }
        });
        imBtnNext.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                onNextImage();
            }
        });
        btnSend.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                textEmail = eTextMail.getText() + " " + eTextName.getText() + " " + eTextNomer.getText();
                new SendMail().execute();

            }
        });

        mDetector = new GestureDetectorCompat(this,this);
        mDetector.setOnDoubleTapListener(this);


        getTestData();

    }


    public ArrayList<String> getTestData() {
        String kDuration = "";
        String kCountryDepart = "";
        String kDate = "";
        String isHot = "";
        String tableName = "";
        Intent intent = getIntent();
        final int idTour = intent.getIntExtra("Pos", 0);

        kDate = intent.getStringExtra("Date");
        kDuration = intent.getStringExtra("Duration");
        tableName = intent.getStringExtra("TableName");

        Cursor cursor = mDb.rawQuery("SELECT * FROM " +  tableName + "  WHERE id = ?", new String[] {String.valueOf(idTour)});
        cursor.moveToFirst();
        String strImages = "";
        Double dRating = 0.1;
        String strPrice = "";
        String strResort = "";
        String strInPrice = "";
        String strPlacement = "";
        String strNomer = "";
        String strFood = "";

        int intImages = cursor.getColumnIndex("images");
        int intRating = cursor.getColumnIndex("rating");
        int intPrice = cursor.getColumnIndex("price");
        int intResort = cursor.getColumnIndex("resort");
        int intInPrice = cursor.getColumnIndex("inprice");
        int intPeople = cursor.getColumnIndex("people");
        int intNomer = cursor.getColumnIndex("nomer");
        int intFood = cursor.getColumnIndex("food");


            strImages = cursor.getString(intImages);
            strPrice = cursor.getString(intPrice);
            strResort = cursor.getString(intResort);
            strInPrice = cursor.getString(intInPrice);
            strPlacement = cursor.getString(intPeople);
            strNomer = cursor.getString(intNomer);
            strFood = cursor.getString(intFood);
            String str = strImages;
            String[] subStr;
            String delimeter = ","; // Разделитель
            subStr = str.split(delimeter); // Разделения строки str с помощью метода split()
            for (int i = 0; i < subStr.length; i++) {
                images.add(subStr[i]);
            }

        tvNameResort.setText(strResort);
        tvDateDurality.setText(kDate + " - " + kDuration + " " + getApplicationContext().getResources().getString(R.string.text_duration));
       tvInPrice.setText(strInPrice);
        tvPlacement.setText(strPlacement);
        tvNomer.setText(strNomer);
        tvFood.setText(strFood);
        btnResPrice.setText(strPrice);


        dRating = cursor.getDouble(intRating);

        try {
            String strRat = String.valueOf(dRating);
            Float f1 = new Float(strRat);
            System.out.println(f1);
            rBar.setRating(f1);
        } catch (NumberFormatException e) {
            System.err.println("Неверный формат строки!");
        }

        getSupportActionBar().setTitle(strResort + " RESORT");
            updatePhoto(images.get(currentImage));

        return images;
    }


    public void updatePhoto(String images1){
        try{
            Picasso.with(getApplication()).load(images1).into(imageView);

        }catch(Exception e){
            e.printStackTrace();
            nameView.setText("Ошибка загрузки файла");
        }
    }


    public boolean onTouchEvent(MotionEvent event){
        this.mDetector.onTouchEvent(event);
        // Be sure to call the superclass implementation
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent event) {

        return false;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2,
                           float velocityX, float velocityY) {

        if(e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
            onNextImage(); // справа налево
        }  else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
            onPrevImage(); // слева направо
        }

        return true;
    }
    public boolean onNextImage(){
        if(images.size() > 0) {
            if (currentImage == images.size() - 1)
                currentImage = 0;
            else
                currentImage++;
            updatePhoto(images.get(currentImage));
        }
        return false;
    }
    public boolean onPrevImage(){
        if(images.size() > 0) {
            if (currentImage == 0)
                currentImage = images.size() - 1;
            else
                currentImage--;
            updatePhoto(images.get(currentImage));
        }
        return false;
    }

    @Override
    public void onLongPress(MotionEvent event) {

    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                            float distanceY) {

        return true;
    }

    @Override
    public void onShowPress(MotionEvent event) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent event) {

        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent event) {

        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent event) {

        return true;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent event) {

        return true;
    }


    private class SendMail extends AsyncTask<String, Void, String>
    {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(getApplicationContext(), "Ваш запит в обробці", Toast.LENGTH_SHORT).show();
        }
        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub
            try {


                GMailSender sender = new GMailSender("op4475921@gmail.com", "q?wert?zxc11086781");


                sender.sendMail("New request",
                        textEmail,
                        "op4475921@gmail.com",
                        "zhyravlev.93@mail.ru");



            } catch (Exception e) {
                Log.e("SendMail", e.getMessage(), e);
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }

            return "Ваш запит оброблено, дані успішно доставлені";
        }
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            eTextMail.setText(null);
            eTextName.setText(null);
            eTextNomer.setText(null);
            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();

        }

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
