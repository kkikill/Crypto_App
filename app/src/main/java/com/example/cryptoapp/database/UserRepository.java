package com.example.cryptoapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UserRepository {

    private UserDatabaseHelper helper;

    public UserRepository(Context context) {
        helper = new UserDatabaseHelper(context);
    }

    public boolean register(String name, String login, String pass) {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(UserDatabaseHelper.COL_NAME, name);
        cv.put(UserDatabaseHelper.COL_LOGIN, login);
        cv.put(UserDatabaseHelper.COL_PASS, pass);

        return db.insert(UserDatabaseHelper.TABLE, null, cv) != -1;
    }

    public String login(String login, String pass) {
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor c = db.query(
                UserDatabaseHelper.TABLE,
                null,
                "login=? AND password=?",
                new String[]{login, pass},
                null, null, null
        );

        if (c.moveToFirst()) {
            String name = c.getString(c.getColumnIndexOrThrow("name"));
            c.close();
            return name;
        }

        c.close();
        return null;
    }
}
