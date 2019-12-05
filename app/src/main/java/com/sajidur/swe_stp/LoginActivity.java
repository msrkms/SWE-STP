package com.sajidur.swe_stp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {
    private MaterialButton buttonLogin;
    private TextView textViewSignUp,textViewForgetPassword;
    private TextInputEditText textInputEditTextEmail,textInputEditTextPassword;

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
                String Email=textInputEditTextEmail.getText().toString();
                String Password=textInputEditTextPassword.getText().toString();

            }
        });







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

    private void intentToAdmin(){
        startActivity(new Intent(LoginActivity.this,AdminDashboardActivity.class));
        LoginActivity.this.finish();
    }

    private void intentToStudent(){
        startActivity(new Intent(LoginActivity.this,StudentDashboardActivity.class));
        LoginActivity.this.finish();
    }

}
