package com.sajidur.swe_stp.Backend;

import android.content.Context;
import android.content.DialogInterface;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.sajidur.swe_stp.Backend.Model.User;
import com.sajidur.swe_stp.RegistrationActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

public class UserController {
    private User user;

    public UserController(User  user){
        this.user=user;
    }


    public UserController(){

    }

    public String genarateVCode(){
        Random random= new Random();
        String CharList = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int RandInt = random.nextInt(1000);
        return String.valueOf(CharList.charAt(random.nextInt(CharList.length())))+RandInt;
    }

    public String getUserRole(String Email, String ID){
        String UserRole="Not Vaild";
        if(Email.contains("-") && ID.contains("-")){
            UserRole="Student";
        }else if(!Email.contains("-") && !ID.contains("-")){
            UserRole="Teacher";
        }else{
            UserRole="Not Valid";
        }

        return UserRole;


    }

}
