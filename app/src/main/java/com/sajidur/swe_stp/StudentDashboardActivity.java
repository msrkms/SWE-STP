package com.sajidur.swe_stp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.card.MaterialCardView;

public class StudentDashboardActivity extends AppCompatActivity {

    MaterialCardView materialCardViewTask,materialCardViewEvent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);

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
