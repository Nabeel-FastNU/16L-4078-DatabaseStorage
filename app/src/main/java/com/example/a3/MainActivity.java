package com.example.a3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getSharedPreferences("AppPreferences", Context.MODE_PRIVATE);
        int check_id = sharedPreferences.getInt("check_id", -1);
        if(check_id == 1){
            Intent intent = new Intent(getBaseContext(), Display_Contacts.class);
            startActivity(intent);
        }
        else{
            setContentView(R.layout.activity_main);
            login();
        }
    }

    private void login(){
        Button loginBtn = findViewById(R.id.loginBtn);
        final CheckBox check = findViewById(R.id.checkBox);
        final TextView username = findViewById(R.id.username);
        final TextView password = findViewById(R.id.password);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(check.isChecked()){
                    SharedPreferences sharedPreferences = getSharedPreferences("AppPreferences", Context.MODE_PRIVATE);
                    SharedPreferences.Editor my_editor = sharedPreferences.edit();
                    my_editor.putInt("check_id", 1);
                    my_editor.putString("username", username.getText().toString());
                    my_editor.putString("password", password.getText().toString());
                    my_editor.commit();
                }
                else {
                    SharedPreferences sharedPreferences = getSharedPreferences("AppPreferences", Context.MODE_PRIVATE);
                    SharedPreferences.Editor my_editor=sharedPreferences.edit();
                    my_editor.putInt("check_id", 0);
                    my_editor.commit();
                }
                Intent intent = new Intent(getBaseContext(), Display_Contacts.class);
                intent.putExtra("username", username.getText().toString());
                intent.putExtra("password", password.getText().toString());
                startActivity(intent);
            }
        });
    }
}

//    void logout(){
//        Button btn = findViewById(R.id.BtnSignOut);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SharedPreferences sharedPreferences = getSharedPreferences("AppPreferences", Context.MODE_PRIVATE);
//                SharedPreferences.Editor my_editor = sharedPreferences.edit();
//                my_editor.putInt("check_id", 0);
//                my_editor.commit();
//                Intent intent = new Intent(getBaseContext(), MainActivity.class);
//                startActivity(intent);
//            }
//        });
//    }




