package com.sajidur.swe_stp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class UploadExamRoutineActivity extends AppCompatActivity {

    TextInputEditText selectDate;
    Calendar calendar;
    DatePickerDialog datePickerDialog;
    int day,month,year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_exam_routine);

        selectDate=(TextInputEditText)findViewById(R.id.datePicker);

        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar=Calendar.getInstance();
                 day=calendar.get(Calendar.DAY_OF_MONTH);
                 month=calendar.get(Calendar.MONTH);
                 year=calendar.get(Calendar.YEAR);

                datePickerDialog=new DatePickerDialog(UploadExamRoutineActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        selectDate.setText(i2+"/"+(i1+1)+"/"+i);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });
    }
}
