package com.sajidur.swe_stp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.card.MaterialCardView;

public class AdminDashboardActivity extends AppCompatActivity {
    private MaterialCardView materialCardViewClassRoutine,materialCardViewExamRoutine,materialCardViewEvents,materialCardViewCourseOffer,materialCardViewLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        materialCardViewClassRoutine=(MaterialCardView) findViewById(R.id.materialCardViewAdminClassRoutine);
        materialCardViewCourseOffer=(MaterialCardView) findViewById(R.id.materialCardViewAdminCourseOffer);
        materialCardViewEvents=(MaterialCardView) findViewById(R.id.materialCardViewAdminEvent);
        materialCardViewExamRoutine=(MaterialCardView) findViewById(R.id.materialCardViewAdminExamRoutine);
        materialCardViewLogout=(MaterialCardView) findViewById(R.id.materialCardViewAdminLogOut);

        materialCardViewEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminDashboardActivity.this,EventListActivity.class));
            }
        });


        materialCardViewExamRoutine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminDashboardActivity.this,UploadExamRoutineActivity.class));
               // AdminDashboardActivity.this.finish();
            }
        });


        materialCardViewClassRoutine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminDashboardActivity.this,UploadRoutineActivity.class));
            }
        });


        materialCardViewCourseOffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminDashboardActivity.this,UploadCourseOfferActivity.class));
            }
        });


        materialCardViewLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdminDashboardActivity.this.finish();
            }
        });


    }
}
