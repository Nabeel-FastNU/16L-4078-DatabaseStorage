package com.example.a3;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class Contact_Repository {

    private Contact_DAO contactDao;
    private LiveData<List<Contact_Model>> allContacts;
    private List<Contact_Model> databaseContacts;

    Contact_Repository(Application application) {
        Contact_Database db = Contact_Database.getDatabase(application);
        contactDao = db.contactDao();
        allContacts = contactDao.getAllContacts();
    }

    LiveData<List<Contact_Model>> getContacts() {
        return allContacts;
    }

    List<Contact_Model> getDatabaseContacts(){
        if(databaseContacts == null)
            databaseContacts = contactDao.databaseContacts();
        return databaseContacts;
    }

    int deleteContactByUser(String id){
        return contactDao.deleteContactByUser(id);
    }


    public void insert (Contact_Model contact) {
        new insertAsyncTask(contactDao).execute(contact);
    }

    private static class insertAsyncTask extends AsyncTask<Contact_Model, Void, Void> {

        private Contact_DAO mAsyncTaskDao;

        insertAsyncTask(Contact_DAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Contact_Model... params) {
            mAsyncTaskDao.insertContact(params[0]);
            return null;
        }
    }
}
