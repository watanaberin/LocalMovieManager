package com.example.DBHelper;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.utils.Movie;
import com.example.utils.User;

import java.util.ArrayList;
/**
 * Created by RIN 2020/6/20
 */

public class DBOpenHelper extends SQLiteOpenHelper {
    private SQLiteDatabase db;
    private String TAG = "sq";

    public DBOpenHelper(Context context){
        super(context,"db_test",null,4);
        db = getReadableDatabase();

    }


    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE IF NOT EXISTS user(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "password TEXT)");
        db.execSQL("create table movie"
                + "("
                +"_id integer primary key autoincrement,"
                + "name varchar(20),"
                + "intro varchar(200),"
                + "note varchar(200))");
        Log.i(TAG, "execSQL函数用于执行插入數據。");
        db.execSQL("insert into movie(name,intro,note) Values('春潮','报社记者郭建波、母亲纪明岚与女儿郭婉婷同住在一个屋檐下，祖孙三代因亲情关系捆绑在一起的生活，看似平静实则暗潮涌动。','ok')");
        db.execSQL("insert into movie(name,intro,note) Values('黑水','基于Nathaniel Rich在《纽约时报》上发表的文章。','ok')");
        db.execSQL("insert into movie(name,intro,note) Values('阳光普照','平凡的一家人阿文（陈以文 饰）和琴姐（柯淑勤 饰）育有两个儿子，叛逆的小儿子阿和（巫建和 饰）与好友菜头','好看！')");
        db.execSQL("insert into movie(name,intro,note) Values('天气之子','基于Nathaniel Rich在《纽约时报》上发表的文章。','o的k')");
        db.execSQL("insert into movie(name,intro,note) Values('Black lives matter','','ok')");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS user");
        db.execSQL("DROP TABLE IF EXISTS movie");

        onCreate(db);
    }

    public void add(String name,String password){
        db.execSQL("INSERT INTO user (name,password) VALUES(?,?)",new Object[]{name,password});
    }
    public void delete(String name,String password){
        db.execSQL("DELETE FROM user WHERE name = AND password ="+name+password);
    }

    public void updata(String password){
        db.execSQL("UPDATE user SET password = ?",new Object[]{password});
    }

    public void addM(String name,String intro,String note){
        db.execSQL("INSERT INTO movie (name,intro,note) VALUES(?,?,?)",new Object[]{name,intro,note});
    }
    public void deleteM(String name){
        db.execSQL("DELETE FROM movie WHERE name = ?",new Object[]{name});
    }

    public void updataM(String name,String intro,String note){
        db.execSQL("UPDATE movie SET intro = ? AND note=? WHERE name=?",new Object[]{intro,note,name});
    }

    public ArrayList<Movie> selectM(String name) {
        ArrayList<Movie> list = new ArrayList<Movie>();

        Cursor cursor = db.rawQuery("select * from movie where name = '" + name+ "'",null);
        while (cursor.moveToNext()) {
            list.add(new Movie(cursor.getString(1), cursor.getString(2), cursor.getString(3)));
        }
        return list;
    }
    //搜索
    public ArrayList<String> selectName(){
        ArrayList<String> list = new ArrayList<String>();
        Cursor cursor = db.query("movie",null,null,null,null,null,"name DESC");
        while (cursor.moveToNext()) {
            list.add(cursor.getString(cursor.getColumnIndex("name")));
        }
        return list;
    }

    public ArrayList<User> getAllData(){
                    ArrayList<User> list = new ArrayList<User>();
                    Cursor cursor = db.query("user",null,null,null,null,null,"name DESC");
                    while(cursor.moveToNext()){
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String password = cursor.getString(cursor.getColumnIndex("password"));
                        list.add(new User(name,password));
            }
            return list;
    }

    public ArrayList<Movie> getAllMovie(){

        ArrayList<Movie> list = new ArrayList<Movie>();
        Cursor cursor = db.query("movie",null,null,null,null,null,"name DESC");
        while(cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String intro = cursor.getString(cursor.getColumnIndex("intro"));
            String note = cursor.getString(cursor.getColumnIndex("note"));

            list.add(new Movie(name,intro,note));
        }
        return list;
    }
}
