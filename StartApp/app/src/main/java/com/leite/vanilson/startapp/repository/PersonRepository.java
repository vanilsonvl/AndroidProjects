package com.leite.vanilson.startapp.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.leite.vanilson.startapp.util.Constants;

/**
 * Created by Vanilson on 12/12/2017.
 */

public class PersonRepository extends SQLiteOpenHelper {

    public PersonRepository(Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        StringBuilder query = new StringBuilder();
        query.append("CREATE TABLE TB_PERSON(");
        query.append("id_person INTEGER PRIMARY KEY AUTOINCREMENT,");
        query.append("name TEXT(30) NOT NULL,");
        query.append("address TEXT(50),");
        query.append("cpf TEXT(14),");
        query.append("cnpj TEXT(18),");
        query.append("gender INTEGER(1) NOT NULL,");
        query.append("job INTEGER(3) NOT NULL,");
        query.append("birthday INTEGER NOT NULL)");
        db.execSQL(query.toString());
    }
}
