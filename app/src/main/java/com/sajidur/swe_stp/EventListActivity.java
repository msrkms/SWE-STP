package com.sajidur.swe_stp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;

public class EventListActivity extends AppCompatActivity {


    private MaterialButton materialButtonAddNewEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);

        materialButtonAddNewEvent=(MaterialButton) findViewById(R.id.materialButtonAddNewEvent);


        materialButtonAddNewEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EventListActivity.this,EventAddActivity.class));
                finish();
            }
        });

    }
}
