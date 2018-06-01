package com.example.a10146.demo2.ProQuestion;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 10146 on 2018/5/30.
 */

public class AnalysisDB {
    private SQLiteDatabase db;

    //构造方法
    public AnalysisDB() {
        //连接数据库
        db = SQLiteDatabase.openDatabase("/data/data/com.example.a10146.demo2/databases/Analysis.db", null, SQLiteDatabase.OPEN_READWRITE);

    }

    //获取数据库的数据
    public List<Analysis> getAnalysis() {
        List<Analysis> list = new ArrayList<>();
        //执行sql语句
        Cursor cursor = db.rawQuery("select * from question", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int count = cursor.getCount();
            //遍历
            for (int i = 0; i < count; i++) {
                cursor.moveToPosition(i);
                Analysis analysis = new Analysis();
                //ID
                analysis.ID = cursor.getInt(cursor.getColumnIndex("Field1"));

                //解析
                analysis.Analysis = cursor.getString(cursor.getColumnIndex("Field2"));

                list.add(analysis);
            }
        }
        return list;

    }
}
