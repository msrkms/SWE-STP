package com.sajidur.swe_stp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;

public class HelpPostListActivity extends AppCompatActivity {

    MaterialButton submitQuery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_post_list);

        submitQuery=(MaterialButton)findViewById(R.id.btnSubmitQuery);

        submitQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HelpPostListActivity.this,AddQueryActivity.class));
            }
        });
    }
}
