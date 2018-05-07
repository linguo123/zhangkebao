package com.example.a10146.demo2;

/**
 * Created by 10146 on 2018/4/9.
 */

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class smsRegister extends AppCompatActivity implements  View.OnClickListener {

    private TimerTask tt;
    private Timer tm;
    private EditText et_phonenum;
    private Button btn_check;
    private EditText et_checkecode;
    private String randchar;
    private EditText et_pwd;
    private Button btn_sure;
    private int TIME = 60;//倒计时60s这里应该多设置些因为mob后台需要60s,我们前端会有差异的建议设置90，100或者120
    public String country="86";//这是中国区号，如果需要其他国家列表，可以使用getSupportedCountries();获得国家区号
    private String phone;
    private String pwd;
    private String code;
    private static final int CODE_REPEAT = 1; //重新发送
    private String tmp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.smsregister);
        initView();
    }
    Handler hd = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == CODE_REPEAT) {
                btn_check.setEnabled(true);
                btn_sure.setEnabled(true);
                tm.cancel();//取消任务
                tt.cancel();//取消任务
                TIME = 60;//时间重置
                btn_check.setText("重新发送验证码");
            }else {
                btn_check.setText(TIME + "重新发送验证码");
            }
        }
    };

    private void toast(final String str) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(smsRegister.this, str, Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void initView() {
        et_phonenum = (EditText) findViewById(R.id.et_phonenum);
        btn_check = (Button) findViewById(R.id.btn_check);
        et_checkecode = (EditText) findViewById(R.id.et_checkecode);
        et_pwd = (EditText) findViewById(R.id.et_pwd);
        btn_sure = (Button) findViewById(R.id.btn_sure);
        btn_check.setOnClickListener(this);
        btn_sure.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_check:
                phone = et_phonenum.getText().toString().trim();   //.replaceAll("/s","");
                if (!TextUtils.isEmpty(phone)) {
                    String REGEX_MOBILE_SIMPLE = "[1][3578]\\d{9}";
                    //把正则表达式的规则编译成模板
                    Pattern pattern = Pattern.compile(REGEX_MOBILE_SIMPLE);
                    //把需要匹配的字符给模板匹配，获得匹配器
                    Matcher matcher = pattern.matcher(phone);
                    // 通过匹配器查找是否有该字符，不可重复调用重复调用matcher.find()
                    if (matcher.find()) {//匹配手机号是否存在
                        alterWarning();
                    } else {
                        toast("手机号格式错误");
                    }
                } else {
                    toast("请先输入手机号");
                }
                //获得用户输入的验证码


                break;
            case R.id.btn_sure:

                pwd = et_pwd.getText().toString();
                if (TextUtils.isEmpty(pwd)) {
                    toast("请输入密码后再提交");
                }
                code = et_checkecode.getText().toString();
                if (TextUtils.isEmpty(code)) {//判断验证码是否为空
                    //如果用户输入的内容为空，提醒用户
                    toast("请输入验证码后再提交");
                }
                Map<String, String> postmap = new HashMap<String, String>();
                postmap.put("phone", phone);
                postmap.put("password", pwd);
                postmap.put("type", "1");
                postmap.put("randchar",randchar);
                postmap.put("captcha",code);
                String posturl = "http://extlife.xyz/user/register";
                String posthttp = HttpConnectionUtil.getHttp().postRequset(posturl, postmap,null);
                JsonContainer<String> rootModel = new JsonContainer<String>();
                Gson gson = new Gson();
                rootModel = gson.fromJson(posthttp, new TypeToken<JsonContainer<String>>() {
                }.getType());

                if(rootModel.getStatus().equals("Success")){
                    tmp = rootModel.getToken();
                    toast("注册成功"+tmp);
                    Intent intent = new Intent(smsRegister.this, MainActivity.class);
                    startActivity(intent);
                }else {
                    String Error = rootModel.getErrormsg();
                    switch (Error) {
                        case "UserAlready":
                            toast("手机号已注册");
                            break;
                        case "NoRandChar":
                            toast("未获取验证码");
                            break;
                        case "CaptchaWrong":
                            toast("验证码错误");
                            break;
                    }
                }
                break;
        }
    }


    //弹窗确认下发
    private void alterWarning() {
        //构造器
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示"); //设置标题
        builder.setMessage("我们将要发送到" + phone + "验证"); //设置内容
        builder.setIcon(R.mipmap.sign);//设置图标，图片id即可
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            //设置确定按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); //关闭dialog
                //通过sdk发送短信验证（请求获取短信验证码，在监听（eh）中返回）
                String sdk = HttpConnectionUtil.getHttp().getRequset("http://extlife.xyz/user/getcode?auth=1&phone="+phone,null);
                //做倒计时操作
                JsonContainer<String> rootModel = new JsonContainer<String>();
                Gson gson = new Gson();
                rootModel = gson.fromJson(sdk,new TypeToken<JsonContainer<String>>(){}.getType());
                randchar = (String)rootModel.getData();

                Toast.makeText(smsRegister.this, "已发送" + which, Toast.LENGTH_SHORT).show();
                btn_check.setEnabled(false);
                btn_sure.setEnabled(true);
                tm = new Timer();
                tt = new TimerTask() {
                    @Override
                    public void run() {
                        hd.sendEmptyMessage(TIME--);
                    }
                };
                tm.schedule(tt,0,1000);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() { //设置取消按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Toast.makeText(smsRegister.this, "已取消" + which, Toast.LENGTH_SHORT).show();
            }
        });
        //参数都设置完成了，创建并显示出来
        builder.create().show();
    }

}

