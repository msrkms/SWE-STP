package com.sajidur.swe_stp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.sajidur.swe_stp.Backend.DBHealper;
import com.sajidur.swe_stp.Backend.DataHold;
import com.sajidur.swe_stp.Backend.Model.User;
import com.sajidur.swe_stp.Backend.MyUrl;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class LoginActivity extends AppCompatActivity {
    private MaterialButton buttonLogin;
    ProgressDialog progressDialog;
    private TextView textViewSignUp,textViewForgetPassword;
    private TextInputEditText textInputEditTextEmail,textInputEditTextPassword;
    private  User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        buttonLogin=(MaterialButton) findViewById(R.id.buttonLogin);
        textInputEditTextEmail=(TextInputEditText) findViewById(R.id.editTextEmail);
        textInputEditTextPassword=(TextInputEditText) findViewById(R.id.textInputEditTextloginPassword);
        textViewSignUp=(TextView) findViewById(R.id.textViewSignup);
        textViewForgetPassword=(TextView) findViewById(R.id.textViewforgotPassword);



        textViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegistrationActivity.class));
                LoginActivity.this.finish();
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user=new User();
                String Email=textInputEditTextEmail.getText().toString();
                String Password=textInputEditTextPassword.getText().toString();
                if(Email.equals("admin")){
                    intentToAdmin();
                }else if(Email.equals("Student")){
                    intentToStudent();
                }
            }
        });







    }



    public boolean checkInternetConnection(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting())) return true;
            else return false;
        } else
            return false;
    }


    public void checkUser(){
        File file = new File(getFilesDir().getAbsolutePath()+"/"+"User.txt");
        if(file.exists()){
            try {
                Scanner scanner= new Scanner(file);
                String Teamp="--";
                while (scanner.hasNextLine()){
                    Teamp=scanner.nextLine();
                }
                String[] Data=new String[2];
                Data=Teamp.split("--");
                checkSQLITEDB(Data[0],Data[1]);
            }catch (Exception e){

            }


        }else {

        }
    }




    private void tryLogin( String Email, String Password){
        showLoading();
        final String Url= MyUrl.Login;

        final String inputEmail=Email;

        JSONObject postparams = new JSONObject();
        try {

            postparams.put("email",user.getEmail());
            postparams.put("password",user.getPassword());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, MyUrl.Login,postparams,
                new  Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        try {
                            User user= new User();

                            JSONObject dataobj = new JSONObject(response.toString());
                            user.setRole(dataobj.getString("role"));
                            user.setPassword(dataobj.getString("password"));
                            user.setID(dataobj.getString("id"));
                            user.setEmail(dataobj.getString("email"));
                            user.setVcode(dataobj.getString("vCode"));
                            user.setIsVerified(dataobj.getString("verify"));


                            if(user.getEmail().equals(inputEmail)){
                                DataHold.UserEmail=user.getEmail();
                                DataHold.user=user;
                                savetoSQLITE();
                                createLoginFile(user.getEmail(),user.getPassword());
                                intentFilter();
                            }

                            progressDialog.dismiss();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                            System.out.println(e);
                            new MaterialAlertDialogBuilder(LoginActivity.this)
                                    .setTitle("Failed")
                                    .setMessage("Make Sure Your Enter Correct information")
                                    .setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            progressDialog.dismiss();
                                        }
                                    })
                                    .show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        new MaterialAlertDialogBuilder(LoginActivity.this)
                                .setTitle("Failed")
                                .setMessage("Make Sure Your Enter Correct information")
                                .setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        progressDialog.dismiss();
                                    }
                                })
                                .show();
                    }
                });

        // request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(jsonObjectRequest);
    }



    private void showLoading(){
        this.progressDialog= new ProgressDialog(this);
        progressDialog.setCancelable(true);
        progressDialog.setTitle("Verifying");
        progressDialog.show();
    }

    private void createLoginFile(String email,String pass){
        File file=new File(getFilesDir().getAbsolutePath()+"/"+"User.txt");
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(email+"--"+pass);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void savetoSQLITE(){
        try{
            SQLiteDatabase ss = SQLiteDatabase.openDatabase(DBHealper.pathToSaveDBFile,null,SQLiteDatabase.OPEN_READWRITE);
            ContentValues contentValues = new ContentValues();
            contentValues.put("Email",DataHold.user.getEmail());
            contentValues.put("Password",DataHold.user.getPassword());
            ss.insert("User",null,contentValues);
            ss.close();
        }catch (SQLException e){

        }catch (Exception e){

        }

    }

    private void checkSQLITEDB(String Email,String Password){
        try {
            SQLiteDatabase db = SQLiteDatabase.openDatabase(DBHealper.pathToSaveDBFile, null, SQLiteDatabase.OPEN_READONLY);
            String query = "select * from User where Email='"+Email+"' and Password='"+Password+"'";
            Cursor cursor = db.rawQuery(query, null);
            if(cursor.moveToFirst()){
                DataHold.UserEmail=Email;

            }else{
            }
            db.close();
        }catch (Exception e){
        }
    }


    @Override
    public void onBackPressed() {


        new MaterialAlertDialogBuilder(LoginActivity.this)
                .setTitle("Confirm")
                .setMessage("Do You Want To Exit")
                .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        LoginActivity.this.finish();
                    }
                })
                .setNegativeButton("No",null)
                .show();
    }


    private void intentFilter(){
        if(DataHold.user.getRole().equals("Student")){
            intentToStudent();
        }else if(DataHold.user.getRole().equals("Teacher")){

        }else if(DataHold.user.getRole().equals("Admin")){
            intentToAdmin();
        }
    }

    private void intentToAdmin(){
        startActivity(new Intent(LoginActivity.this,AdminDashboardActivity.class));
        LoginActivity.this.finish();
    }

    private void intentToStudent(){
        startActivity(new Intent(LoginActivity.this,StudentDashboardActivity.class));
        LoginActivity.this.finish();
    }

}
