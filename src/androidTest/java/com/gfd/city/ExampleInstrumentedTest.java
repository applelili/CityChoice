package com.gfd.city;

import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.gfd.city.entity.City;
import com.gfd.city.utils.DBHelper;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Test
    public void test(){
        List<City> allCityInfo = DBHelper.getAllCityInfo();
        Log.e("db",allCityInfo.size() + "");
    }

}
