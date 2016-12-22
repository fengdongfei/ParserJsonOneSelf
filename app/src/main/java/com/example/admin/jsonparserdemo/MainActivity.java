package com.example.admin.jsonparserdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 参考资料: https://my.oschina.net/sammy1990/blog/272510
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "parser_result";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // TODO 全是对象的json
        String json_1 = File_Util.readAssets(this, "D.json");
        // TODO 全是数组的Json(数组元素不是键值对)
        String json_2 = File_Util.readAssets(this, "S.json");
        // TODO 最外层是数组,里面是对象的json
        String json_3 = File_Util.readAssets(this, "S_D.json");
        // TODO 在外层是数组,内部数组,再内部是对象的json
        String json_4 = File_Util.readAssets(this, "S_S_D.json");
        // TODO 最外层是对象,内部是数组,数组内部是对象(键值对)
        String json_5 = File_Util.readAssets(this, "D_S_D.json");


        /**解析对象**/
        Parser_D(json_1);
        /**解析数组(不是键值对)**/
        Parser_S(json_2);
        /**解析数组-对象(最外层是数组)**/
        Parser_S_D(json_3);
        /**解析数组-数组-对象**/
        Parser_S_S_D(json_4);
        /**解析对象-数组-对象**/
        Parser_D_S_D(json_5);
    }

    /**
     * 手动解析---->在外层是对象,内部数组,内部对象
     * @param json_5
     */
    private void Parser_D_S_D(String json_5) {
        try{
            List<String> list=new ArrayList<>();
            JSONObject jsonObj = new JSONObject(json_5);
            JSONArray jsonArray = jsonObj.optJSONArray("list");
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.optJSONObject(i);
                String guess_title = jsonObject.optString("guess_title");
                int room_bet = jsonObject.optInt("room_bet");
                list.add(guess_title+"="+room_bet);
            }
            String messages = jsonObj.optString("message");
            String status = jsonObj.optString("status");
            list.add(messages+"="+status);
            Log.e(TAG,list+"  ");
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    /**
     * 手动解析----> 最外层是数组-数组-对象
     * @param json_4
     */
    private void Parser_S_S_D(String json_4) {
        try{
            List<String> list=new ArrayList<>();
            JSONArray jsonArray = new JSONArray(json_4);
            for (int i= 0; i < jsonArray.length();i++) {
                JSONArray arr = jsonArray.optJSONArray(i);
                for (int j= 0; j < arr.length();j++){
                    JSONObject jsonObject = arr.optJSONObject(j);
                    String name = jsonObject.optString("name");
                    int age = jsonObject.optInt("age");
                    list.add(name+"="+age);
                }
            }
            Log.e(TAG,list+"  ");
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    /**
     * 手动解析---->数组-对象(最外层是数组)
     * @param json_3
     */
    private void Parser_S_D(String json_3) {
        try{
            List<String> list=new ArrayList<>();
            JSONArray jsonArray = new JSONArray(json_3);
            for (int i= 0; i < jsonArray.length();i++) {
                JSONObject jsonObject = jsonArray.optJSONObject(i);
                String name = jsonObject.optString("name");
                int age = jsonObject.optInt("age");
                list.add(name+"="+age);
            }
            Log.e(TAG,list+"  ");
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    /**
     * 手动解析----> 数组(非键值对)
     * @param json_2
     */
    private void Parser_S(String json_2) {
        try {
            List<String> list=new ArrayList<>();
            JSONArray jsonArray = new JSONArray(json_2);
            for (int i= 0; i < jsonArray.length();i++) {
                int age = jsonArray.optInt(i);
                list.add(age+"");
            }
            Log.e(TAG,list+"");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 手动解析json---->对象
     * @param json_1
     */
    private void Parser_D(String json_1) {
        try {
            JSONObject jsonObj = new JSONObject(json_1);
            String codeVer = jsonObj.optString("codeVer");
            String downloadUrl = jsonObj.optString("downloadUrl");
            int weight = jsonObj.optInt("force");
            String updateDescription=jsonObj.optString("updateDescription");
            String ver=jsonObj.optString("ver");
            /**
             Object *=jsonObj.opt（String name）
             boolean *= jsonObj.optBoolean(String name)
             double *=jsonObj.optDouble(String name)
             JSONArray *=jsonObj.optJSONArray(String name)
             JSONObject *=jsonObj.optJSONObject(String name)
             */
            Log.e(TAG,codeVer+"  "+downloadUrl+"  "+weight+"  "+updateDescription+"  "+ver);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
