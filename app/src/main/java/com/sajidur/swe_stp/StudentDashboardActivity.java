package com.sajidur.swe_stp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class StudentDashboardActivity extends AppCompatActivity {
    private TextView textViewClassRoutine,textViewExamRoutine,textViewAppointment,textViewClassDiscussion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);

        textViewAppointment=(TextView) findViewById(R.id.textViewAppointment);
        textViewClassDiscussion=(TextView) findViewById(R.id.textViewClassDiscussion);
        textViewClassRoutine=(TextView) findViewById(R.id.textViewClassRoutine);
        textViewExamRoutine=(TextView) findViewById(R.id.textViewExamRoutine);


        textViewExamRoutine.setSelected(true);
        textViewClassRoutine.setSelected(true);
        textViewClassDiscussion.setSelected(true);
        textViewAppointment.setSelected(true);
    }
}
