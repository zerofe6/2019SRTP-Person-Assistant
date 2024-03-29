package com.wangj.baselibrary.http.bean;

import com.wangj.baselibrary.util.LogUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 常规返回报文格式化(如果有数组只能是单层数组，业务逻辑复杂时请服务端优化逻辑，或者分开请求不同的接口)
 *
 * @author WangJ 2016.06.02
 */
public class CommonResponse {

    /**
     * 交易状态代码
     */
    private String resCode = "";

    /**
     * 交易失败说明
     */
    private String resMsg = "";

    /**
     * 简单信息
     */
    private HashMap<String, String> propertyMap;

    /**
     * 列表类信息
     */
    private ArrayList<HashMap<String, String>> mapList;

    /**
     * 通用报文返回构造函数
     *
     * @param responseString Json格式的返回字符串
     */
    public CommonResponse(String responseString) {

        // 日志输出原始应答报文
        LogUtil.logResponse(responseString);

        propertyMap = new HashMap<>();
        mapList = new ArrayList<>();

        try {
            JSONObject root = new JSONObject(responseString);

            /* 说明：
                以下名称"resCode"、"resMsg"、"property"、"list"
                和请求体中提到的字段名称一样，都是和服务器程序开发者约定好的字段名字，在本文接下来的服务端代码会说到
             */
            resCode = root.getString("resCode");
            resMsg = root.optString("resMsg");

            JSONObject property = root.optJSONObject("property");
            if (property != null) {
                parseProperty(property, propertyMap);
            }

            JSONArray list = root.optJSONArray("list");
            if (list != null) {
                parseList(list);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    /**
     * 简单信息部分的解析到{@link CommonResponse#propertyMap}
     *
     * @param property  信息部分
     * @param targetMap 解析后保存目标
     */
    private void parseProperty(JSONObject property, HashMap<String, String> targetMap) {
        Iterator<?> it = property.keys();
        while (it.hasNext()) {
            String key = it.next().toString();
            Object value = property.opt(key);
            targetMap.put(key, value.toString());
        }
    }

    /**
     * 解析列表部分信息到{@link CommonResponse#mapList}
     *
     * @param list 列表信息部分
     */
    private void parseList(JSONArray list) {
        int i = 0;
        while (i < list.length()) {
            HashMap<String, String> map = new HashMap<>();
            try {
                parseProperty(list.getJSONObject(i++), map);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mapList.add(map);
        }
    }

    public String getResCode() {
        return resCode;
    }

    public String getResMsg() {
        return resMsg;
    }

    public HashMap<String, String> getPropertyMap() {
        return propertyMap;
    }

    public ArrayList<HashMap<String, String>> getDataList() {
        return mapList;
    }
}