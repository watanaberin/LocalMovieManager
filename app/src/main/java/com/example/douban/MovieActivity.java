package com.example.douban;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.DBHelper.DBOpenHelper;

public class MovieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        DBOpenHelper mDBOpenHelper = new DBOpenHelper(this);

        TextView showname=(TextView)findViewById(R.id.showName);
        TextView shownote=(TextView) findViewById(R.id.showNote);
        TextView showintro=(TextView)findViewById(R.id.showIntro);
        Intent i=getIntent();
        showname.setText(i.getStringExtra("name"));
        /**showpic.setImageResource();**/
        showintro.setText(i.getStringExtra("intro"));
        showintro.setText(i.getStringExtra("note"));

    }
    public boolean onCreateOptionsMenu(Menu menu){
        menu.add(0,1,1,"首页");
        menu.add(0,2,2,"小组");
        menu.add(0,3,3,"个人");
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id=item.getItemId();
        switch(id){
            case 1:
                Intent i=new Intent(MovieActivity.this,HomeActivity.class);
                startActivity(i);
                break;
            case 2:
                Intent i1=new Intent(MovieActivity.this,GroupActivity.class);
                startActivity(i1);
                break;
            case 3:
                Intent i2=new Intent(MovieActivity.this,ModifyActivity.class);
                startActivity(i2);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
