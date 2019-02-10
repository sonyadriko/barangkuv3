package com.example.xdreamer.barangkuv3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.HashMap;

public class UserPreference {

  /*  private String KEY_NAME = "name";
    private String KEY_EMAIL = "email";
    private String KEY_PHONE_NUMBER = "phone_number";
    private SharedPreferences preferences;
    UserPreference(Context context){
        String PRESF_NAME = "UserPref";
        preferences = context.getSharedPreferences(PRESF_NAME, Context.MODE_PRIVATE);
    }
    public void setKEY_NAME(String name){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_NAME, name);
        editor.apply();
    }
    public String getKEY_NAME(){
        return preferences.getString(KEY_NAME, null);
    }
    public void setKEY_EMAIL(String email){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_EMAIL, email);
        editor.apply();
    }
    public String getKEY_EMAIL(){
        return preferences.getString(KEY_EMAIL, null);
    }
    public void setKEY_PHONE_NUMBER(int phoneNumber){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(KEY_PHONE_NUMBER, phoneNumber);
        editor.apply();
    }
    public int getKEY_PHONE_NUMBER(){
        return preferences.getInt(KEY_PHONE_NUMBER, 0);
    }
*/


  /*
  SharedPreferences sharedPreferences;

  SharedPreferences.Editor editor;

  Context _context;

  int PRIVATE_MODE = 0;

  private static final String PREFER_NAME = "member";

  private static final String IS_USER_LOGIN = "IsUserLogin";

  public static final String KEY_NAME = "name";

  public static final String KEY_EMAIL = "email";

  public UserPreference(Context context){
      this._context = context;
      sharedPreferences = _context.getSharedPreferences(PREFER_NAME, PRIVATE_MODE);
      editor = sharedPreferences.edit();
  }

  public void createUserSessionLogin(String name, String email){
      editor.putBoolean(IS_USER_LOGIN, true);

      editor.putString(KEY_NAME, name);

      editor.putString(KEY_EMAIL, email);

      editor.commit();
  }

public boolean checkLogin(){
      if (!this.isUserLoggedIn()){
          Intent i = new Intent(_context, MasukActivity.class);

          i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
          i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
          _context.startActivity(i);
          return true;
      }
      return false;

  }


  public HashMap<String, String> getUserDetails(){
      HashMap<String , String > user = new HashMap<>();
      user.put(KEY_NAME, sharedPreferences.getString(KEY_NAME, null));
      user.put(KEY_EMAIL, sharedPreferences.getString(KEY_EMAIL, null));

      return user;
  }

  public void logoutUser(){
      editor.clear();
      editor.commit();

      Intent p = new Intent(_context, MasukActivity.class);

      p.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
      p.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

      _context.startActivity(p);


  }
   */
}
