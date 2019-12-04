package com.sajidur.swe_stp.Backend;

public class Events {

    private int ID;
    private String Title;
    private String Description;
    private String EventTime;
    private String EventDate;

    public int getID(){
        return ID;
    }

    public void setID(int ID){
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

}
