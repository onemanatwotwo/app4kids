package com.android.example.childrenapp;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;

import java.util.Locale;

public class LanguageManager {
    private Context ct;
    LanguageManager(Context ctx){
        ct=ctx;
    }
    public void updateResource(String code){
        Locale locale=new Locale(code);
        Locale.setDefault(locale);
        Resources reso= ct.getResources();
        Configuration configuration= reso.getConfiguration();
        configuration.locale=locale;
        reso.updateConfiguration(configuration,reso.getDisplayMetrics());



    }


}
