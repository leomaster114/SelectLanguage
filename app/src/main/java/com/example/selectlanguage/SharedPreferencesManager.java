package com.example.selectlanguage;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesManager {
    private static final String SHARED_PREF_NAME = "selectLanguage";
    private static SharedPreferencesManager mInstance;
    private Context mContext;

    public SharedPreferencesManager(Context mContext) {
        this.mContext = mContext;
    }
    public static synchronized SharedPreferencesManager getInstance(Context context){
        if(mInstance == null) mInstance = new SharedPreferencesManager(context);
        return mInstance;
    }
    public void saveData(String languague){
        SharedPreferences preferences = mContext.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("languague",languague);
        editor.apply();
    }
    public String getData(){
        SharedPreferences preferences = mContext.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return preferences.getString("languague",null);
    }
    public boolean isSelected(){
        SharedPreferences preferences = mContext.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return (preferences.getString("languague","null") != "null");
    }
    public void clear(){
        SharedPreferences preferences = mContext.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
    }
}
