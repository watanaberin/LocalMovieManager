package com.example.douban;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.adapter.GroupAdapter;
import com.example.utils.Group;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class GroupActivity extends AppCompatActivity {
    private List<Group> groupList = new ArrayList<Group>();
    private ListView listView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        initGroup(); // 初始化数据
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recycler_view) ;
        StaggeredGridLayoutManager layoutManager=new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        GroupAdapter adapter = new GroupAdapter(groupList);//实例化Adapter
        recyclerView.setAdapter(adapter);//设置Adapter


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
                Intent i=new Intent(GroupActivity.this,HomeActivity.class);
                startActivity(i);
                break;
            case 2:
                Intent i1=new Intent(GroupActivity.this,GroupActivity.class);
                startActivity(i1);
                break;
            case 3:
                Intent i2=new Intent(GroupActivity.this,ModifyActivity.class);
                startActivity(i2);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    private void initGroup()
    {
        for(int i=0;i<5;i++)
        {
            Group mov1 = new Group(getRandomLengthName("banana"),R.drawable.movie1,""); //添加图片
            groupList.add(mov1);
            Group mov2 = new Group(getRandomLengthName("apple"),R.drawable.movie2,""); //添加图片
            groupList.add(mov2);
            Group mov3 = new Group(getRandomLengthName("orange"),R.drawable.movie3,""); //添加图片
            groupList.add(mov3);
            Group mov4 = new Group(getRandomLengthName("tomato"),R.drawable.movie4,""); //添加图片
            groupList.add(mov4);
            Group mov5 = new Group(getRandomLengthName("yesok"),R.drawable.movie5,""); //添加图片
            groupList.add(mov5);
        }


    }

    private String getRandomLengthName(String name) {
        Random random = new Random();
        int length = random.nextInt(20) + 1;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(name);
        }
        return builder.toString();
    }
}