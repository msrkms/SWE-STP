package com.sajidur.swe_stp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.sajidur.swe_stp.Backend.DataHold;
import com.sajidur.swe_stp.Backend.Model.User;
import com.sajidur.swe_stp.Backend.MyUrl;

import org.json.JSONException;
import org.json.JSONObject;

public class VerifyAccountActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private  String Email,Vcode;
    private TextInputEditText textInputEditTextEmail,textInputEditTextVCode;
    private MaterialButton materialButtonVerify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_account);


        textInputEditTextEmail=(TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        textInputEditTextVCode=(TextInputEditText) findViewById(R.id.textInputEditTextVerificationCode) ;
        materialButtonVerify=(MaterialButton) findViewById(R.id.buttonVerify);
        materialButtonVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Email=textInputEditTextEmail.getText().toString();
                Vcode=textInputEditTextVCode.getText().toString();

                tryVerify(Email,Vcode);


            }
        });
    }



    private void tryVerify( String Email, String VCode){
        showLoading();




        JSONObject postparams = new JSONObject();
        try {

            postparams.put("email",Email);
            postparams.put("vCode",VCode);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, MyUrl.Verification,postparams,
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
                            user.setIsVerified(dataobj.getString("isVerified"));


                            if(user.getIsVerified().equals("Verified")){
                                new MaterialAlertDialogBuilder(VerifyAccountActivity.this)
                                        .setTitle("Verified")
                                        .setMessage("Account Is Verified")
                                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                progressDialog.dismiss();
                                            }
                                        })
                                        .show();

                            }else{
                                new MaterialAlertDialogBuilder(VerifyAccountActivity.this)
                                        .setTitle("Cannot Verify")
                                        .setMessage("Information is not right")
                                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                progressDialog.dismiss();
                                            }
                                        })
                                        .show();

                            }




                        } catch (JSONException e) {
                            e.printStackTrace();

                            new MaterialAlertDialogBuilder(VerifyAccountActivity.this)
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
                        new MaterialAlertDialogBuilder(VerifyAccountActivity.this)
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
}
