package com.example.a3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Contact_Detail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact__detail);
        showContactDetail();
    }


    void showContactDetail(){

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String email = intent.getStringExtra("email");
        String pno = intent.getStringExtra("pno");
        String image = intent.getStringExtra("image");

        TextView txtName = findViewById(R.id.textView4);
        TextView txtPno = findViewById(R.id.textView5);
        TextView txtEmail = findViewById(R.id.textView6);
        ImageView imgImage = findViewById(R.id.imageView);

        Bitmap map = null;
        map = BitmapFactory.decodeFile(image);
        if(map != null){
            imgImage.setImageBitmap(map);
        }
        if(email.equals("")){
            email = "None";
        }

        txtEmail.setText(email);
        txtName.setText(name);
        txtPno.setText(pno);
    }
}
