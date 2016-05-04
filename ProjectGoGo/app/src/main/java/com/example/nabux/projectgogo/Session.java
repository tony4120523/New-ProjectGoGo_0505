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

    public void setUserID(String userid) {
        prefs.edit().putString("userid", userid).commit();
        //prefsCommit();
    }

    public String getUserID() {
        String userid = prefs.getString("userid","");
        return userid;
    }

    public void setUserPSD(String userpsd) {
        prefs.edit().putString("userpsd", userpsd).commit();
        //prefsCommit();
    }

    public String getUserPSD() {
        String userpsd = prefs.getString("userpsd","");
        return userpsd;
    }

}

