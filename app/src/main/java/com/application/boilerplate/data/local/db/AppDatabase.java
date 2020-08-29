package com.application.boilerplate.data.local.db;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.application.boilerplate.data.local.db.dao.EmployeeDao;
import com.application.boilerplate.data.model.db.Employee;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Database(entities = {Employee.class}, exportSchema = false, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public static final String DB_NAME = "employee_db";
    private static AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context, AppDatabase.class, DB_NAME)
                    .fallbackToDestructiveMigration().build();
        }
        return instance;
    }


    public static synchronized void resetInstance(){
        instance = null;
    }

    public abstract EmployeeDao employeeDao();
}
