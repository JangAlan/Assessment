package com.gilang.assessment.androiddeveloper.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.google.gson.Gson;

import java.util.Locale;


public class AppSessionManager {

    public static final String TAG = AppSessionManager.class.getSimpleName();

    // Shared Preferences
    private SharedPreferences pref;

    // Editor for Shared preferences
    private SharedPreferences.Editor editor;

    // Context
    private Context context;

    // Shared pref mode
    private int PRIVATE_MODE = 0;

    private static final String EMPTY_STRING = "";

    // Shared key name
    public static final String GCM_REG_ID = "ciwaru_gcm_registration_id";
    public static final String TOKEN = "ciwaru_token";
    public static final String REFRESH_TOKEN = "ciwaru_refresh_token";
    public static final String LANGUAGE = "ciwaru_language";
    public static final String USER_ACCOUNT = "ciwaru_user_account";
    public static final String USER_ACCOUNT_TEMP = "ciwaru_user_account_temp";


    private final String SESSION_PREFERENCE = "ciwaru_session_pref_demo";

    public AppSessionManager(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(SESSION_PREFERENCE, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Clear all preference data
     */
    public void clearAllPreference() {
        editor.clear();
        editor.apply();
    }

    /**
     * Store string data to preferences
     */
    public void putStringData(String key, String value) {
        editor.putString(key, value);
        editor.apply();
    }

    /**
     * Get string data from preference
     */
    public String getStringData(String key) {
        return pref.getString(key, EMPTY_STRING);
    }


    /**
     * Remove string data from
     */
    public void removeStringData(String key) {
        editor.remove(key);
        editor.apply();
    }

    /**
     * Put Boolean data to preference
     *
     */
    public void putBooleanData(String key, Boolean value) {
        editor.putBoolean(key, value);
        editor.apply();
    }

    /**
     * Get Boolean data from preference
     * <br>Default value : FALSE
     *
     */
    public Boolean getBooleanData(String key) {
        return pref.getBoolean(key, Boolean.FALSE);
    }

    /**
     * Put Integer data to preference
     *
     */
    public void putIntegerData(String key, Integer value) {
        editor.putInt(key, value);
        editor.apply();
    }

    /**
     * Get Integer data from preference
     * <br>Default value : 0
     *
     */
    public Integer getIntegerData(String key) {
        return pref.getInt(key, 0);
    }


    /**
     * Put Float data to preference
     */
    public void putFloatData(String key, Float value) {
        editor.putFloat(key, value);
        editor.apply();
    }

    /**
     * Get Float data from preference
     * <br>Default value : 0
     */
    public Float getFloatData(String key) {
        return pref.getFloat(key, 0);
    }

    /**
     * Remove data from preference
     */
    public void removeData(String key) {
        editor.remove(key);
        editor.apply();
    }

    /**
     * Put list of object to shared preference
     * <br>list object must be converted to json string
     */


    /**
     * Get gcm key
     */
    public String getGcmKey() {
        return getStringData(GCM_REG_ID);
    }

    public void setGcmKey(String GcmKey) {
        putStringData(GCM_REG_ID, GcmKey);
    }


    public boolean isUserLogon() {
        String userSession = getStringData(AppSessionManager.USER_ACCOUNT);
        if (userSession.equals("")) {
            return false;
        }

        return true;
    }


    public void setLanguage(String language) {

        putStringData(LANGUAGE, language);
    }

    public String getLanguage() {
        return getStringData(LANGUAGE);
    }

    public void setToken(String token) {
        putStringData(TOKEN, token);
    }

    public String getToken() {
        return getStringData(TOKEN);
    }

    public void setRefreshToken(String refreshToken) {
        putStringData(REFRESH_TOKEN, refreshToken);
    }

    public String getRefreshToken() {
        return getStringData(REFRESH_TOKEN);
    }

    public void setLocale(String lang) {
        Locale myLocale;
        myLocale = new Locale(lang);
        Resources res = context.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
    }

    /*public void setUserAccount(Obj_AuthUser userData) {
        if (userData != null) {
            String data = new Gson().toJson(userData);
            putStringData(USER_ACCOUNT, data);
        } else {
            putStringData(USER_ACCOUNT, "");
        }
    }

    public Obj_AuthUser getUserProfile() {
        return new Gson().fromJson(getStringData(USER_ACCOUNT), Obj_AuthUser.class);
    }

    public void setUserAccountTemp(Obj_AuthUser userData) {
        if (userData != null) {
            String data = new Gson().toJson(userData);
            putStringData(USER_ACCOUNT_TEMP, data);
        } else {
            putStringData(USER_ACCOUNT_TEMP, "");
        }
    }

    public Obj_AuthUser getUserProfileTemp() {
        return new Gson().fromJson(getStringData(USER_ACCOUNT_TEMP), Obj_AuthUser.class);
    }*/

}
