package com.sajidur.swe_stp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.card.MaterialCardView;
import com.sajidur.swe_stp.Backend.Events;

import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class StudentDashboardActivity extends AppCompatActivity {

    private TextView textViewAppointment,textViewClassDiscussion,textViewExamRoutine,textViewClassRoutine;
    private MaterialCardView materialCardViewTask,materialCardViewEvent,materialCardViewHelp,materialCardViewProfile,materialCardViewAppointment;

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
        materialCardViewHelp=(MaterialCardView)findViewById(R.id.materialCardViewHelp);
        materialCardViewAppointment=(MaterialCardView)findViewById(R.id.materialCardViewAppointment);
        materialCardViewProfile=(MaterialCardView) findViewById(R.id.materialCardViewProfile);
        materialCardViewAppointment=(MaterialCardView) findViewById(R.id.materialCardViewAppointment);



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

        materialCardViewHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StudentDashboardActivity.this,HelpPostListActivity.class));
            }
        });

        materialCardViewAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StudentDashboardActivity.this,AppointmentSTActivity.class));
            }
        });

        materialCardViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StudentDashboardActivity.this,ProfileActivity.class));
            }
        });

        materialCardViewAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // startActivity(StudentDashboardActivity.this,Appo);
            }
        });

    }


}
