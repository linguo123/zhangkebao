package com.example.a10146.demo2.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.example.a10146.demo2.R;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {


    private EditText edit_username;
    private EditText edit_password;
    private TextView find_password;
    private TextView registerbtn;
    private  TextView smsRegister;
    private TextView login_btn;
    private static final String TAG = "MainActivity";
    Tencent mTencent;
    BaseUiListener qqlistener = new BaseUiListener();
    private ImageButton qq;

    private OptionsPickerView city;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      //  loadCityData.initJsonData(this);
        mTencent = Tencent.createInstance("1106790301", this.getApplicationContext());
        initView();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            Tencent.onActivityResultData(requestCode,resultCode,data,qqlistener);
    }


    private void initView() {
       //   registerbtn = (TextView) findViewById(R.id.registerbtn);
          find_password = (TextView) findViewById(R.id.find_password);
          edit_username = (EditText) findViewById(R.id.edit_username);
          edit_password = (EditText) findViewById(R.id.edit_password);
          login_btn = (TextView) findViewById(R.id.login_btn);
          smsRegister = (TextView) findViewById(R.id.smsRegister);
           qq = (ImageButton)findViewById(R.id.qq);
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
                  Intent intent = new Intent(MainActivity.this, com.example.a10146.demo2.Activity.smsRegister.class);
                  startActivity(intent);

              }

          });
          login_btn.setOnClickListener(new View.OnClickListener() {

              public void onClick(View v) {
                  Intent intent = new Intent(MainActivity.this, HomePage.class);
                  startActivity(intent);

               /*   String userinfodata;
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
   */
              }



          });
      qq.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              if (!mTencent.isSessionValid())
              {
                  mTencent.login(MainActivity.this,"all", qqlistener);
              }
          }
      });


      }

      private class BaseUiListener implements IUiListener {
        @Override
        public void onComplete(Object response) {
            doComplete((JSONObject) response);
        }
        protected void doComplete(JSONObject values) {
            Log.i("Json",values.toString());
        }
        @Override
        public void onError(UiError e) {
        }
        @Override
        public void onCancel() {
        }
    }

}
