package com.example.vidal.dipl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

public class ActivitySelectDate extends AppCompatActivity {

    String key = "";

    private DatePicker mDatePicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_date);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);


        mDatePicker = (DatePicker) findViewById(R.id.datePicker2);

        Calendar today = Calendar.getInstance();

        mDatePicker.init(today.get(Calendar.YEAR), today.get(Calendar.MONTH),
                today.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {

                    @Override
                    public void onDateChanged(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                        Toast.makeText(getApplicationContext(),
                                "Год: " + year + "\n" + "Месяц: "
                                        + (monthOfYear + 1) + "\n" + "День: " + dayOfMonth, Toast.LENGTH_SHORT).show();

                    }
                });

        Button changingDateButton = (Button) findViewById(R.id.ok_button);
        Button cancelBtn = (Button) findViewById(R.id.cancel_button);

        changingDateButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String date = "";
                int month = mDatePicker.getMonth() + 1;
                if(month<10) {
                   date = mDatePicker.getDayOfMonth() + "." + 0 + month + "." + mDatePicker.getYear();
                }
                else
                    date = mDatePicker.getDayOfMonth() + "." + month + "." + mDatePicker.getYear();

                //Toast.makeText(getApplicationContext(), date, Toast.LENGTH_SHORT).show();

                Intent res = new Intent();
                res.putExtra("key", key);
                res.putExtra("value", date);
                setResult(RESULT_OK, res);
                finish();
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

    }

}