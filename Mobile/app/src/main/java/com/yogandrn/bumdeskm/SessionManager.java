package com.yogandrn.bumdeskm;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private Context context;

    private static final String IS_LOGGED_IN = "isLoggedin";
    private static final String ID_USER = "id_user";
    private static final String USERNAME = "username";
    private static final String EMAIL = "email";

//    public SessionManager(Context context) {
//        this.context = context;
//        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
//        editor = sharedPreferences.edit();
//    }
//
//    public void createLoginSession(String id_user){
//        editor.putBoolean(IS_LOGGED_IN, true);
//        editor.putString(ID_USER, id_user);
//        editor.commit();
//    }
//
//    public HashMap<String, String> getSesiUser(){
//        HashMap<String, String> user = new HashMap<>();
//        user.put(ID_USER, sharedPreferences.getString(ID_USER, null));
//        user.put(USERNAME, sharedPreferences.getString(USERNAME, null));
//        user.put(EMAIL, sharedPreferences.getString(EMAIL, null));
//        return user;
//    }
//
    public void logoutSession(){
        editor.clear();
        editor.commit();
    }
//
//    public boolean isLoggedin(){
//        return sharedPreferences.getBoolean(IS_LOGGED_IN, false);
//    }


    public SessionManager(Context context) {
        sharedPreferences = context.getSharedPreferences("AppKey", 0);
        editor = sharedPreferences.edit();
        editor.apply();
    }

    public void setLogin(boolean login) {
        editor.putBoolean("KEY_LOGIN", login);
        editor.commit();
    }

    public boolean getLogin(){
        return sharedPreferences.getBoolean("KEY_LOGIN", false);
    }

    public void setSessionID(String id){
        editor.putString("ID_USER", id);
        editor.commit();
    }

    public String getSessionID() {
        String id_user = sharedPreferences.getString("ID_USER", "");
        return id_user;
    }

}
