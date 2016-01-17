package com.example.androidversiontest;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by tagitdev on 15/1/2016.
 */
public class AndroidVersion extends HashMap<String, String> implements Serializable {
    private static final String MOCK_URL = "http://lorempixel.com/800/400/food/";
    static final String baseurl = "http://192.168.1.13:3000/devices";


//    public AndroidVersion(String id, String name, String version, String codename,String target,String distribution) {
//        put("id", id);
//        put("name", name);
//        put("version", version);
//        put("codename", codename);
//        put("target", target);
//        put("distribution", distribution);
//    }

    public AndroidVersion(String id, String imageurl,String name,String snippet) {

        put("id", id);
        put("imageurl", imageurl);
        put("name", name);
        put("snippet", name);
    }

    public AndroidVersion(String id,String androidid, String imageurl,String name,String snippet,String carrier) {
        put("id", id);
        put("androidId", androidid);
        put("imageUrl", imageurl);
        put("carrier",carrier);
        put("name", name);
        put("snippet", name);
    }

    public static List<AndroidVersion> createAndroidVersionList() {
        List<AndroidVersion> Androidlist = new ArrayList<>();
        AndroidVersion c = null;
        try {
            JSONArray rlist = JSONParser.getJSONArrayFromUrl(baseurl);
            for (int i = 0; i < rlist.length(); i++) {
                JSONObject jsonobject = (JSONObject) rlist.get(i);
                c = new AndroidVersion(jsonobject.optString("androidId"),
                        jsonobject.optString("imageUrl"),
                        jsonobject.optString("name"),
                        jsonobject.optString("snippet"));

                Androidlist.add(c);
            }

        } catch (Exception e) {
            Log.e("list", "JSON error");
        }
        return Androidlist;
    }

    public static void AddAndroidVersion(AndroidVersion android) {

        try {
            JSONObject jsonAndroidVersion = new JSONObject();
            try {
                jsonAndroidVersion.put("id", Integer.parseInt(android.get("id")));
                jsonAndroidVersion.put("androidId", Integer.parseInt(android.get("androidId")));
                jsonAndroidVersion.put("carrier", android.get("carrier"));
                jsonAndroidVersion.put("name", android.get("name"));
                jsonAndroidVersion.put("snippet",android.get("snippet"));
            } catch (Exception e) {
            }
            String result=JSONParser.postStream(baseurl,jsonAndroidVersion.toString());

            JSONArray rlist = JSONParser.getJSONArrayFromUrl(baseurl);
//            for (int i = 0; i < rlist.length(); i++) {
//                JSONObject jsonobject = (JSONObject) rlist.get(i);
//                c = new AndroidVersion(MOCK_URL + jsonobject.optString("id"),
//                        jsonobject.optString("name"),
//                        jsonobject.optString("version"),
//                        jsonobject.optString("codename"),
//                        jsonobject.optString("target"),
//                        jsonobject.optString("distribution"));
//
//                Androidlist.add(c);
//            }

        } catch (Exception e) {
            Log.e("list", "JSON error");
        }
    }

}