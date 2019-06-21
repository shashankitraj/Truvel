package com.example.aakarsh.truvel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.aakarsh.truvel.adapter.CustomAdapter;
import com.example.aakarsh.truvel.model.CityData;
import com.example.aakarsh.truvel.model.CityDataModel;

import java.util.ArrayList;

public class CityListIntent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_intent);

    }
}
