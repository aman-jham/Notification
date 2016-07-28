package com.funnelapp.androidnotification;

import android.content.Context;

import com.funnelapp.androidnotification.api.Parse;
import com.funnelapp.androidnotification.api.WebRequests;
import com.funnelapp.androidnotification.extras.Constants;
import com.google.gson.JsonObject;

import java.util.Hashtable;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Snyxius Technologies on 7/25/2016.
 */
public class AsyncHandlerInteraction implements IAsyncHandlerInteraction, Callback<JsonObject> {

    OnValidateFinishListner onValidateFinishListner;
    String App_ID = "";
    Context context = null;
    @Override
    public void validateInteraction(OnValidateFinishListner onValidateFinishListner,Context context, String App_Id) {
        this.onValidateFinishListner = onValidateFinishListner;
        this.App_ID = App_Id;
        this.context = context;
        if(App_Id.equals("")){
            onValidateFinishListner.emptyAppId();
        }else if(context == null){
            onValidateFinishListner.emptyContext();
        }else{
           onValidateFinishListner.onSuccess("Success");
        }
    }

    @Override
    public void validateInteractionToken(OnValidateFinishListner onValidateFinishListner,String Token) {
        this.onValidateFinishListner = onValidateFinishListner;
        if(App_ID.equals("")){
            onValidateFinishListner.emptyAppId();
        }else if(context == null){
            onValidateFinishListner.emptyContext();
        }else if(Token.equals("")){
            onValidateFinishListner.emptyToken();
        }else{
            WebRequests service = initializeRetrofit().create(WebRequests.class);
            Call<JsonObject> call = service.registerToken(Parse.sendData(context,App_ID,Token));
            call.enqueue(this);
        }
    }


    public static final Retrofit initializeRetrofit() {
//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    @Override
    public void validateInteractionTokenAndData(OnValidateFinishListner onValidateFinishListner,String Token, Hashtable<String, String> data) {
        if(App_ID.equals("")){
            onValidateFinishListner.emptyAppId();
        }else if(context == null){
            onValidateFinishListner.emptyContext();
        }else if(Token.equals("")){
            onValidateFinishListner.emptyToken();
        }else if (data.isEmpty() || data == null){
            onValidateFinishListner.emptyData();
        }else{
            WebRequests service = initializeRetrofit().create(WebRequests.class);
            Call<JsonObject> call = service.registerToken(Parse.sendData(context,App_ID,Token,data));
            call.enqueue(this);
        }
    }


    @Override
    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
        if(response.code() == Constants.SUCCESS){
            onValidateFinishListner.onSuccess("as");
        }else{
            onValidateFinishListner.serverIssue();
        }
    }

    @Override
    public void onFailure(Call<JsonObject> call, Throwable t) {
        onValidateFinishListner.internetIssue();
    }
}
