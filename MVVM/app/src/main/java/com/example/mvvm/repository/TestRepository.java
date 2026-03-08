package com.example.mvvm.repository;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mvvm.db.DBHelper;
import com.example.mvvm.model.TestModel;

import java.util.ArrayList;

public class TestRepository {

    public static void addData(Context context, TestModel testModel){
        DBHelper dbHepler = new DBHelper(context);
        SQLiteDatabase sqLiteDatabase = dbHepler.getWritableDatabase();

        String sql = "insert into TestTable "
                + "(textData1, textData2) "
                + "values (?, ?)";

        String [] args ={
                testModel.getTextData1(),
                testModel.getTextData2()
        };

        sqLiteDatabase.execSQL(sql, args);
        dbHepler.close();
    }

    public static ArrayList<TestModel> getDataAll(Context context){

        ArrayList<TestModel> a1 = new ArrayList<>();
        DBHelper dbHepler = new DBHelper(context);
        SQLiteDatabase sqLiteDatabase = dbHepler.getWritableDatabase();

        String sql = "select * from TestTable order by idxData desc";
        Cursor c1 = sqLiteDatabase.rawQuery(sql, null);

        while(c1.moveToNext()){
            int idx1 = c1.getColumnIndex("idxData");
            int idx2 = c1.getColumnIndex("textData1");
            int idx3 = c1.getColumnIndex("textData2");

            int idxData = c1.getInt(idx1);
            String textData1 = c1.getString(idx2);
            String textData2 = c1.getString(idx3);

            TestModel testModel = new TestModel(idxData, textData1, textData2);

            a1.add(testModel);
        }

        dbHepler.close();
        return a1;
    }
}