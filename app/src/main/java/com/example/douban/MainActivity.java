package com.example.douban;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.DBHelper.DBOpenHelper;
import com.example.application.UserApplication;
import com.example.utils.User;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private DBOpenHelper mDBOpenHelper;
    private Button sign_in_button;
    private Button register_button;
    private EditText username;
    private  EditText pswd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewInit();
        mDBOpenHelper = new DBOpenHelper(this);
    }


        private void ViewInit(){
            sign_in_button=findViewById(R.id.sign_in_button);
            register_button=findViewById(R.id.register_button);
            username=findViewById(R.id.EditText1);
            pswd=findViewById(R.id.EditText2);

            sign_in_button.setOnClickListener(this);
            register_button.setOnClickListener(this);
        }
        public void onClick(View view){
        switch (view.getId()){

            case R.id.register_button:
                Intent intent=new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.sign_in_button:
                String name=username.getText().toString().trim();
                String password=pswd.getText().toString().trim();
                if(!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(password))
                {
                    ArrayList<User> data=mDBOpenHelper.getAllData();
                    boolean match=false;
                    User user=new User();
                    for(int i=0;i<data.size();i++){
                        user=data.get(i);
                        if(name.equals(user.getName())&&password.equals(user.getPassword())){
                            match=true;
                            break;
                        }else{
                            match=false;
                        }
                    }
                    if (match) {
                        UserApplication app=new UserApplication();
                        app.setName(user.getName().trim());
                        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                        Intent intent1 = new Intent(this, HomeActivity.class);
                        startActivity(intent1);
                        finish();//销毁此Activity
                    } else {
                        Toast.makeText(this, "用户名或密码不正确，请重新输入", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(this, "请输入你的用户名或密码", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        }


}