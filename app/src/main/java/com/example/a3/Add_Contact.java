package com.example.a3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class Add_Contact extends AppCompatActivity {

    private static final int PICK_IMAGE = 100;
    Uri imageUri;
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;
    ImageView src, actionImage;
    TextView name, email, number;
    Button btn;
    private Contact_ViewModel mContactViewModel;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__contact);
        mContactViewModel = new ViewModelProvider(this).get(Contact_ViewModel.class);
        src = findViewById(R.id.newPhoto);
        actionImage = findViewById(R.id.imageView4);
        loadImage();
        btn = findViewById(R.id.saveContactBtn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path;
                name = findViewById(R.id.newName);
                email = findViewById(R.id.newEmail);
                number = findViewById(R.id.newNumber);
                File cacheDirectory = getBaseContext()
                        .getCacheDir();
                File tmp = new File(cacheDirectory.getPath()
                        + "/_nabeel" + number.getText().toString() + ".png");
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(
                            tmp);
                    bitmap.compress(Bitmap.CompressFormat.PNG,
                            100, fileOutputStream);
                    fileOutputStream.flush();
                    fileOutputStream.close();
                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
                path = tmp.getPath();
                Contact_Model obj = new Contact_Model(number.getText().toString(), name.getText().toString(),
                        number.getText().toString(), email.getText().toString(), path);
                new addContact().execute(obj);
                Display_Contacts.arrayList.add(obj);
            }
        });
    }


    void loadImage(){
        actionImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(Add_Contact.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(Add_Contact.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            MY_PERMISSIONS_REQUEST_READ_CONTACTS);
                }
                else {
                    openGallery();
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openGallery();
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
        }
    }

    void openGallery(){
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data.getData();
            src.setImageURI(imageUri);
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private class addContact extends AsyncTask<Contact_Model, Void, Void> {

        @Override
        protected Void doInBackground(Contact_Model... contact_models) {
            addAccount(contact_models[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

        super.onPostExecute(result);
            Toast toast = Toast.makeText(Add_Contact.this,
                    "Contact Added Successfully",
                    Toast.LENGTH_SHORT);
            toast.show();
        }

        private void addAccount(Contact_Model idd){
            mContactViewModel.insert(idd);
        }
    }

}
