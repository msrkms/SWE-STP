package com.sajidur.swe_stp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.android.volley.toolbox.Volley;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.sajidur.swe_stp.Backend.DataHold;
import com.sajidur.swe_stp.Backend.DataVerification;
import com.sajidur.swe_stp.Backend.EmailSender;
import com.sajidur.swe_stp.Backend.Model.User;
import com.sajidur.swe_stp.Backend.MyUrl;
import com.sajidur.swe_stp.Backend.UserController;

import org.json.JSONException;
import org.json.JSONObject;

public class RegistrationActivity extends AppCompatActivity {

    private Button buttonSignUp;
    private EditText editTextEmail,editTextID,editTextPassword,editTextConfirmPassword;
    private TextView textViewAlreadyHaveAccount;
    ProgressDialog progressDialog;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        buttonSignUp=(Button) findViewById(R.id.buttonsignUp);
        editTextEmail=(EditText) findViewById(R.id.editTextemail);
        editTextID=(EditText) findViewById(R.id.editTextID);
        editTextPassword=(EditText) findViewById(R.id.editTextpassword);
        editTextConfirmPassword=(EditText) findViewById(R.id.editTextconfirmPassword);
        textViewAlreadyHaveAccount=(TextView) findViewById(R.id.textViewalreadyHaveAccount);



        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getInput();
                Registration();
            }
        });


        textViewAlreadyHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrationActivity.this,LoginActivity.class));
                RegistrationActivity.this.finish();
            }
        });
    }



    @Override
    public void onBackPressed() {


        new MaterialAlertDialogBuilder(RegistrationActivity.this)
                .setTitle("Confirm")
                .setMessage("Do You Want To Exit")
                .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        RegistrationActivity.this.finish();
                    }
                })
                .setNegativeButton("No",null)
                .show();
    }




    private void getInput(){
        showLoading();
        String Email= editTextEmail.getText().toString();
        String ID=editTextID.getText().toString();
        String Password=editTextPassword.getText().toString();
        String ConfirmPassord=editTextConfirmPassword.getText().toString();

        DataVerification dataVerification =new DataVerification();
        if(dataVerification.Email(Email) && dataVerification.Password(Password)){
            if(editTextPassword.getText().toString().equals(editTextConfirmPassword.getText().toString())) {
                user = new User(editTextEmail.getText().toString(), editTextID.getText().toString(), editTextPassword.getText().toString());
                progressDialog.dismiss();
            }else{
                new MaterialAlertDialogBuilder(RegistrationActivity.this)
                        .setTitle("Opps")
                        .setMessage("Make Sure you have enter both password same")
                        .setPositiveButton("Got it", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                progressDialog.dismiss();

                            }
                        })
                        .show();
            }

        }else{
            new MaterialAlertDialogBuilder(RegistrationActivity.this)
                    .setTitle("Invalid Data")
                    .setMessage("Make Sure you used DIU provided email and a strong password")
                    .setPositiveButton("Got it", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            progressDialog.dismiss();
                        }
                    })
                    .show();
        }


    }




    private void showLoading(){
        this.progressDialog= new ProgressDialog(this);
        progressDialog.setCancelable(true);
        progressDialog.setTitle("Creating Account");
        progressDialog.show();
    }

    private void Registration(){

        showLoading();
        UserController userController= new UserController();
        DataHold.IsSuccess=false;


        user.setVcode(userController.genarateVCode());
        user.setRole(userController.getUserRole(user.getEmail(),user.getID()));

        JSONObject postparams = new JSONObject();
        try {

            postparams.put("email",user.getEmail());
            postparams.put("id",user.getID());
            postparams.put("password",user.getPassword());
            postparams.put("role",user.getRole());
            postparams.put("vCode",user.getVcode());
            postparams.put("isVerified","UnVerified");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                MyUrl.Registration, postparams,
                new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        try{

                            System.out.println(response.toString());
                            JSONObject jsonObject= new JSONObject(response.toString());
                            if(!(jsonObject==null)){


                                    EmailSender emailSender=new EmailSender();
                                    emailSender.SendVerificationEmail(user.getEmail(),user.getVcode());
                                    DataHold.IsSuccess=true;
                                    progressDialog.dismiss();
                                    new MaterialAlertDialogBuilder(RegistrationActivity.this)
                                            .setTitle("Registration Success")
                                            .setMessage("Please Confirm Your Email.You are not able to login without email verification")
                                            .setNegativeButton("ok",null)
                                            .show();


                            }
                        }catch (JSONException e) {

                            DataHold.IsSuccess=false;
                            progressDialog.dismiss();
                            new MaterialAlertDialogBuilder(RegistrationActivity.this)
                                    .setTitle("Registration Not Success")
                                    .setMessage("Something is not valid")
                                    .setNegativeButton("ok",null)
                                    .show();
                        }

                    }


                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error.toString());
                        progressDialog.dismiss();
                        new MaterialAlertDialogBuilder(RegistrationActivity.this)
                                .setTitle("Registration Not Success")
                                .setMessage("Cannot Connect Server")
                                .setNegativeButton("ok",null)
                                .show();
                    }
                });





        RequestQueue requestQueue = Volley.newRequestQueue(RegistrationActivity.this);
        requestQueue.add(jsonObjReq);

    }












}
