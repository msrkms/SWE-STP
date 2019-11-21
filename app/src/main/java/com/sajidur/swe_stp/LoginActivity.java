package com.sajidur.swe_stp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {
    private MaterialButton buttonLogin;
    private TextInputEditText textInputEditTextEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        buttonLogin=(MaterialButton) findViewById(R.id.buttonLogin);
        textInputEditTextEmail=(TextInputEditText) findViewById(R.id.editTextEmail);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email=textInputEditTextEmail.getText().toString();

                if(Email.equals("admin")){
                    intentToAdmin();
                }else if(Email.equals("student")){
                    intentToStudent();
                }
            }
        });


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
