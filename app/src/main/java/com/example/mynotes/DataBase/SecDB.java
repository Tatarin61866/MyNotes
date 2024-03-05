package com.example.mynotes.DataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mynotes.Models.Notes;

@Database(entities = Notes.class, version = 1, exportSchema = false)
public abstract class SecDB extends RoomDatabase {

        private static SecDB database;
        private static String DATABASE_NAME = "NoteApp";

        public  synchronized static SecDB getInstancec(Context context){
            if (database == null) {
                database = Room.databaseBuilder(context.getApplicationContext(), SecDB.class, DATABASE_NAME)
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build();
            }
            return database;
        }
        public abstract mainDB mainDB();



}
