package com.example.douban;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.DBHelper.DBOpenHelper;
import com.example.adapter.MovieAdapter;
import com.example.utils.Movie;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity  {
    private List<Movie> movieList = new ArrayList<Movie>();
    private ListView listView = null;
    private SQLiteDatabase db;
    private DBOpenHelper mDBOpenHelper;
    private List<String> lists = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        MovieAdapter adapter = new MovieAdapter(HomeActivity.this, R.layout.simple_item, movieList);//实例化Adapter
        listView = (ListView) findViewById(R.id.firstlist);//绑定Listview
        listView.setAdapter(movie_adapter);//设置Adapter
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movie movie = movieList.get(position);
                Toast.makeText(HomeActivity.this, "点击！", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(HomeActivity.this, ModifyMovieActivity.class);
                i.putExtra("name", movie.getName());
                i.putExtra("note", movie.getNote());
                i.putExtra("intro", movie.getIntroduction());
                startActivity(i);
            }
        });
    }

    protected void onResume() {
        movieList = new ArrayList<Movie>();
        mDBOpenHelper = new DBOpenHelper(this);
        ArrayList<Movie> data=mDBOpenHelper.getAllMovie();
        Movie movie=new Movie();
        for(int i=0;i<data.size();i++)
        {
            movie=data.get(i);
            movieList.add(movie);
        }
        MovieAdapter movieAdapter=new MovieAdapter(HomeActivity.this, R.layout.simple_item, movieList);
        listView = (ListView) findViewById(R.id.firstlist);//绑定Listview
        listView.setAdapter(movie_adapter);//设置Adapter

        movieAdapter.notifyDataSetChanged();
        super.onResume();
    }
    BaseAdapter movie_adapter= new BaseAdapter(){

        @Override
        public View getView(int arg0, View arg1, ViewGroup arg2) {
            View view=LayoutInflater.from(HomeActivity.this).inflate(R.layout.simple_item,null);
            final int position=arg0;

                CheckBox movie_checkbox = (CheckBox) view.findViewById(R.id.stu_checkbox);
                TextView movieIntro=(TextView) view.findViewById(R.id.intro);
                TextView movieName=(TextView) view.findViewById(R.id.name);
                movie_checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,
                                                 boolean isChecked) {
                        // TODO Auto-generated method stub
                        movieList.get(position).setChecked(isChecked);

                    }
                });
            movieIntro.setText(movieList.get(arg0).getIntroduction());
            movieName.setText(movieList.get(arg0).getName());
            return view;
            /** View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
             ImageView movieImage = (ImageView) view.findViewById(R.id.pic);
             TextView movieName = (TextView) view.findViewById(R.id.name);
             TextView movieIntro = (TextView) view.findViewById(R.id.intro);
             movieImage.setImageResource(movie.getImageId());
             movieName.setText(movie.getName());
             movieIntro.setText(movie.getIntroduction());
             return view;**/
        }
        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return null;
        }

        public int getCount() {
            // TODO Auto-generated method stub
            return movieList.size();
        }
    };





    //按钮
        public boolean onCreateOptionsMenu (Menu menu){
            menu.add(0, 1, 1, "首页");
            menu.add(0, 2, 2, "新增");
            menu.add(0, 3, 3, "删除");
            menu.add(0, 4, 4, "小组");
            menu.add(0, 5, 5, "个人");
            menu.add(0, 6, 6, "搜索");

            return super.onCreateOptionsMenu(menu);
        }

        public boolean onOptionsItemSelected (MenuItem item){
            int id = item.getItemId();
            switch (id) {
                case 1:
                    Intent i = new Intent(HomeActivity.this, HomeActivity.class);
                    startActivity(i);
                    break;
                case 2:
                    Intent idd = new Intent(HomeActivity.this, AddMovieActivity.class);
                    startActivity(idd);
                    break;
                case 3:
                    Movie movie=new Movie();
                    for(int j=0;j<movieList.size();j++)
                    {
                        movie=movieList.get(j);
                        if(movie.isChecked())
                        {
                            mDBOpenHelper.deleteM(movie.getName());
                            Toast.makeText(this, "删除 "+movie.getName()+"成功！", Toast.LENGTH_SHORT).show();
                        }
                    }
                    onResume();
                    break;
                case 4:
                    Intent i11 = new Intent(HomeActivity.this, GroupActivity.class);
                    startActivity(i11);
                    break;
                case 5:
                    Intent i2 = new Intent(HomeActivity.this, ModifyActivity.class);
                    startActivity(i2);
                    break;
                case 6:
                    Intent i6 = new Intent(HomeActivity.this, SearchActivity.class);
                    startActivity(i6);
                    break;

            }
            return super.onOptionsItemSelected(item);
        }


}
