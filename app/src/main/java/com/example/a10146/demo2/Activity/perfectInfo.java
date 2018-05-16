package com.example.a10146.demo2.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.bigkoo.pickerview.OptionsPickerView;
import com.example.a10146.demo2.HttpConnectionUtil;
import com.example.a10146.demo2.JsonWarp;
import com.example.a10146.demo2.LoadCityUtil;
import com.example.a10146.demo2.R;

import java.io.IOException;


/**
 * Created by 10146 on 2018/4/16.
 */

public class perfectInfo extends AppCompatActivity implements  View.OnClickListener {
    private ImageButton backInfo_btn;
    private ImageButton nextInfo_btn;
    private EditText    nameInfo;
 //   private EditText    sexInfo;
    private EditText    brAddress;
    private EditText    schoolInfo;

    private Button      perfectInfo_btn;
    private ImageButton      add_btn;
    private Button      scadd_btn;
    private Button      man;
    private Button      woman;


    private String      Name;
    private String      Sex;
    private String      Braddress;
    private String      School;


    private OptionsPickerView city;
    private StringBuilder selectedarea;
    private LoadCityUtil loadCityData = new LoadCityUtil();

     private String      Selectadd;
     private String      Selectadd2;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfect_info);
        loadCityData.initJsonData(this);
        initView();

    }
    private void initView() {
        backInfo_btn = (ImageButton) findViewById(R.id.infoBack);
        nextInfo_btn = (ImageButton) findViewById(R.id.infoNext);

        nameInfo  = (EditText)findViewById(R.id.infoName);
        brAddress = (EditText)findViewById(R.id.brAddress);

        schoolInfo = (EditText)findViewById(R.id.infoSchool);

        perfectInfo_btn =(Button) findViewById(R.id.perfectInfo);
        add_btn = (ImageButton) findViewById(R.id.gaokao_btn);
        man  = (Button)findViewById(R.id.man);
        woman  = (Button)findViewById(R.id.women);


        backInfo_btn.setOnClickListener((View.OnClickListener) this);
        perfectInfo_btn.setOnClickListener((View.OnClickListener) this);
        nextInfo_btn.setOnClickListener((View.OnClickListener) this);
        add_btn.setOnClickListener(this);
        man.setOnClickListener(this);
        woman.setOnClickListener(this);


        }

    public void onClick(View v){
        switch (v.getId()) {
            case R.id.infoBack:
                Intent intentback = new Intent(perfectInfo.this,MainActivity.class);
                startActivity(intentback);
                break;

            case R.id.infoNext:
                Intent intentnext = new Intent(perfectInfo.this,MainActivity.class);
                startActivity(intentnext);
                break;

            case R.id.gaokao_btn:
                onSelectCity();
                break;
            case R.id.man:
                Sex = "男";
                break;
            case R.id.women:
                Sex ="女";
                break;
            case R.id.perfectInfo:
//                Name = nameInfo.getText().toString();
//                School = schoolInfo.getText().toString();
//                Map<String, String> data = new HashMap<String, String>();
//                data.put("name",Name);
//                data.put("school",School);
//                data.put("sex",Sex);
//                data.put("area2",Selectadd);

                gsonData();
                 System.out.println(gsonData());
                 Log.i("Gson ", gsonData());
                String posturl = "http://extlife.xyz/userinfo/setuserinfo";
                try {
                    String posthttp = HttpConnectionUtil.getHttp().post(posturl,gsonData());
                } catch (IOException e) {
                    e.printStackTrace();
                }

        }
        }

        private  String gsonData() {
            Name = nameInfo.getText().toString();
            // Sex = sexInfo.getText().toString();
            //  Braddress = brAddress.getText().toString();
           // Scaddress = scAddress.getText().toString();
            School = schoolInfo.getText().toString();

           JsonWarp jw =new JsonWarp();
            jw.setData("name",Name);
            jw.setData("school",School);
            jw.setData("sex",Sex);
            jw.setData("area2",Selectadd);
            Log.i(" ",jw.toString());
            return jw.toString();
    }

    public void onSelectCity() {
        city = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                selectedarea = new StringBuilder();
                selectedarea.append(loadCityData.options1Items.get(options1).getPickerViewText()+" ");

                selectedarea.append(loadCityData.options2Items.get(options1).get(options2)+" ");

                selectedarea.append(loadCityData.options3Items.get(options1).get(options2).get(options3)+" ");
                  brAddress.setText(selectedarea.toString());
                  Selectadd =selectedarea.toString();


            }
        }).build();
        city.setPicker(loadCityData.options1Items, loadCityData.options2Items, loadCityData.options3Items);//三级选择器
        city.show();

    }

}



