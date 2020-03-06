package com.vvitmdc.chats;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class BranchWiseStatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch_wise_stat);
        PieChart pieChart = findViewById(R.id.piechart);
        ArrayList<Entry> NoOfEmp = new ArrayList<>();
        NoOfEmp.add(new Entry(44f, 0));
        NoOfEmp.add(new Entry(64f, 1));
        NoOfEmp.add(new Entry(56f, 2));
        NoOfEmp.add(new Entry(158f, 3));
        NoOfEmp.add(new Entry(160f, 4));
        NoOfEmp.add(new Entry(34f, 5));
        NoOfEmp.add(new Entry(12f, 6));
        PieDataSet dataSet = new PieDataSet(NoOfEmp, "Number Of Selections");

        ArrayList<String> year = new ArrayList<>();

        year.add("CIVIL");
        year.add("EEE");
        year.add("MECH");
        year.add("ECE");
        year.add("CSE");
        year.add("IT");
        year.add("MCA");
        PieData data = new PieData(year, dataSet);
        pieChart.setData(data);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieChart.animateXY(5000, 5000);

    }
}
