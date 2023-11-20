package com.anusikh.app.util;

import android.content.Context;
import android.content.SharedPreferences;

public class ClearSharedPref {

    public static void clear(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("accessToken");
        editor.remove("refreshToken");
        editor.remove("expiresAt");
        editor.apply();
    }
}
