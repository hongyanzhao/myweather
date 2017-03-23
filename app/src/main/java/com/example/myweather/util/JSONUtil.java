package com.example.myweather.util;

import android.text.TextUtils;

import com.example.myweather.db.City;
import com.example.myweather.db.County;
import com.example.myweather.db.Province;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by Administrator on 2017/3/22 0022.
 */

public class JSONUtil {
    public static boolean handleProvinceResponse(String response) {
        if (!TextUtils.isEmpty(response)) {
            Gson gson = new Gson();
            List<Province> provinceList = gson.fromJson(response, new TypeToken<List<Province>>(){}.getType());
            for (Province province : provinceList) {
                province.save();
            }
            return true;
        }
        return false;
    }

    public static boolean handleCityResponse(String response, int provinceId) {
        if (!TextUtils.isEmpty(response)) {
            Gson gson = new Gson();
            List<City> cityList = gson.fromJson(response, new TypeToken<List<City>>(){}.getType());
            for (City city : cityList) {
                city.setprovinceId(provinceId);
                city.save();
            }
            return true;
        }
        return false;
    }

    public static boolean handleCountyResponse(String response, int cityId) {
        if (!TextUtils.isEmpty(response)) {
            Gson gson = new Gson();
            List<County> countyList = gson.fromJson(response, new TypeToken<List<County>>(){}.getType());
            for (County county : countyList) {
                county.setcityId(cityId);
                county.save();
            }
            return true;
        }
        return false;
    }
}
