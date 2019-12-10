package com.sajidur.swe_stp.Backend;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DateFormatting {

    public String dateConvert(String Date){
        String sDate=Date;

        try{
            String pattern = "yyyy-MM-dd'T'HH:mm:ss";
            String pattern2 = "dd-MM-yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            java.util.Date date = simpleDateFormat.parse(Date);

            DateFormat dateFormat = new SimpleDateFormat(pattern2);

            String strDate = dateFormat.format(date);
            sDate=strDate;

        }catch (Exception e){

        }

        return sDate;
    }

    public String dateConvertforPost(String Date){
        String sDate=Date;

        try{
            String pattern2 = "yyyy-MM-dd'T'HH:mm:ss";
            String pattern = "dd-MM-yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            java.util.Date date = simpleDateFormat.parse(Date);

            DateFormat dateFormat = new SimpleDateFormat(pattern2);

            String strDate = dateFormat.format(date);
            sDate=strDate;

        }catch (Exception e){

        }

        return sDate;
    }

    public String timeConvert(String Date){
        String sDate=Date;

        try{
            String pattern = "yyyy-MM-dd'T'HH:mm:ss";
            String pattern2 = "HH:mm";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            java.util.Date date = simpleDateFormat.parse(Date);

            DateFormat dateFormat = new SimpleDateFormat(pattern2);

            String strDate = dateFormat.format(date);
            sDate=strDate;

        }catch (Exception e){

        }

        return sDate;
    }

    public String timeConvertforPost(String Date){
        String sDate=Date;

        try{
            String pattern2 = "yyyy-MM-dd'T'HH:mm:ss";
            String pattern = "HH:mm";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            java.util.Date date = simpleDateFormat.parse(Date);

            DateFormat dateFormat = new SimpleDateFormat(pattern2);

            String strDate = dateFormat.format(date);
            sDate=strDate;

        }catch (Exception e){

        }

        return sDate;
    }




}
