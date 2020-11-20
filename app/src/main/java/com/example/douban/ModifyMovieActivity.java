package com.example.douban;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.DBHelper.DBOpenHelper;

public class ModifyMovieActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText modifyName;
    private EditText modifyIntro;
    private EditText modifyNote;
    private Button modify_m_confirm;
    private Button modify_m_cancel;
    private DBOpenHelper dbOpenHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_movie);
        dbOpenHelper=new DBOpenHelper(this);
        modifyName=(EditText)findViewById(R.id.modify_movie_name);
        modifyIntro=(EditText)findViewById(R.id.modify_movie_intro);
        modifyNote=(EditText)findViewById(R.id.modify_movie_note);
        modify_m_confirm=(Button)findViewById(R.id.modify_m_confirm);
        modify_m_cancel=(Button)findViewById(R.id.modify_m_cancel);
        Intent intent=getIntent();
        String name=intent.getStringExtra("name");
        String intro=intent.getStringExtra("intro");
        String note=intent.getStringExtra("note");
        modifyName.setText(name);
        modifyIntro.setText(intro);
        modifyNote.setText(note);
        modify_m_confirm.setOnClickListener(this);
        modify_m_cancel.setOnClickListener(this);
    }
    public void onClick(View view) {
    switch (view.getId()){
        case R.id.modify_m_confirm:
            String modify_name=modifyName.getText().toString();
            String modify_intro=modifyIntro.getText().toString();
            String modify_note=modifyNote.getText().toString();
            SQLiteDatabase db = dbOpenHelper.getWritableDatabase();

            if(modify_name!=null &&modify_intro!=null)
            {

                ContentValues cv = new ContentValues();
                cv.put("intro",modify_intro);
                cv.put("note",modify_note);
                db.update("movie",cv,"name=?",new String[]{modify_name});
                Toast.makeText(this,"更新阅片记录成功！返回主界面",Toast.LENGTH_SHORT).show();
                Intent tomain =new Intent(ModifyMovieActivity.this,HomeActivity.class);
                startActivity(tomain);
            }
            break;



        case R.id.modify_m_cancel:
            Intent i=new Intent(ModifyMovieActivity.this,HomeActivity.class);
            startActivity(i);
    }


    }
    }
