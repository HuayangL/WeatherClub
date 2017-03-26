package com.club.nicetomeetyou.weatherclub.util;

import android.text.TextUtils;

import com.club.nicetomeetyou.weatherclub.db.City;
import com.club.nicetomeetyou.weatherclub.db.County;
import com.club.nicetomeetyou.weatherclub.db.Province;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.util.LogUtil;

import java.util.EmptyStackException;

/**
 * Created by nice to meet you on 2017/3/3.
 */

public class Utility {
    /**
     * 解析和处理服务器返回的省级数据
     */
    public static boolean handleProvinceResponse(String response) {
        LogUtil.e(response,new Exception("handleProvinceResponse空指针异常"));
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allProvince = new JSONArray(response);
                for (int i = 0; i < allProvince.length(); i++) {
                    JSONObject provinceObjcet = allProvince.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(provinceObjcet.getString("name"));
                    province.setProvinceCode(provinceObjcet.getInt("id"));
                    province.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 解析和处理服务器返回的市级数据
     */
    public static boolean handleCityResponse(String response, int provinceId) {
        LogUtil.e(response,new Exception("handleCityResponse空指针异常"));
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allCities = new JSONArray(response);
                for (int i = 0; i < allCities.length(); i++) {
                    JSONObject cityObject = allCities.getJSONObject(i);
                    City city = new City();
                    city.setCityName(cityObject.getString("name"));
                    city.setCityCode(cityObject.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    /**
     * 解析和处理服务器返回的县级数据
     */
    public static boolean handleCountyResponse(String response ,int cityId) {
        LogUtil.e(response,new Exception("handleCountyResponse空指针异常"));
        if(!TextUtils.isEmpty(response)) {
            try {
                JSONArray allCounties = new JSONArray(response);
                for (int i = 0; i < allCounties.length(); i++) {
                    JSONObject countyObject = allCounties.getJSONObject(i);
                    County county = new County();
                    county.setConutyName(countyObject.getString("name"));
                    county.setWeatherId(countyObject.getString("weather_id"));
                    county.setCityId(cityId);
                    county.save();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
