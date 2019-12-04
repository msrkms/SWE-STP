package com.sajidur.swe_stp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class QueryItemViewActivity extends AppCompatActivity {

    MaterialButton yourComment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_item_view);

        yourComment=(MaterialButton)findViewById(R.id.btnComment);

        yourComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder popBuilder = new AlertDialog.Builder(QueryItemViewActivity.this);
                View popView = getLayoutInflater().inflate(R.layout.putyourcomment,null);
                TextInputEditText yourComment = (TextInputEditText)popView.findViewById(R.id.myCommentEditText);
                //code for post the comment of that Query to server
            }
        });
    }
}
