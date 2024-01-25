package com.example.todoapp.Utils;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.todoapp.Model.ToDoModel;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static int VERSION=1;
    private static String DB_NAME="tododatabase";
    private static String TABLE_NAME="todotable";
    private static String ID="id";
    private static String TASK="task";
    private static String STATUS="status";

    private static String CREATE_TABLE="CREATE TABLE "+TABLE_NAME+" ("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                                        + TASK+" TEXT,"+STATUS+" INTEGER)";

    private SQLiteDatabase db;
    private DatabaseHandler(Context context){
        super(context,DB_NAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //Drop table
        sqLiteDatabase.execSQL(TABLE_NAME);
        //create table
        onCreate(sqLiteDatabase);
    }

    public void openDatabase(){
        db=this.getWritableDatabase();
    }

    @SuppressLint("Range")
    public List<ToDoModel> getAlltasks(){
        List<ToDoModel> list=new ArrayList<>();
        Cursor cursor=null;


        db.beginTransaction();

        try {
            cursor=db.query(TABLE_NAME,null,null,null,null,null,null);
            if(cursor!=null){
                if(cursor.moveToFirst()) {
                    do {
                        ToDoModel task = new ToDoModel();
                        task.setId(cursor.getInt(cursor.getInt(cursor.getColumnIndex(ID))));
                        task.setTask(cursor.getString(cursor.getColumnIndex(TASK)));
                        task.setStatus(cursor.getInt(cursor.getColumnIndex(STATUS)));
                        list.add(task);
                    }while (cursor.moveToNext());
                }
            }
        }
        finally {
            db.endTransaction();
            cursor.close();
        }
        return list;
    }

    public void insertTask(ToDoModel task){
        ContentValues cv=new ContentValues();
        cv.put(TASK,task.getTask());
        cv.put(STATUS,0);
        db.insert(TABLE_NAME,null,cv);
    }
    public void updateStatus(int id, int status){
        ContentValues cv=new ContentValues();
        cv.put(STATUS,status);
        db.update(TABLE_NAME,cv,ID+"=?",new String[]{String.valueOf(id)});
    }
    public void updateTask(int id, String task){
        ContentValues cv=new ContentValues();
        cv.put(TASK,task);
        db.update(TABLE_NAME,cv,ID+"=?",new String[]{String.valueOf(id)});
    }
    public void delete(int id){
        db.delete(TABLE_NAME,ID+"=?",new String[]{String.valueOf(id)});
    }
}
