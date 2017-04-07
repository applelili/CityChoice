package com.gfd.city.adapter;

import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gfd.city.R;
import com.gfd.city.app.App;
import com.gfd.city.entity.City;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类描述：
 * 作者：郭富东
 * 创建日期： 2017/3/24 0024 下午 4:41
 * 更新日期：
 */
public class CityAdapter extends RecyclerView.Adapter<CityAdapter.MyViewHolde> {

    private List<City> cities;
    public Map<String,Integer> letterIndex = new HashMap<>();

    public CityAdapter(List<City> cities) {
        this.cities = cities;
        for (int i = 0; i < cities.size(); i++) {
            //去重
            String previouStr = (i - 1 >= 0) ? getAleph(cities.get(i - 1).getPinyin()) : "";
            String currentStr = getAleph(cities.get(i).getPinyin());
            if(!previouStr.equals(currentStr)){
                letterIndex.put(currentStr,i);
                Log.e("db","存储的：" + currentStr + " 位置为：" + i);
            }


        }
    }

    @Override
    public MyViewHolde onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(App.appContext).inflate(R.layout.item_city,parent,false);
        return new MyViewHolde(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolde holder, int position) {
        City city = cities.get(position);
        holder.setText(R.id.tv_item_name, city.getName());
        //获取上一个字母
        String previouStr = (position - 1 >= 0) ? getAleph(cities.get(position - 1).getPinyin()) : "";
        String currentStr = getAleph(city.getPinyin());
        if(previouStr.equals(currentStr)){//当前城市的首字母和上一个的相等
            holder.setVisibility(R.id.tv_item_letter,View.GONE);
        }else{//不相等
            holder.setVisibility(R.id.tv_item_letter,View.VISIBLE);
            holder.setText(R.id.tv_item_letter,getAleph(city.getPinyin()));
        }


    }

    @Override
    public int getItemCount() {
        return cities == null ? 0 : cities.size();
    }

    public static class  MyViewHolde extends RecyclerView.ViewHolder{


        private View itemView;

        public MyViewHolde(View itemView) {
            super(itemView);
            this.itemView = itemView;
        }

        private <T extends View> T getViewById(@IdRes int viewID){
           return (T) itemView.findViewById(viewID);
        }

        public void setText(@IdRes int viewID,String text){
            TextView tv = getViewById(viewID);
            tv.setText(text);
        }

        public void setVisibility(@IdRes int viewID,int visibility){
            getViewById(viewID).setVisibility(visibility);
        }
    }

    private String getAleph(String pinyin){
        if(!TextUtils.isEmpty(pinyin)){
            return pinyin.substring(0, 1).toUpperCase();
        }
        return "#";
    }
}
