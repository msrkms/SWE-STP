package com.sajidur.swe_stp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class EventAddActivity extends AppCompatActivity {

    private static final int ImageTraceBack=1;
    private StorageReference firebaseFolder;
    private Button buttonUpload;
    private ImageView imageViewEventImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_add);

        buttonUpload=(Button) findViewById(R.id.buttonSave);
         imageViewEventImage =(ImageView) findViewById(R.id.imageViewEventImage);
        firebaseFolder= FirebaseStorage.getInstance().getReference().child("EventImages");



        imageViewEventImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseEventImage();
            }
        });
    }

    private void chooseEventImage(){
        Intent intent   = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent,ImageTraceBack);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==ImageTraceBack){
            if(resultCode==RESULT_OK){
                Uri uriImage=data.getData();
                final StorageReference ImageName=firebaseFolder.child("image"+ UUID.randomUUID().toString());
                ImageName.putFile(uriImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        ImageName.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                System.out.println(uri);
                            }
                        });
                    }
                });

            }
        }
    }
}
