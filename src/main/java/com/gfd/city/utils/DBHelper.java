package com.gfd.city.utils;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gfd.city.app.App;
import com.gfd.city.entity.City;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 类描述：
 * 作者：郭富东
 * 创建日期： 2017/3/24 0024 下午 4:16
 * 更新日期：
 */
public class DBHelper {

    private static final String DB_PATH = "/data/data/com.gfd.city/databases/";
    private static final String DB_NAME = "meituan_cities.db";

    static {
        copyDatabase();
    }
    public static List<City> getAllCityInfo() {

        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DB_PATH + DB_NAME, null);
        Cursor cursor = db.query("city", new String[]{"name", "pinyin"}, null, null, null, null, null);
        List<City> cities = new ArrayList<>();
        while (cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String pinyin = cursor.getString(cursor.getColumnIndex("pinyin"));
            cities.add(new City(name,pinyin));
        }
        return cities;
    }

    public static List<City> getCityInfo(String searchStr) {

        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DB_PATH + DB_NAME, null);
        Cursor cursor = db.rawQuery("select * from city where name like  ? or pinyin like ?;",new String[]{
                "%" + searchStr + "%", searchStr + "%"
        } );
        List<City> cities = new ArrayList<>();
        while (cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String pinyin = cursor.getString(cursor.getColumnIndex("pinyin"));
            cities.add(new City(name,pinyin));
        }
        return cities;
    }

    /**
     * 复制数据库文件到应用存储目录中
     */
    public static void copyDatabase() {
        try {
            File outFile = new File(DB_PATH + DB_NAME);
            if (!outFile.exists()) {//文件不存在才复制
                File file = new File(DB_PATH);
                if (!file.exists()) file.mkdirs();
                FileOutputStream out = new FileOutputStream(outFile);
                InputStream is = App.appContext.getAssets().open("meituan_cities.db");
                byte[] bytes = new byte[1024];
                int len = 0;
                while ((len = is.read(bytes)) != -1){
                    out.write(bytes,0,len);
                }
                out.close();
                is.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
