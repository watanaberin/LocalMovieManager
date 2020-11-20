package com.example.douban;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.DBHelper.DBOpenHelper;
import com.example.application.UserApplication;
import com.example.code.Code;
import com.example.utils.User;

import java.util.ArrayList;

public class ModifyActivity extends AppCompatActivity implements View.OnClickListener{
       private String realCode;
        private Button comfirm;
        private Button dele;
        private TextView uname;
        private EditText oldpswd;
        private  EditText newpswd;
        private EditText code;
        private ImageView showcode;
        private DBOpenHelper mDBOpenHelper;
        private UserApplication app;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);
        mDBOpenHelper = new DBOpenHelper(this);
        init();/**初始化**/
        showcode.setImageBitmap(Code.getInstance().createBitmap());
        realCode = Code.getInstance().getCode().toLowerCase();

    }
    private void init(){
        app=(UserApplication)getApplication();/**获取当前用户**/
        String name=app.getName();

        uname =(TextView)findViewById(R.id.modify_username);
        uname.setText(name);
        oldpswd=(EditText)findViewById((R.id.pswd_old));
        newpswd=(EditText)findViewById((R.id.pswd_new));
        code=(EditText)findViewById(R.id.et_registeractivity_phoneCodes);
        showcode=(ImageView)findViewById(R.id.show_code);
        comfirm=(Button)findViewById(R.id.modify_confirm);
        dele=(Button)findViewById(R.id.delete_anyway);

        comfirm.setOnClickListener(this);
        dele.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.show_code:    //改变随机验证码的生成
                showcode.setImageBitmap(Code.getInstance().createBitmap());
                realCode = Code.getInstance().getCode().toLowerCase();
                break;
            case R.id.modify_confirm:    //修改按钮
                //获取用户输入的用户名、密码、验证码
                String username = uname.getText().toString().trim();
                String o_pswd = oldpswd.getText().toString().trim();
                String n_pswd = newpswd.getText().toString().trim();
                String phoneCode = code.getText().toString().toLowerCase();
                //注册验证

                if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(o_pswd) && !TextUtils.isEmpty(n_pswd) && !TextUtils.isEmpty(phoneCode)) {
                    ArrayList<User> data=mDBOpenHelper.getAllData();
                    boolean match=false;
                    User user1=new User();
                    for(int i=0;i<data.size();i++){
                        user1=data.get(i);
                        if(username.equals(user1.getName())&&o_pswd.equals(user1.getPassword())){
                            match=true;
                            break;
                        }else{
                            match=false;
                        }
                        System.out.println(user1.getName()+user1.getPassword());

                    }
                    if (match){
                        if (phoneCode.equals(realCode)) {
                            //update
                            SQLiteDatabase db = mDBOpenHelper.getWritableDatabase();
                            ContentValues cv = new ContentValues();
                            cv.put("password", n_pswd);
                            db.update("user", cv, "name=?", new String[]{username});
                            Intent intent2 = new Intent(this, MainActivity.class);
                            startActivity(intent2);
                            finish();
                            Toast.makeText(this, "验证通过，跳转到登录页面！", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(this, "验证码错误,修改失败", Toast.LENGTH_SHORT).show();
                        }
                }else{
                        Toast.makeText(this, "原密码错误，修改失败", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(this, "未完善信息，修改失败", Toast.LENGTH_SHORT).show();
                }
                break;

                case R.id.delete_anyway:
                    Builder builder = new Builder(ModifyActivity.this);
                    builder.setMessage("确认删除？")
                            .setTitle("提示");
                    builder.setPositiveButton("确认", new AlertDialog.OnClickListener(){
                        public void onClick(DialogInterface arg0, int arg1) {
                            // TODO Auto-generated method stub
                            data_delete();
                            arg0.dismiss();

                            Intent intent = new Intent(ModifyActivity.this,MainActivity.class);
                            startActivity(intent);
                            ModifyActivity.this.finish();
                        }});
                    builder.setNegativeButton("取消",new AlertDialog.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            // TODO Auto-generated method stub
                            arg0.dismiss();
                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                        break;

    }
    }

    private void data_delete(){
        // TODO Auto-generated method stub
        SQLiteDatabase db=mDBOpenHelper.getWritableDatabase();
        String name=app.getName();
        db.delete("user","name= ? ",new String[]{name});
        Toast.makeText(this, "注销成功，返回登录页面", Toast.LENGTH_SHORT).show();
        Intent i=new Intent(ModifyActivity.this,MainActivity.class);
        startActivity(i);
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
                Intent i=new Intent(ModifyActivity.this,HomeActivity.class);
                startActivity(i);
                break;
            case 2:
                Intent i1=new Intent(ModifyActivity.this,GroupActivity.class);
                startActivity(i1);
                break;
            case 3:
                Intent i2=new Intent(ModifyActivity.this,ModifyActivity.class);
                startActivity(i2);
                break;


        }
        return super.onOptionsItemSelected(item);
    }
}
