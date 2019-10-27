package com.example.a3;

import android.os.AsyncTask;

public class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

    private final Contact_DAO mDao;

    PopulateDbAsync(Contact_Database db) {
        mDao = db.contactDao();
    }

    @Override
    protected Void doInBackground(final Void... params) {
        mDao.deleteAll();
        Contact_Model contact = new Contact_Model("-1", "Nabeel", "0300303030", "none", "none");
        mDao.insertContact(contact);
        return null;
    }

}

