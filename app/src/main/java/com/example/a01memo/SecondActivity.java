package com.example.a01memo;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class SecondActivity extends AppCompatActivity {

    int TEXT_POSITION = -1;
    SQLiteDatabase db;
    SQLiteOpenHelper help;
    String msg;
    String _message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);


        //DBの初期化
        help = new Database(this);
        db = help.getWritableDatabase();

        if (getIntent().getExtras() != null) {
            Intent intent = getIntent();
            _message = intent.getStringExtra("message");
            TEXT_POSITION = intent.getIntExtra("position", -1);
            EditText layout = (EditText) findViewById(R.id.message);
            layout.setText(_message);
        }
    }

    //タイトルバー
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //削除、保存ボタンの処理
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.trash_memo:
                alert();
                return true;

            case R.id.save_memo:
                onClickSave(findViewById(R.id.save));

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onClickSave(View view) {

        //保存後に画面下に文字をトースト表示
        CharSequence text = "保存されました";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(this, text, duration);
        toast.show();


        //テキストの位置
        if (TEXT_POSITION != -1) {
            ((Database) help).sakuzyo(_message);
        }


        //入力内容の取得
        EditText message = (EditText) findViewById(R.id.message);
        String messageText = message.getText().toString();

        //入力内容の受け渡し
        boolean insertData = ((Database) help).sounyu(messageText);
        Intent intent = new Intent(SecondActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void onClickDelete(View view) {
        alert();
    }

    //削除ポップアップ
    public void alert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("削除確認");
        builder.setMessage("本当に削除しますか?");
        builder.setPositiveButton("はい", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                ((Database) help).sakuzyo(_message);
                CharSequence text = "削除されました";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(SecondActivity.this, text, duration);

                toast.show();

                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent);
            }
        })
                .setNegativeButton("いいえ", null)
                .show();
    }


}

