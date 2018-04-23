package com.example.a10146.demo2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.bigkoo.pickerview.OptionsPickerView;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by 10146 on 2018/4/16.
 */

public class perfectInfo extends AppCompatActivity implements  View.OnClickListener {
    private ImageButton backInfo_btn;
    private ImageButton nextInfo_btn;
    private EditText    nameInfo;
 //   private EditText    sexInfo;
    private EditText    brAddress;
    private EditText    scAddress;
    private EditText    schoolInfo;
    private EditText    classInfo;

    private Button      perfectInfo_btn;
    private Button      add_btn;
    private Button      scadd_btn;
    private Button      man;
    private Button      woman;


    private String      Name;
    private String      Sex;
    private String      Braddress;
    private String      Scaddress;
    private String      School;
    private String      Grade;
    private String      Class;

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
        scAddress = (EditText)findViewById(R.id.scAddress);
        schoolInfo = (EditText)findViewById(R.id.infoSchool);
        classInfo = (EditText)findViewById(R.id.infoClass);

        perfectInfo_btn =(Button) findViewById(R.id.perfectInfo);
        add_btn = (Button) findViewById(R.id.gaokao_btn);
        scadd_btn=(Button)findViewById(R.id.scadd_btn);
        man =(Button)findViewById(R.id.man);
        woman =(Button)findViewById(R.id.woman);


        backInfo_btn.setOnClickListener((View.OnClickListener) this);
        perfectInfo_btn.setOnClickListener((View.OnClickListener) this);
        nextInfo_btn.setOnClickListener((View.OnClickListener) this);
        add_btn.setOnClickListener(this);
        scadd_btn.setOnClickListener(this);
        man


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
                brAddress.setText(Selectadd);
                onSelectCity(1);

                break;
            case R.id.scadd_btn:
                onSelectCity(2);
                break;

            case R.id.perfectInfo:
                 gsonData();
                Log.i("Gson ",gsonData());

            }

        }

        private  String gsonData() {
            Name = nameInfo.getText().toString();
            //     Sex = sexInfo.getText().toString();
            //  Braddress = brAddress.getText().toString();
            Scaddress = scAddress.getText().toString();
            School = schoolInfo.getText().toString();
            Class = classInfo.getText().toString();
            JsonCreate jsonCreate = new JsonCreate();
            jsonCreate.setName(Name);
            jsonCreate.setSex(Sex);
            jsonCreate.setSchool(School);
            jsonCreate.setGrade(Grade);
            String[] add =addOject(Selectadd);
            Map<String, String> Addmap = new HashMap<String, String>();
            Addmap.put("Province", add[0]);
            Addmap.put("City", add[1]);
            Addmap.put("Area", add[2]);
            jsonCreate.setExamAdd(Addmap);
            String[] add2 =addOject(Selectadd2);
            Map<String, String> Addmap2 = new HashMap<String, String>();
            Addmap2.put("Province", add2[0]);
            Addmap2.put("City", add2[1]);
            Addmap2.put("Area", add2[2]);
            jsonCreate.setSchoolAdd(Addmap2);
            return   jsonCreate.toString();
    }

    public void onSelectCity(final int Flag) {
        city = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                selectedarea = new StringBuilder();
                selectedarea.append(loadCityData.options1Items.get(options1).getPickerViewText()+" ");

                selectedarea.append(loadCityData.options2Items.get(options1).get(options2)+" ");

                selectedarea.append(loadCityData.options3Items.get(options1).get(options2).get(options3)+" ");
              switch (Flag) {
                  case 1:
                  brAddress.setText(selectedarea.toString());
                      Selectadd =selectedarea.toString();
                      break;
                  case 2:
                      scAddress.setText(selectedarea.toString());
                      Selectadd2 =selectedarea.toString();
              }
            }
        }).build();
        city.setPicker(loadCityData.options1Items, loadCityData.options2Items, loadCityData.options3Items);//三级选择器
        city.show();

    }
     public String[] addOject(String temp){
         String[]  address = null;
         address = temp.split(" ");
        return address;
    }
}



