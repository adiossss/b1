package com.example.myapplication0613;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
     Button b1, b2,b3,b4;
        TextView end;
       ImageView imageView,imageView2;
         Intent intent;
        int PICK_CONTACT_REQUEST=1;
        Uri uri;
        String data_list;
        StorageReference storageReference,pic_storage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      b1=(Button) findViewById(R.id.b1);
      b2=(Button) findViewById(R.id.b2);
      b3=(Button) findViewById(R.id.b3);
      b4=(Button) findViewById(R.id.b4);
      end=(TextView) findViewById(R.id.end);
      imageView=(ImageView) findViewById(R.id.imageView);
      imageView2=(ImageView) findViewById(R.id.imageView2);
      b2.setEnabled(false);
      b3.setEnabled(false);


      storageReference=FirebaseStorage.getInstance().getReference();
      b1.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              intent=new Intent();
              intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
              intent.setType("image/*");
              intent.setAction(Intent.ACTION_GET_CONTENT);
              startActivityForResult(intent,1);
              b2.setEnabled(true);
              b3.setEnabled(true);
              b4.setEnabled(true);
          }
      });

b2.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        pic_storage=storageReference.child("m4."data_list);
pic_storage.putFile(Uri).addOnSuccessListener(new OnSuccessListener<UploadTask.Tastsnapshot>){
    @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot){
        end.setText("????????????");

            }
      }    }
});
b3.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        pic_storage=storageReference.child("m4."+data_list);
        fine File file;
        try {


        file= File.createTempFile("images","png");
        pic_storage.getFile(file).addOnSuccessListener(new OnSuccessListener<UploadTask.Tastsnapshot>)
        {
            @Override
            public void onSuccess (UploadTask.TaskSnapshot taskSnapshot){
            imageView2.setImageURI(Uri.fromFile(file));
            end.setText("??????:????????????");

        }
        }).addOnFailureListener(new OnFailureListener(){
            @Override
            public void onFailure(@NonNull Exception e){
                end.setText("??????:????????????");

            }
        });
    }catch(IOException exception){
        exception.printStackTrace();
    }

    }
});
b4.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        pic_storage=storageReference.child("m4."data_list);
        pic_storage.delete();
        end.setText("??????:????????????");
        imageView.setImageURI(null);
        imageView2.setImageURI(null);
    }
});
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
if(requestCode==PICK_CONTACT_REQUEST){
    uri=data.getData();
    imageView.setImageURI(uri);
    ContentResolver contentResolver=getContentResolver();
    MimeTypeMap mimeTypeMap=MimeTypeMap.getSingleton();
    data_list=mimeTypeMap.getExtensionFromMimeType((contentResolver.getType(uri)));

}
        super.onActivityResult(requestCode,resultCode,data);

    }


}