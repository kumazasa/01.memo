package com.example.a01memo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

    private static final String DB_NAME = "memo.db";
    private static final int DB_VERSION = 3;
    private int count = 0;
    public static final String MEMO_TABLE = "memo";
    public static SQLiteDatabase db;

    Database(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    //テーブルの初期化
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + MEMO_TABLE + " ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "MESSAGE TEXT);");
    }

    //データベースの更新
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
    }

    //メッセージの取り出し
    public Cursor Uketori() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(" SELECT * FROM " + MEMO_TABLE, null);
        return c;
    }

    //メッセージの取り出し（並べ替え)
    public Cursor SortUketori(String string) {
        db = this.getWritableDatabase();
        Cursor c = db.query(MEMO_TABLE, new String[]{"MESSAGE"}, "MESSAGE = ?", new String[]{string}, null, null, null);
        return c;
    }

    //メモ挿入部分
    public boolean sounyu(String a) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MESSAGE", a);
        long b = db.insert("memo", null, values);
        if (b < 0) {
            return false;
        } else {
            return true;
        }
    }

    //削除
    public void sakuzyo(String d) {
        db = this.getWritableDatabase();
        db.delete("memo", "MESSAGE = ?", new String[]{d});
    }


}