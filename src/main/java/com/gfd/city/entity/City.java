package com.gfd.city.entity;

import android.support.annotation.NonNull;

/**
 * 类描述：
 * 创建日期： 2017/3/24 0024 下午 4:14
 * 更新日期：
 */
public class City implements Comparable<City>{

    private String name;
    private String pinyin;

    public City(){}

    public City(String name, String pinyin) {
        this.name = name;
        this.pinyin = pinyin;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPinyin() {
        return pinyin;
    }
    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }
    @Override
    public int compareTo(@NonNull City o) {
        return this.pinyin.substring(0,1).compareTo(o.getPinyin().substring(0,1));
    }
}
