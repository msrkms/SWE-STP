package com.sajidur.swe_stp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputEditText;
import com.sajidur.swe_stp.Backend.DateFormatting;
import com.squareup.picasso.Picasso;

import java.sql.Time;
import java.text.SimpleDateFormat;

public class ShowEventItemDetailsSTActivity extends AppCompatActivity {

    ImageView imageViewST;
    TextInputEditText eventTitleST,eventDescriptionST,eventTimeST,eventDateST;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_event_item_details_st);

        String imglink=getIntent().getStringExtra("imglink");
        String title=getIntent().getStringExtra("title");
        String description=getIntent().getStringExtra("description");
        String time=getIntent().getStringExtra("time");
        String date=getIntent().getStringExtra("date");

        DateFormatting dateFormatting=new DateFormatting();
        time=dateFormatting.timeConvert(time);
        date=dateFormatting.dateConvert(date);

        imageViewST=(ImageView)findViewById(R.id.eventImageST);
        eventTitleST=(TextInputEditText)findViewById(R.id.eventTitleST);
        eventDescriptionST=(TextInputEditText)findViewById(R.id.eventDescriptionST);
        eventTimeST=(TextInputEditText)findViewById(R.id.eventTimeST);
        eventDateST=(TextInputEditText)findViewById(R.id.eventDateST);

        Picasso.get().load(imglink).into(imageViewST);
        eventTitleST.setText(title);
        eventDescriptionST.setText(description);
        eventTimeST.setText(time);
        eventDateST.setText(date);
    }
}
