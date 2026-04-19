package com.example.cryptoapp.session;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

    private SharedPreferences prefs;

    public SessionManager(Context context) {
        prefs = context.getSharedPreferences("session", Context.MODE_PRIVATE);
    }

    public void saveUser(String displayName) {
        prefs.edit().putString("displayName", displayName).apply();
    }

    public String getUser() {
        return prefs.getString("displayName", "Гость");
    }

    public void saveUserLogin(String login) {
        prefs.edit().putString("userLogin", login).apply();
    }

    public String getUserLogin() {
        return prefs.getString("userLogin", "guest@example.com");
    }
}
