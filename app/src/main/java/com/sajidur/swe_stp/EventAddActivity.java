package com.sajidur.swe_stp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.sajidur.swe_stp.Backend.DataHold;
import com.sajidur.swe_stp.Backend.Model.Event;
import com.sajidur.swe_stp.Backend.MyUrl;
import com.sajidur.swe_stp.Backend.UserController;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Calendar;
import java.util.UUID;

public class EventAddActivity extends AppCompatActivity {

    private static final int ImageTraceBack=1;
    private StorageReference firebaseFolder;
    private DatePickerDialog datePicker;
    private TimePickerDialog timePicker;
    private Calendar calendar;
    private int year, month, day,hour,minute;
    private Button buttonUpload;
    private ImageView imageViewEventImage;
    private TextInputEditText textInputEditTextTitle,textInputEditTextDetails,textInputEditTextDate,textInputEditTextTime;
    private ProgressDialog progressDialog;
    private Event event;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_add);
        event=new Event();
        textInputEditTextTitle=(TextInputEditText) findViewById(R.id.textInputEditTextTitle);
        textInputEditTextDetails=(TextInputEditText) findViewById(R.id.textInputEditDetails);
        textInputEditTextDate=(TextInputEditText) findViewById(R.id.textInputEditTextDate);
        textInputEditTextTime=(TextInputEditText) findViewById(R.id.textInputEditTextTime);
        buttonUpload=(Button) findViewById(R.id.buttonSave);
        imageViewEventImage =(ImageView) findViewById(R.id.imageViewEventImage);
        firebaseFolder= FirebaseStorage.getInstance().getReference().child("EventImages");



        imageViewEventImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseEventImage();
            }
        });

        textInputEditTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDate();
            }
        });


        textInputEditTextTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTime();
            }
        });

        buttonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getInput();
                addEvent();

            }
        });


    }

    private void chooseEventImage(){
        Intent intent   = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent,ImageTraceBack);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==ImageTraceBack){
            if(resultCode==RESULT_OK){
                final Uri uriImage=data.getData();
                final StorageReference ImageName=firebaseFolder.child("image"+ UUID.randomUUID().toString());
                ImageName.putFile(uriImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        ImageName.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                try {
                                    Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),uriImage);
                                    imageViewEventImage.setImageBitmap(bitmap);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                event.setAttachmentUrl(uri.toString());
                            }
                        });
                    }
                });

            }
        }
    }


    private void showLoading(){
        this.progressDialog= new ProgressDialog(this);
        progressDialog.setCancelable(true);
        progressDialog.setTitle("Adding Event");
        progressDialog.show();
    }

    public void getInput(){

        event.setTitle(textInputEditTextTitle.getText().toString());
        event.setDetails(textInputEditTextDetails.getText().toString());
        event.setDate(textInputEditTextDate.getText().toString());
        event.setTime(textInputEditTextTime.getText().toString());
    }

    public void setTime(){
        calendar = Calendar.getInstance();
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
        timePicker = new TimePickerDialog(EventAddActivity.this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
                        String Min=String.valueOf(minute);
                        if(minute<10){
                            Min="0"+minute;
                        }

                        String Hour=String.valueOf(hourOfDay);
                        if(hourOfDay<10){
                            Hour="0"+hourOfDay;
                        }

                        textInputEditTextTime.setText(Hour + ":" + Min);


                    }
                }, hour, minute, false);
        timePicker.show();
    }

    private void setDate(){
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);


        datePicker = new DatePickerDialog(EventAddActivity.this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        String dayofmonth=String.valueOf(dayOfMonth);
                        if(dayOfMonth<10){
                            dayofmonth="0"+dayOfMonth;
                        }

                        String month=String.valueOf(monthOfYear+1);

                        if((monthOfYear+1)<10){
                            month="0"+monthOfYear;
                        }

                        String Date= year+ "-" + (month) + "-" + dayofmonth;
                        textInputEditTextDate.setText(Date);

                    }
                }, year, month, day);
        datePicker.show();
    }

    private void addEvent(){

        showLoading();
        DataHold.IsSuccess=false;



        JSONObject postparams = new JSONObject();
        try {

            postparams.put("title",event.getTitle());
            postparams.put("date",event.getDate());
            postparams.put("time",event.getTime());
            postparams.put("details",event.getDetails());
            postparams.put("attachmentUrl",event.getAttachmentUrl());


        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                MyUrl.AddEvent, postparams,
                new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        try{
                            JSONObject jsonObject= new JSONObject(response.toString());
                            if(!(jsonObject==null)){
                                DataHold.IsSuccess=true;
                                new MaterialAlertDialogBuilder(EventAddActivity.this)
                                        .setTitle("Added")
                                        .setMessage("Event Added")
                                        .setNegativeButton("ok",null)
                                        .show();

                                progressDialog.dismiss();
                            }
                        }catch (JSONException e) {
                            e.printStackTrace();
                            DataHold.IsSuccess=false;
                            progressDialog.dismiss();
                        }

                    }


                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                    }
                });





        RequestQueue requestQueue = Volley.newRequestQueue(EventAddActivity.this);
        requestQueue.add(jsonObjReq);

    }
}
