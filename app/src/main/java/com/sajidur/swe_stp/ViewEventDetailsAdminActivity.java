package com.sajidur.swe_stp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.Picasso;

public class ViewEventDetailsAdminActivity extends AppCompatActivity {

    boolean isEnabled;
    ImageView imageViewAdmin,imageViewEdit,imageViewDone;
    TextInputEditText eventTitleAdmin,eventDescriptionAdmin,eventTimeAdmin,eventDateAdmin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_event_details_admin);
        isEnabled=false;
        String imglink=getIntent().getStringExtra("imglink");
        String title=getIntent().getStringExtra("title");
        String description=getIntent().getStringExtra("description");
        String time=getIntent().getStringExtra("time");
        String date=getIntent().getStringExtra("date");


        imageViewEdit=(ImageView)findViewById(R.id.editOk);
        imageViewDone=(ImageView)findViewById(R.id.done);
        imageViewAdmin=(ImageView)findViewById(R.id.eventImageAdmin);
        eventTitleAdmin=(TextInputEditText)findViewById(R.id.eventTitleAdmin);
        eventDescriptionAdmin=(TextInputEditText)findViewById(R.id.eventDescriptionAdmin);
        eventTimeAdmin=(TextInputEditText)findViewById(R.id.eventTimeAdmin);
        eventDateAdmin=(TextInputEditText)findViewById(R.id.eventDateAdmin);


        enableOrDisable(isEnabled);
            imageViewDone.setVisibility(View.INVISIBLE);
            Picasso.get().load(imglink).into(imageViewAdmin);
            eventTitleAdmin.setText(title);
            eventDescriptionAdmin.setText(description);
            eventTimeAdmin.setText(time);
            eventDateAdmin.setText(date);


            imageViewEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    isEnabled=true;
                    enableOrDisable(isEnabled);
                    imageViewEdit.setVisibility(View.INVISIBLE);
                    imageViewDone.setVisibility(View.VISIBLE);
                }
            });



    }

    public void enableOrDisable(boolean isEnabled){
        eventTitleAdmin.setEnabled(isEnabled);
        eventDescriptionAdmin.setEnabled(isEnabled);
        eventDateAdmin.setEnabled(isEnabled);
        eventTimeAdmin.setEnabled(isEnabled);
    }
}
