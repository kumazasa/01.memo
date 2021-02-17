package com.example.a01memo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> ar = new ArrayList<String>();
    private TextView textView;
    private boolean flag = false;
    private Database database;
    private SQLiteDatabase db;
    private String abc;
    private Cursor c;
    private ListView ListV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), SecondActivity.class);
                startActivity(intent);
            }
        });


        //メモをリスト表示
        ListView listV = findViewById(R.id.list);
        database = new Database(this);
        db = (new Database(this)).getWritableDatabase();
        c = db.query("memo", new String[]{"_id", "MESSAGE"}, null, null, null, null, "_id DESC");
        final SimpleCursorAdapter sca = new SimpleCursorAdapter(this, R.layout.list_name,
                c,
                new String[]{"MESSAGE"},
                new int[]{android.R.id.text1}, 0);
        listV.setAdapter(sca);

        //リストの設定
        AdapterView.OnItemClickListener icl = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int p, long l) {
                abc = sca.getCursor().getString(1);
                Intent it = new Intent(MainActivity.this, SecondActivity.class);
                it.putExtra("message", abc);
                it.putExtra("position", p);
                startActivity(it);
            }

        };
        listV.setOnItemClickListener(icl);
    }

    //コールバックメソッド
    @Override
    public void onPause() {
        super.onPause();
    }

    public void onDestroy() {
        super.onDestroy();
        c.close();
        db.close();
    }

    //戻るボタンが標準の動きをする
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }


}









