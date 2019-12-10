package com.sajidur.swe_stp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
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
import com.sajidur.swe_stp.Backend.DataHold;
import com.sajidur.swe_stp.Backend.Events;
import com.sajidur.swe_stp.Backend.ListViewAdapterEvent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ShowEventListSTActivity extends AppCompatActivity {

    ArrayList<Events>eventsArrayList;
    ListView listViewEventST;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_event_list_st);

        listViewEventST=(ListView)findViewById(R.id.listViewEventST);

        loading();
        getAllEvents();


        listViewEventST.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String id = DataHold.eventsArrayList.get(i).getID();
                String imglink=DataHold.eventsArrayList.get(i).getImageUrl();
                String title=DataHold.eventsArrayList.get(i).getTitle();
                String description=DataHold.eventsArrayList.get(i).getDescription();
                String time=DataHold.eventsArrayList.get(i).getEventTime();
                String date= DataHold.eventsArrayList.get(i).getEventDate();
                System.out.println(id+imglink+title+description+time+date);


                Intent intent = new Intent(ShowEventListSTActivity.this,ShowEventItemDetailsSTActivity.class);
                intent.putExtra("imglink",imglink);
                intent.putExtra("title",title);
                intent.putExtra("description",description);
                intent.putExtra("time",time);
                intent.putExtra("date",date);
                startActivity(intent);
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

        String url="http://swestp.sajidur.com/api/getallevents";

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
                        ListViewAdapterEvent listViewAdapterEvent=new ListViewAdapterEvent(ShowEventListSTActivity.this,DataHold.eventsArrayList);
                        listViewEventST.setAdapter(listViewAdapterEvent);
                    }else{
                        listViewEventST.setAdapter(null);
                    }
                    progressDialog.dismiss();

                }catch (JSONException e){
                    e.printStackTrace();
                    progressDialog.dismiss();
                    listViewEventST.setAdapter(null);
                    Toast.makeText(ShowEventListSTActivity.this,"Failed to get data",Toast.LENGTH_LONG).show();
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
