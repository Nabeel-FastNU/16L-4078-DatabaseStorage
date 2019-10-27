package com.example.a3;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class Contact_ViewModel extends AndroidViewModel {

    private Contact_Repository mRepository;

    private LiveData<List<Contact_Model>> mAllContacts;

    private List<Contact_Model> databaseContacts;

    public Contact_ViewModel (Application application) {
        super(application);
        mRepository = new Contact_Repository(application);
        mAllContacts = mRepository.getContacts();
    }

    LiveData<List<Contact_Model>> getContacts() { return mAllContacts; }
    List<Contact_Model> getDatabaseContacts(){

        if(databaseContacts == null)
            databaseContacts = mRepository.getDatabaseContacts();
        return databaseContacts;
    }

    int deleteContactByUser(String id){
        return mRepository.deleteContactByUser(id);
    }

    public void insert(Contact_Model contact) { mRepository.insert(contact); }

}
