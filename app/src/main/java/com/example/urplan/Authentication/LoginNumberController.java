package com.example.urplan.Authentication;

import com.interfaceForAPIs.LoginInterface;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginNumberController {


    private static final String url="http://UrPlan_App.svc/";
    private static LoginNumberController clienobject;
    private static Retrofit retrofit;

    LoginNumberController() {
                 retrofit=new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized LoginNumberController getInstance() {
        if(clienobject==null)
            clienobject=new LoginNumberController();
            return  clienobject;
    }

    LoginInterface getapi() {
        return retrofit.create(LoginInterface.class);
    }
}



