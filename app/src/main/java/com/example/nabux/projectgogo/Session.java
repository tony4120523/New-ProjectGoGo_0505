package com.example.nabux.projectgogo;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by lala on 2016/4/10.
 */
public class Session {
    private SharedPreferences prefs;

    public Session(Context cntx) {
        // TODO Auto-generated constructor stub
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);

    }

    public void setUserAccount(String userAccount) {
        prefs.edit().putString("userAccount", userAccount).commit();
        //prefsCommit();
    }

    public String getUserAccount() {
        String userAccount = prefs.getString("userAccount","");
        return userAccount;
    }

    public void setUserPSD(String userpsd) {
        prefs.edit().putString("userpsd", userpsd).commit();
        //prefsCommit();
    }

    public String getUserPSD() {
        String userpsd = prefs.getString("userpsd","");
        return userpsd;
    }

    public void setUserID(String userID) {
        prefs.edit().putString("userID",userID).commit();
    }

    public String getUserID() {
        String userID = prefs.getString("userID","");
        return userID;
    }

    public void setNickName(String NickName) {
        prefs.edit().putString("NickName",NickName).commit();
    }

    public String getNickName() {
        String NickName = prefs.getString("NickName","");
        return NickName;
    }
    public void setToken(String token) {
        prefs.edit().putString("token", token).commit();
    }
    public String getToken() {
        String token = prefs.getString("token","");
        return token;
    }
    public void setUserCPemail(String user_cp_email) {
        prefs.edit().putString("CPemail", user_cp_email).commit();
    }
    public String getUserCPemail() {
        String CPemail = prefs.getString("CPemail","");
        return CPemail;
    }
    public void setHypertension(String user_hypertension) {
        prefs.edit().putString("hypertension", user_hypertension).commit();
    }
    public String getHypertension() {
        String hypertension = prefs.getString("hypertension","");
        return hypertension;
    }
    public void setDiabetes(String user_diabetes) {
        prefs.edit().putString("diabetes", user_diabetes).commit();
    }
    public String getDiabetes() {
        String diabetes = prefs.getString("diabetes","");
        return diabetes;
    }
    public void setDisease(String user_disease) {
        prefs.edit().putString("disease", user_disease).commit();
    }
    public String getDisease() {
        String disease = prefs.getString("disease","");
        return disease;
    }
}

