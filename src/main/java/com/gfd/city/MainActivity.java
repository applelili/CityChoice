package com.gfd.city;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.gfd.city.adapter.CityAdapter;
import com.gfd.city.entity.City;
import com.gfd.city.utils.DBHelper;
import com.gfd.city.view.LetterView;

import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LetterView.OnSlidingListaner,TextWatcher{

    private EditText mEdSearch;
    private RecyclerView mRecyCity;
    private RecyclerView mRecySearch;
    private LinearLayoutManager layoutManager;
    private LetterView mLeter;
    private CityAdapter cityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        TextView tvToast = (TextView) findViewById(R.id.tv_taost);
        mLeter = (LetterView) findViewById(R.id.letter_view);
        mLeter.setText(tvToast);
        mEdSearch = (EditText) findViewById(R.id.ed_city);
        mRecyCity = (RecyclerView) findViewById(R.id.recy_city);
        mRecySearch = (RecyclerView) findViewById(R.id.recy_search);
        layoutManager = new LinearLayoutManager(this);
        mRecyCity.setLayoutManager(layoutManager);
        mRecySearch.setLayoutManager(new LinearLayoutManager(this));
        List<City> allCityInfo = DBHelper.getAllCityInfo();
        Collections.sort(allCityInfo);
        cityAdapter = new CityAdapter(allCityInfo);
        mRecyCity.setAdapter(cityAdapter);
        mLeter.setOnSlidingListaner(this);
        mEdSearch.addTextChangedListener(this);
    }

    @Override
    public void onSliding(String letter) {
        int position = cityAdapter.letterIndex.get(letter);
        Log.e("db","点击的" + position);
        layoutManager.scrollToPositionWithOffset(position,0);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }


    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        //隐藏城市列表
        //显示搜索的列表
        mRecyCity.setVisibility(View.GONE);
        mRecySearch.setVisibility(View.VISIBLE);
        //根据输入的数据获取匹配的数据
        List<City> cityInfo = DBHelper.getCityInfo(s.toString());
        Collections.sort(cityInfo);
        mRecySearch.setAdapter(new CityAdapter(cityInfo));
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
