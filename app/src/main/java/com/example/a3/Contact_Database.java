package com.example.a3;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Contact_Model.class}, version = 1)
public abstract class Contact_Database extends RoomDatabase {

    public abstract Contact_DAO contactDao();

    private static volatile Contact_Database INSTANCE;

    static Contact_Database getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (Contact_Database.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            Contact_Database.class, "contact_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

//    private static RoomDatabase.Callback sRoomDatabaseCallback =
//            new RoomDatabase.Callback(){
//
//                @Override
//                public void onOpen (@NonNull SupportSQLiteDatabase db){
//                    super.onOpen(db);
//                    new PopulateDbAsync(INSTANCE).execute();
//                }
//            };

}
