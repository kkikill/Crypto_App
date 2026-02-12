package com.example.cryptoapp.session;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

    private SharedPreferences prefs;

    public SessionManager(Context context) {
        prefs = context.getSharedPreferences("session", Context.MODE_PRIVATE);
    }

    public void saveUser(String name) {
        prefs.edit().putString("user", name).apply();
    }

    public String getUser() {
        return prefs.getString("user", "Guest");
    }
}
