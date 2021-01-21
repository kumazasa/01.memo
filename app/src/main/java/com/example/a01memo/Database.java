package com.example.a01memo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

    private static final String DB_NAME = "memo.db";
    private static final int DB_VERSION = 1;
    private int count = 0;
    public static final String MEMO_TABLE = "MEMO";
    public static SQLiteDatabase db;

    Database(Context context) { super(context, DB_NAME, null, DB_VERSION); }

    //テーブルの初期化
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE" + MEMO_TABLE + " ("
        + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
        + "MESSAGE TEXT);");
    }
    //データベースの更新
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
    }

    //メッセージの検索
    public Cursor GetMessage(){
    //ここまで
    }

}