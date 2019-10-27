package com.example.a3;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Contact_Model")
public class Contact_Model {

    // Getter and setter for contacts
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "contactId")
    private String contactId;

    @NonNull
    @ColumnInfo(name = "contactName")
    private String contactName;

    @ColumnInfo(name = "contactNumber")
    private String contactNumber;

    @ColumnInfo(name = "contactEmail")
    private String contactEmail;

    @ColumnInfo(name = "contactPhoto")
    private String contactPhoto;

    public Contact_Model(String contactId, String contactName,
                         String contactNumber, String contactEmail, String contactPhoto) {
        this.contactId = contactId;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
        this.contactNumber = contactNumber;
        this.contactPhoto = contactPhoto;
    }

    public String getContactId() {
        return contactId;
    }

    public String getContactName() {
        return contactName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getContactPhoto() {
        return contactPhoto;
    }
}
