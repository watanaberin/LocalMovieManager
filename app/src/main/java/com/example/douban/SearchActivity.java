package com.example.douban;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.DBHelper.DBOpenHelper;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    private SearchView mSearchView;
    private ListView mListView;
    private DBOpenHelper dbOpenHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        dbOpenHelper = new DBOpenHelper(this);

        init();

    }
    public void init(){
        mSearchView = (SearchView) findViewById(R.id.searchView);
        mListView = (ListView) findViewById(R.id.listView);
        ArrayList<String> mDatas=dbOpenHelper.selectName();
        mListView.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, mDatas));
        mListView.setTextFilterEnabled(true);

        // 设置搜索文本监听
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // 当点击搜索按钮时触发该方法
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            // 当搜索内容改变时触发该方法
            @Override
            public boolean onQueryTextChange(String newText) {
                if (!TextUtils.isEmpty(newText)){
                    mListView.setFilterText(newText);
                }else{
                    mListView.clearTextFilter();
                }
                return false;
            }
        });

    }
    public boolean onCreateOptionsMenu(Menu menu){
        menu.add(0,1,1,"首页");
        menu.add(0,2,2,"广播");
        menu.add(0,3,3,"小组");
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id=item.getItemId();
        switch(id){
            case 1:
                Intent i=new Intent(SearchActivity.this,HomeActivity.class);
                startActivity(i);
                break;
            case 2:
                Intent i1=new Intent(SearchActivity.this,GroupActivity.class);
                startActivity(i1);
                break;
            case 3:
                Intent i2=new Intent(SearchActivity.this,ModifyActivity.class);
                startActivity(i2);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
