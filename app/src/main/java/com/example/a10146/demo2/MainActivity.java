package com.example.a10146.demo2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private EditText edit_username;
    private EditText edit_password;
    private TextView find_password;
    private TextView registerbtn;
    private  TextView smsRegister;
    private Button login_btn;
    private static final String TAG = "MainActivity";

 private OptionsPickerView city;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      //  loadCityData.initJsonData(this);
        initView();

    }


      private void initView() {
       //   registerbtn = (TextView) findViewById(R.id.registerbtn);
          find_password = (TextView) findViewById(R.id.find_password);
          edit_username = (EditText) findViewById(R.id.edit_username);
          edit_password = (EditText) findViewById(R.id.edit_password);
          login_btn = (Button) findViewById(R.id.login_btn);
          smsRegister = (TextView) findViewById(R.id.smsRegister);
          find_password.setOnClickListener(new View.OnClickListener() {

              @Override
              public void onClick(View arg0) {
                  // TODO 自动生成的方法存根
                  Intent intent = new Intent(MainActivity.this,perfectInfo.class);
                  startActivity(intent);
              }

          });
          smsRegister.setOnClickListener(new View.OnClickListener() {

              public void onClick(View v) {
                  Intent intent = new Intent(MainActivity.this, smsRegister.class);
                  startActivity(intent);

              }

          });
          login_btn.setOnClickListener(new View.OnClickListener() {

              public void onClick(View v) {
                  String userinfodata;
                  String username = edit_username.getText().toString();
                  String password = edit_password.getText().toString();
                  Map<String, String> postmap = new HashMap<String, String>();
                  postmap.put("auth", "1");
                  postmap.put("phone", username);
                  postmap.put("password", password);
                  String posturl = "http://extlife.xyz/user/login";
                  String posthttp = HttpConnectionUtil.getHttp().postRequset(posturl, postmap,null);

                  JsonContainer<String> rootModel = new JsonContainer<String>();
                  Gson gson = new Gson();
                  rootModel = gson.fromJson(posthttp, new TypeToken<JsonContainer<String>>() {
                  }.getType());
                  if (rootModel.getStatus().equals("Success")) {

                      String token = rootModel.getToken();
                      userinfodata = HttpConnectionUtil.getHttp().getRequset("http://extlife.xyz/user/getuserinfo",token);
                      JsonContainer<User> userinfo = new JsonContainer<User>();
                      userinfo = gson.fromJson(userinfodata, new TypeToken<JsonContainer<User>>() {
                      }.getType());
                      User tmp2 = userinfo.getData();
                      AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                      builder.setTitle("提示"); //设置标题
                      builder.setMessage("欢迎" + tmp2.getNickName() + "进入掌课宝"); //设置内容
                      builder.create().show();
                  } else {
                      String Error = rootModel.getErrormsg();
                      switch (Error) {
                          case "PasswordIncorrect":
                              Toast.makeText(MainActivity.this,"密码错误",Toast.LENGTH_LONG).show();
                              break;
                          case "NoUser":
                              Toast.makeText(MainActivity.this,"用户不存在",Toast.LENGTH_LONG).show();

                              break;
                          case "CaptchaRequire":

                              break;

                      }
                  }
               //   logindata = rootModel.getToken();
                //  url = "http://extlife.xyz/user/getuserinfo?token=";
                //  String url2 = url + logindata;
                 // String get = HttpConnectionUtil.getHttp().getRequset(url2,null);

              }



          });


      }



}
