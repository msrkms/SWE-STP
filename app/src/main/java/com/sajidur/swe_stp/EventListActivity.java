package com.sajidur.swe_stp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sajidur.swe_stp.Backend.DataHold;
import com.sajidur.swe_stp.Backend.Events;
import com.sajidur.swe_stp.Backend.RecyclerViewAdapterEvents;

import java.util.ArrayList;

public class EventListActivity extends AppCompatActivity {


    private FloatingActionButton materialButtonAddNewEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);

        ArrayList<Events>eventsArrayList= DataHold.eventsArrayList;

        materialButtonAddNewEvent=(FloatingActionButton) findViewById(R.id.materialButtonAddNewEvent);


        materialButtonAddNewEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EventListActivity.this,EventAddActivity.class));
                finish();
            }
        });

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerViewEvent);
        RecyclerViewAdapterEvents recyclerViewAdapterEvents= new RecyclerViewAdapterEvents(this,eventsArrayList);
        recyclerView.setAdapter(recyclerViewAdapterEvents);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }
}
