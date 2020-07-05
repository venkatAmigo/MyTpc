package com.vvitmdc.chats;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SavePreference {
    static SharedPreferences getShare(Context context)
    {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
    public static void setLogin(Context context,boolean login,String user)
    {
        SharedPreferences.Editor editor=getShare(context).edit();
        editor.putBoolean("lin",login);
        editor.putString("user",user);
        editor.apply();
    }
    public static boolean getLogin(Context context)
    {
        return  getShare(context).getBoolean("lin",false);
    }
    public static String getUser(Context context)
    {
        return  getShare(context).getString("user","tpo");
    }
}
