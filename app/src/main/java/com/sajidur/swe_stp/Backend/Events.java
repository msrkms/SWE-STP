package com.sajidur.swe_stp.Backend;

import android.widget.ImageView;

public class Events {

    private String ID;
    private String Title;
    private String Description;
    private String EventTime;
    private String EventDate;
    private String ImageUrl;

    public String getID(){
        return ID;
    }

    public void setID(String ID){
        this.ID=ID;
    }

    public String getTitle(){
        return Title;
    }

    public void setTitle(String Title){
        this.Title=Title;
    }

    public String getDescription(){
        return Description;
    }

    public void setDescription(String Description){
        this.Description=Description;
    }

    public String getEventTime(){
        return EventTime;
    }

    public void setEventTime(String EventTime){
        this.EventTime=EventTime;
    }

    public String getEventDate(){
        return EventDate;
    }

    public void setEventDate(String EventDate){
        this.EventDate=EventDate;
    }

    public String getImageUrl(){ return ImageUrl; }

    public void setImageUrl(String ImageUrl){ this.ImageUrl=ImageUrl;}

}
