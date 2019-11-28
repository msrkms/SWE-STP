package com.sajidur.swe_stp;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import java.util.regex.Pattern;

public class UploadRoutineActivity extends AppCompatActivity {

    MaterialButton Upload;
    TextView locationText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_routine);

        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1001);
        }

        Upload = (MaterialButton)findViewById(R.id.btnUpload);
        locationText=(TextView)findViewById(R.id.txtFileLocation);


        Upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new MaterialFilePicker()
                        .withActivity(UploadRoutineActivity.this).withRequestCode(1000)
                        .start();

            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1000 && resultCode == RESULT_OK) {
            String filePath = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
            // Do anything with file
            locationText.setText(filePath);
        }
    }


}
