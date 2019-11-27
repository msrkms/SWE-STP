package com.sajidur.swe_stp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.card.MaterialCardView;
import android.widget.TextView;

public class StudentDashboardActivity extends AppCompatActivity {

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

        materialCardViewTask=(MaterialCardView)findViewById(R.id.materialCardViewMytask);
        materialCardViewEvent=(MaterialCardView)findViewById(R.id.materialCardViewEvent);

        materialCardViewTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StudentDashboardActivity.this,MyTaskActivity.class));
            }
        });

        materialCardViewEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StudentDashboardActivity.this,ShowEventListSTActivity.class));
            }
        });

    }
}
