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
import com.sajidur.swe_stp.Backend.DataHold;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AdminDashboardActivity extends AppCompatActivity {
    private MaterialCardView materialCardViewClassRoutine,materialCardViewExamRoutine,materialCardViewEvents,materialCardViewCourseOffer,materialCardViewLogout;

    ArrayList<Events>eventsArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        materialCardViewClassRoutine=(MaterialCardView) findViewById(R.id.materialCardViewClassRoutine);
        materialCardViewCourseOffer=(MaterialCardView) findViewById(R.id.materialCardViewCourseOffer);
        materialCardViewEvents=(MaterialCardView) findViewById(R.id.materialCardViewEvents);
        materialCardViewExamRoutine=(MaterialCardView) findViewById(R.id.materialCardViewExamRoutine);
        materialCardViewLogout=(MaterialCardView) findViewById(R.id.materialCardViewLogout);

        materialCardViewEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              startActivity(new Intent(AdminDashboardActivity.this,EventListActivity.class));

                //new getData().execute();
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

    /*
    class getData extends AsyncTask<Void,Void,String>{

        @Override
        protected String doInBackground(Void... voids) {
            getVolley();
            return null;
        }

        private void getVolley(){

            String url ="http://swestp.sajidur.com/api/getallevents";

            StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    System.out.println(response);
                    parseData(response);
                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            System.out.println("Error:"+error.toString());
                        }
                    });
            RequestQueue requestQueue = Volley.newRequestQueue(AdminDashboardActivity.this);
            requestQueue.add(stringRequest);
        }
        //volley ends here

        public void parseData(String response){
            System.out.println("Response:"+response);
            try{
                JSONArray jsonArray=new JSONArray(response);
                eventsArrayList=new ArrayList<Events>();
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject dataObj = jsonArray.getJSONObject(i);
                    Events events= new Events();
                    events.setID("id");
                    events.setTitle(dataObj.getString("title"));
                    events.setDescription(dataObj.getString("details"));
                    events.setEventDate("date");
                    events.setEventTime("time");
                    events.setImageUrl("attachmentUrl");
                    eventsArrayList.add(events);

                }
                if(!(DataHold.eventsArrayList==null)){
                    DataHold.eventsArrayList.clear();
                }
                DataHold.eventsArrayList=eventsArrayList;
                System.out.println("test"+eventsArrayList.size());
            }catch (JSONException e){
                e.printStackTrace();
            }

        }//parseData ends here

        @Override
        protected void onPostExecute(String s) {
            startActivity(new Intent(AdminDashboardActivity.this,EventListActivity.class));

        }
    }*/
    //end getData
}
