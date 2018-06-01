
package com.example.a10146.demo2.MPAndroidChart;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.a10146.demo2.R;
import com.github.mikephil.charting.charts.BarChart;

import java.util.ArrayList;
import java.util.List;

public class AnotherBarActivity extends AppCompatActivity {


        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_barchart);

            BarChart barChart1 = (BarChart) findViewById(R.id.bar_chart1);
        //     BarChart barChart2 = (BarChart) findViewById(R.id.bar_chart2);

            BarChartManager barChartManager1 = new BarChartManager(barChart1);
        //    BarChartManager barChartManager2 = new BarChartManager(barChart2);

            //设置x轴的数据
            ArrayList<Float> xValues = new ArrayList<>();
            for (int i = 0; i <= 10; i++) {
                xValues.add((float) i);
            }

            //设置y轴的数据()
            List<List<Float>> yValues = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                List<Float> yValue = new ArrayList<>();
                for (int j = 0; j <= 10; j++) {
                    yValue.add((float) (Math.random() * 100));
                }
                yValues.add(yValue);
            }

            //颜色集合
            List<Integer> colours = new ArrayList<>();
            colours.add(Color.GREEN);
            colours.add(Color.BLUE);
            colours.add(Color.RED);
            colours.add(Color.CYAN);

            //线的名字集合
//            List<String> names = new ArrayList<>();
//            names.add("综合成绩");
            //创建多条折线的图表
            barChartManager1.showBarChart(xValues, yValues.get(0), null, colours.get(3));

        }
    }
