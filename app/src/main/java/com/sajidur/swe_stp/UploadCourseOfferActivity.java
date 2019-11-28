package com.sajidur.swe_stp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

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

public class UploadCourseOfferActivity extends AppCompatActivity {
    MaterialButton uploadCourse;
    TextView textfileLocationCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_course_offer);

        uploadCourse=(MaterialButton)findViewById(R.id.btnUploadCourse);
        textfileLocationCourse=(TextView)findViewById(R.id.txtFileLocationCourse);

        checkFilePermissions();

        uploadCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MaterialFilePicker()
                        .withActivity(UploadCourseOfferActivity.this)
                        .withRequestCode(1000)
                        .withFilter(Pattern.compile(".*\\.txt$")) // Filtering files and directories by file name using regexp
                        .withFilterDirectories(true) // Set directories filterable (false by default)
                        .withHiddenFiles(true) // Show hidden files and folders
                        .start();
            }
        });

    }



    private void checkFilePermissions() {
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP){
            if(ContextCompat.checkSelfPermission(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},0);
                //   .requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE}, 1001)

            }else{
                System.out.println("Already");
            }
        }else{

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1000 && resultCode == RESULT_OK) {
            String filePath = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
            // Do anything with file
            textfileLocationCourse.setText(filePath);
        }
    }

}
