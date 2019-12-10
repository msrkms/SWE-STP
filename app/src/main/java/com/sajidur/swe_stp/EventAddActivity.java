package com.sajidur.swe_stp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class EventAddActivity extends AppCompatActivity {

    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;
    Calendar calendar;
    int hour;
    int minute;
    String ampm;
    MaterialButton saveBtn;
    TextInputEditText title,description,location,time,date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_add);


        time=(TextInputEditText)findViewById(R.id.eventTime);
        date=(TextInputEditText)findViewById(R.id.eventDate);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar=Calendar.getInstance();
                int year=calendar.get(Calendar.YEAR);
                int month=calendar.get(Calendar.MONTH);
                int day=calendar.get(Calendar.DAY_OF_MONTH);

                datePickerDialog= new DatePickerDialog(EventAddActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        date.setText(i2 + "/" + (i1+1) + "/" + i);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });


        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar=Calendar.getInstance();
                 hour=calendar.get(Calendar.HOUR_OF_DAY);
                 minute=calendar.get(Calendar.MINUTE);


                timePickerDialog=new TimePickerDialog(EventAddActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        if(i>=12){
                             ampm="PM";
                             hour=i-12;
                        }
                        else{
                             ampm="AM";
                        }
                       // time.setText(i+":"+i1);
                        time.setText(String.format("%02d:%02d",hour,minute)+ampm);
                    }
                },hour,minute,false);
                timePickerDialog.show();
            }
        });


    }
}
