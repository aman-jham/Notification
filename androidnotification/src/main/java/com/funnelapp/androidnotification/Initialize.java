package com.funnelapp.androidnotification;

import android.content.Context;

import java.util.Hashtable;

/**
 * Created by Snyxius Technologies on 7/19/2016.
 */
public class Initialize{

    static NotificationPresenter presenter; ;

    public static void initialize(Context contexts,String App_IDs){
        presenter = new NotificationPresenter();
        presenter.initialize(contexts,App_IDs);
    }

    public static void registerToken(String token){
        presenter.registeredToken(token);
    }

    public static void registerToken(String token, Hashtable<String,String> data){presenter.registeredToken(token,data);
    }




}
