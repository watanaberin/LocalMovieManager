package com.example.douban;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.DBHelper.DBOpenHelper;

public class AddMovieActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText name;
    private EditText intro;
    private EditText note;
    private DBOpenHelper mDBOpenHelper;
    private Button add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);
        name=(EditText)findViewById(R.id.add_movie_name);
        intro=(EditText)findViewById(R.id.add_movie_intro);
        note=(EditText)findViewById(R.id.add_movie_note);
        add=(Button)findViewById(R.id.add_submit) ;
        add.setOnClickListener(this);
    }
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_submit:
                mDBOpenHelper = new DBOpenHelper(this);

                String add_name = name.getText().toString();
                String add_intro = intro.getText().toString();
                String add_note = note.getText().toString();
                if (add_name.equals("") || add_intro.equals("")) {
                    Toast.makeText(this, "您输入的信息不完整！", Toast.LENGTH_SHORT).show();}
                else
                    {
                        mDBOpenHelper.addM(add_name, add_intro, add_note);
                        Toast.makeText(this, "增加观影记录成功", Toast.LENGTH_SHORT).show();
                        Intent intent2 = new Intent(this, HomeActivity.class);
                        startActivity(intent2);
                    }
                break;
            case R.id.add_cancel:
                Intent intent4 = new Intent(this, HomeActivity.class);
                startActivity(intent4);
                break;
        }
        }
    }

