package com.example.a10146.demo2.TesQuestion;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LGC on 2018/5/25.
 */
public class DBService {
    private   final String tag="20180707";
    private SQLiteDatabase db;



    //构造方法
    public DBService(int type) {

        //连接数据库
        String Datapath =String.format( "/data/data/com.example.a10146.demo2/databases/question%02d.db",type) ;
        db = SQLiteDatabase.openDatabase(Datapath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    //获取数据库的数据
    public List<Question> getQuestion() {
        List<Question> list = new ArrayList<>();
        //执行sql语句
        Cursor cursor = db.rawQuery("select * from question", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int count = cursor.getCount();
            //遍历
            for (int i = 0; i < count; i++) {
                cursor.moveToPosition(i);
                Question question = new Question();
                //ID
                question.ID = cursor.getInt(cursor.getColumnIndex("Field1"));
                //问题
                question.question = cursor.getString(cursor.getColumnIndex("Field2"));
                //四个选择
                question.answerA = cursor.getString(cursor.getColumnIndex("Field3"));
                question.answerB = cursor.getString(cursor.getColumnIndex("Field4"));
                question.answerC = cursor.getString(cursor.getColumnIndex("Field5"));
                question.answerD = cursor.getString(cursor.getColumnIndex("Field6"));
                //答案
                question.answer = cursor.getInt(cursor.getColumnIndex("Field7"));
                //解析
                question.explaination = cursor.getString(cursor.getColumnIndex("Field8"));
                //设置为没有选择任何选项
                question.selectedAnswer = -1;
                list.add(question);
            }
        }
        return list;

    }



}
