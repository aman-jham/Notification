package com.funnelapp.androidnotification.api;

import android.content.Context;
import android.telephony.TelephonyManager;

import com.funnelapp.androidnotification.extras.Keys;

import com.google.gson.JsonObject;


import java.util.Enumeration;
import java.util.Hashtable;

/**
 * Created by Snyxius Technologies on 6/7/2016.
 */
public class Parse {



    public static  JsonObject sendData(Context context,String App_Id,String token){
        TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(Keys.appid,App_Id) ;
        jsonObject.addProperty(Keys.devicetoken,token);
        jsonObject.addProperty(Keys.imeino,telephonyManager.getDeviceId());
        return  jsonObject;
    }


    public static  JsonObject sendData(Context context, String App_Id, String token, Hashtable<String,String> data){
        JsonObject jsonObject1 = new JsonObject();
        Enumeration e = data.keys();
        while (e.hasMoreElements()) {
            String key = (String) e.nextElement();
            jsonObject1.addProperty(key,data.get(key));
        }
        TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(Keys.appid,App_Id) ;
        jsonObject.addProperty(Keys.devicetoken,token);
        jsonObject.addProperty(Keys.imeino,telephonyManager.getDeviceId());
        jsonObject.add(Keys.data,jsonObject1);
        return  jsonObject;
    }

}
