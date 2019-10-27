package com.example.a3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Remove_Contact extends AppCompatActivity {

    private Contact_ViewModel mContactViewModel;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove__contact);
        mContactViewModel = new ViewModelProvider(this).get(Contact_ViewModel.class);
        final EditText txt = findViewById(R.id.remTxt);
        Button btn = findViewById(R.id.removeBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = txt.getText().toString();
                new deleteContacts().execute(id);
            }
        });
    }



    private class deleteContacts extends AsyncTask<String, Void, Void> {

        private int val;

        @Override
        protected Void doInBackground(String... strings) {
            removeAccount(strings[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

            super.onPostExecute(result);
            if(val > 0){
                Toast toast = Toast.makeText(Remove_Contact.this,
                        "Contact Deleted Successfully",
                        Toast.LENGTH_SHORT);

                toast.show();
            }
            else{
                Toast toast = Toast.makeText(Remove_Contact.this,
                        "Contact Not Found",
                        Toast.LENGTH_SHORT);

                toast.show();
            }
        }

        private void removeAccount(String idd){
            val = mContactViewModel.deleteContactByUser(idd);
        }
    }
}
