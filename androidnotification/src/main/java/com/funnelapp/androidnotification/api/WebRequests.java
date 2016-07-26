package com.funnelapp.androidnotification.api;

import com.funnelapp.androidnotification.extras.Constants;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Snyxius Technologies on 6/3/2016.
 */
public interface WebRequests {





    @POST(Constants.REGISTER)
    Call<JsonObject> registerToken(@Body JsonObject task);
//    {
//        "appid": "123",
//            "devicetoken": "123",
//            "imeino": "12345",
//            "data": { "attr1":"val1","attr2":"val2","attr3":"val3"} optional
//    }







}
