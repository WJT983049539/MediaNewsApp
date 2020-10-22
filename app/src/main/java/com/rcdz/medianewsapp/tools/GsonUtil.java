package com.rcdz.medianewsapp.tools;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/08/04.
 */

public class GsonUtil {

    private static Gson gson = null;
    static {
        if (gson == null) {
            gson = new Gson();
        }
    }

    /**
     * 转成json
     *
     * @param object
     * @return
     */
    public static String BeanToJson(Object object) {
        String gsonString = null;
        if (gson != null) {
            gsonString = gson.toJson(object);
        }
        return gsonString;
    }

    /**
     * 转成bean
     *
     * @param gsonString
     * @param cls
     * @return
     */
    public static <T> T GsonToBean(String gsonString, Type cls) {
        T t = null;
        if (gson != null) {
            t = gson.fromJson(gsonString, cls);
        }
        return t;
    }


    //region jsonToList
    /**
     * 转成list
     * 解决泛型问题
     * @param json
     * @param cls
     * @param <T>
     * @return
     */
//    public <T> List<T> jsonToList(String json, Class<T> cls) {
//        Gson gson = new Gson();
//        List<T> list = new ArrayList<T>();
//        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
//        for(final JsonElement elem : array){
//            list.add(gson.fromJson(elem, cls));
//        }
//        return list;
//    }
    //endregion

    /**
     * 转成list中有map的
     *
     * @param gsonString
     * @return
     */
    public static <T> List<Map<String, T>> GsonToListMaps(String gsonString) {
        List<Map<String, T>> list = null;
        if (gson != null) {
            list = gson.fromJson(gsonString,
                    new TypeToken<List<Map<String, T>>>() {
                    }.getType());
        }
        return list;
    }


    /**
     * 转成map的
     *
     * @param gsonString
     * @return
     */
    public static <T> Map<String, T> GsonToMaps(String gsonString) {
        Map<String, T> map = null;
        if (gson != null) {
            map = gson.fromJson(gsonString, new TypeToken<Map<String, T>>() {
            }.getType());
        }
        return map;
    }


    public static Object getInstanceByJson(Class<?> clazz, String json){
        Object obj = null;
        Gson gson = new Gson();
        obj = gson.fromJson(json,clazz);
        return obj;
    }

    public static <T> List<T> jsonToList(String json, Class<T[]> clazz){
        T[] array = gson.fromJson(json,clazz);
        return Arrays.asList(array);
    }

    public static <T> ArrayList<T> jsonToArrayList(String json, Class<T> clazz){
        Type type = new TypeToken<ArrayList<JsonObject>>(){}.getType();
        ArrayList<JsonObject> jsonObjects = new Gson().fromJson(json,type);
        ArrayList<T> arrayList = new ArrayList<>();
        for(JsonObject jsonObject : jsonObjects){
            arrayList.add(gson.fromJson(jsonObject,clazz));
        }
        return arrayList;
    }


    public static <T> ArrayList<T> jsonToAddArrayList(String json, Class<T> clazz, ArrayList<T> arrayList){
        Type type = new TypeToken<ArrayList<JsonObject>>(){}.getType();
        ArrayList<JsonObject> jsonObjects = new Gson().fromJson(json,type);
        for(JsonObject jsonObject : jsonObjects){
            arrayList.add(gson.fromJson(jsonObject,clazz));
        }
        return arrayList;
    }


//    public static <T> ArrayList<T> jsonToArrayList2(String result,Class<T> clas) {//Gson 解析
//        ArrayList<T> detail = new ArrayList();
//        try {
//            org.json.JSONArray data = new org.json.JSONArray(result);
//            for (int i = 0; i < data.length(); i++) {
//                T entity = gson.fromJson(data.optJSONObject(i).toString(), clas);
//                detail.add(entity);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            //加载json数据失败
//        }
//        return detail;
//    }
}
