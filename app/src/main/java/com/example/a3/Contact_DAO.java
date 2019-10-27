package com.example.a3;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface Contact_DAO {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertContact(Contact_Model contact);

    @Query("DELETE from Contact_Model where contactName = :userId")
    int deleteContactByUser(String userId);

    @Query("SELECT* from Contact_Model")
    LiveData<List<Contact_Model>> getAllContacts();

    @Query("SELECT contactId, contactName, contactNumber, contactEmail, contactPhoto from Contact_Model")
    List<Contact_Model> databaseContacts();

    @Query("DELETE FROM Contact_Model")
    void deleteAll();
}
