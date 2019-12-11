package com.sajidur.swe_stp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sajidur.swe_stp.Backend.DataHold;
import com.sajidur.swe_stp.Backend.Events;
import com.sajidur.swe_stp.Backend.ListViewAdapterEvent;
import com.sajidur.swe_stp.Backend.MyUrl;
import com.sajidur.swe_stp.Backend.RecyclerViewAdapterEvents;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class EventListActivity extends AppCompatActivity {


    private FloatingActionButton materialButtonAddNewEvent;
    ArrayList<Events>eventsArrayList;
    ListView listViewEvents;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);

        listViewEvents=(ListView)findViewById(R.id.eventList);

       // ArrayList<Events>eventsArrayList= DataHold.eventsArrayList;

        materialButtonAddNewEvent=(FloatingActionButton) findViewById(R.id.materialButtonAddNewEvent);


        materialButtonAddNewEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EventListActivity.this,EventAddActivity.class));
                finish();
            }
        });

        loading();
        getAllEvents();

        /*
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerViewEvent);
        RecyclerViewAdapterEvents recyclerViewAdapterEvents= new RecyclerViewAdapterEvents(this,DataHold.eventsArrayList);
        recyclerView.setAdapter(recyclerViewAdapterEvents);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        */

        listViewEvents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String id = DataHold.eventsArrayList.get(i).getID();
                String title=DataHold.eventsArrayList.get(i).getTitle();
                String description=DataHold.eventsArrayList.get(i).getDescription();
                String time=DataHold.eventsArrayList.get(i).getEventTime();
                String date= DataHold.eventsArrayList.get(i).getEventDate();
                System.out.println(id+title+description+time+date);



            }
        });


    }

    private void loading(){
        this.progressDialog= new ProgressDialog(this);
        progressDialog.setCancelable(true);
        progressDialog.setTitle("Getting Data");
        progressDialog.show();
    }

    private void getAllEvents(){

        String url= MyUrl.ALLEVENTS;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONArray jsonArray=new JSONArray(response);
                    eventsArrayList=new ArrayList<Events>();
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Events events = new Events();
                        events.setID(jsonObject.getString("id"));
                        events.setTitle(jsonObject.getString("title"));
                        events.setDescription(jsonObject.getString("details"));
                        events.setEventDate(jsonObject.getString("date"));
                        events.setEventTime(jsonObject.getString("time"));
                        events.setImageUrl(jsonObject.getString("attachmentUrl"));
                        eventsArrayList.add(events);

                    }
                    if(!(DataHold.eventsArrayList==null)){
                        DataHold.eventsArrayList.clear();
                    }
                    DataHold.eventsArrayList=eventsArrayList;
                    System.out.println("test"+DataHold.eventsArrayList.size());

                    if(!(DataHold.eventsArrayList==null)&& DataHold.eventsArrayList.size()>0){
                        ListViewAdapterEvent listViewAdapterEvent=new ListViewAdapterEvent(EventListActivity.this,DataHold.eventsArrayList);
                        listViewEvents.setAdapter(listViewAdapterEvent);
                    }else{
                        listViewEvents.setAdapter(null);
                    }
                    progressDialog.dismiss();

                }catch (JSONException e){
                    e.printStackTrace();
                    progressDialog.dismiss();
                    listViewEvents.setAdapter(null);
                    Toast.makeText(EventListActivity.this,"Failed to get data",Toast.LENGTH_LONG).show();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        //request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);


    }


}
