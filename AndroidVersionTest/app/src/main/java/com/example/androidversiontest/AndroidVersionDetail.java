package com.example.androidversiontest;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by halyson on 19/12/14.
 */
public class AndroidVersionDetail extends HashMap<String, String> implements Serializable {
    private static final String MOCK_URL = "http://lorempixel.com/800/400/food/";
    static final String baseurl = "http://192.168.1.13:3000/android/";


    public AndroidVersionDetail(String id, String name, String version, String codename,String target,String distribution) {
        put("id", id);
        put("name", name);
        put("version", version);
        put("codename", codename);
        put("target", target);
        put("distribution", distribution);
    }


    public static AndroidVersionDetail CreateAndroidVersionDetail(String androidid) {

        AndroidVersionDetail android = null;
        try {
            JSONObject jsonobject = JSONParser.getJSONFromUrl(baseurl+androidid);

                android = new AndroidVersionDetail(jsonobject.optString("id"),
                        jsonobject.optString("name"),
                        jsonobject.optString("version"),
                        jsonobject.optString("codename"),
                        jsonobject.optString("target"),
                        jsonobject.optString("distribution"));

            return android;

        } catch (Exception e) {
            Log.e("list", "JSON error");
        }
        return android;
    }

    public static List<AndroidVersionDetail> CreateAllAndroidVersionDetail() {
        List<AndroidVersionDetail> Androidlist = new ArrayList<>();
        AndroidVersionDetail android = null;
        try {
            JSONArray rlist = JSONParser.getJSONArrayFromUrl(baseurl);
            for (int i = 0; i < rlist.length(); i++) {
                JSONObject jsonobject = (JSONObject) rlist.get(i);

                android = new AndroidVersionDetail(jsonobject.optString("id"),
                        jsonobject.optString("name"),
                        jsonobject.optString("version"),
                        jsonobject.optString("codename"),
                        jsonobject.optString("target"),
                        jsonobject.optString("distribution"));
                Androidlist.add(android);
            }

        } catch (Exception e) {
            Log.e("list", "JSON error");
        }
        return Androidlist;
    }


//
//    public static List<AndroidVersion> updateAndroidVersion(String androidid) {
//        List<AndroidVersion> Androidlist = new ArrayList<>();
//        AndroidVersion c = null;
//        try {
//            JSONArray rlist = JSONParser.getJSONArrayFromUrl(baseurl);
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
//
//        } catch (Exception e) {
//            Log.e("list", "JSON error");
//        }
//        return Androidlist;
//    }
//
//    public static List<AndroidVersion> deleteAndroidVersion(String androidid) {
//        List<AndroidVersion> Androidlist = new ArrayList<>();
//        AndroidVersion c = null;
//        try {
//            JSONArray rlist = JSONParser.getJSONArrayFromUrl(baseurl);
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
//
//        } catch (Exception e) {
//            Log.e("list", "JSON error");
//        }
//        return Androidlist;
//    }
}