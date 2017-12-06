package com.leite.vanilson.startapp.repository;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.leite.vanilson.startapp.util.Constants;
import com.leite.vanilson.startapp.util.Util;

/**
 * Created by Vanilson on 07/07/17.
 */

public class LoginRepository extends SQLiteOpenHelper {
    public LoginRepository(Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder query = new StringBuilder();
        query.append("CREATE TABLE TB_LOGIN(");
        query.append("id_login INTEGER PRIMARY KEY AUTOINCREMENT,");
        query.append("user TEXT(15) NOT NULL,");
        query.append("password TEXT(15) NOT NULL)");
        db.execSQL(query.toString());
        populateDB(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void populateDB(SQLiteDatabase db) {
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO TB_LOGIN(user, password) VALUES(?, ?)");
        db.execSQL(query.toString(), new String[] {"admin", "admin"});
    }

    public void listLogins(Activity activity) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("TB_LOGIN", null, "id_login = ?", new String[] {"1"}, null, null, "user");
        String txt = "";
        while (cursor.moveToNext()) {
            txt = "Id " + String.valueOf(cursor.getInt(cursor.getColumnIndex("id_login")));
        }
//        Util.showMsgToast(activity, txt);
    }

    public void addLogin(String login, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("user", login);
        contentValues.put("password", password);
        db.insert("TB_LOGIN", null, contentValues);
    }

    public void updateLogin(String login, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("user", login);
        contentValues.put("password", password);
        db.insert("TB_LOGIN", null, contentValues);
        db.update("TB_LOGIN", contentValues, "id_login > 1", null);
    }

    public void deleteLogin(String login, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("TB_LOGIN", "user = ? or password = ?", new String[] {login, password});
    }
}