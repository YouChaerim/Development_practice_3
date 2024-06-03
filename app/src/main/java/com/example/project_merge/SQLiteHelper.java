package com.example.project_merge;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "icheonmember.db";
    private static final int DATABASE_VERSION = 1;

    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Memberss");

        sqLiteDatabase.execSQL("create table Memberss (id integer primary key autoincrement, Name text, birthDay integer, deathDay integer, burrDay integer, qualification text, pname1 text, pname2 text, graveno integer, latitude real, longitude real);");

        sqLiteDatabase.execSQL("INSERT INTO Memberss VALUES (1,'강기동', 19331108, 19890722, 20160909, '참전', null, null, 1019, 37.12534249298, 127.492461511984);");
        sqLiteDatabase.execSQL("INSERT INTO Memberss VALUES (2,'이강훈', 19280125, 20090122, 20090124, '참전', '오세인', null, 294, 37.1267789, 127.4896179);");
        sqLiteDatabase.execSQL("INSERT INTO Memberss VALUES (3,'이강훈', 19281210, 20040510, 20080719, '참전', null, null, 2003, 37.1270076, 127.4890932);");
        sqLiteDatabase.execSQL("INSERT INTO Memberss VALUES (4,'이강훈', 19260330, 20100416, 20100418, '상이', null, null, 1841, 37.1265842, 127.4905129);");
        sqLiteDatabase.execSQL("INSERT INTO Memberss VALUES (5,'이강훈', 19300525, 20121128, 20121130, '상이', null, null, 1725, 37.1259209, 127.4904677);");
        sqLiteDatabase.execSQL("INSERT INTO Memberss VALUES (6,'이강훈', 19450404, 20140713, 20140715, '참전', null, null, 2840, 37.1254759, 127.4913315);");
        sqLiteDatabase.execSQL("INSERT INTO Memberss VALUES (7,'김철수', 19370921, 20170401, 20170403, '참전', null, null, 2239, 37.125271330622, 127.493053190529);");
        sqLiteDatabase.execSQL("INSERT INTO Memberss VALUES (8,'김철수', 19320715, 20080926, 20080928, '무공', null, null, 751, 37.124759, 127.4913315);");
        sqLiteDatabase.execSQL("INSERT INTO Memberss VALUES (9,'김철수', 19450404, 20140713, 20140715, '참전', null, null, 2840, 37.1269185, 127.4892953);");
        sqLiteDatabase.execSQL("INSERT INTO Memberss VALUES (10,'김철수', 19490402, 20070109, 20080603, '참전', null, null, 305, 37.1270557, 127.4889089);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public ArrayList<String> getMemberNames (){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT Name FROM Memberss",null);
        ArrayList<String> result = new ArrayList<>();
        while (cursor.moveToNext()) {
            result.add(cursor.getString(0));
        }
        cursor.close();
        return result;
    }

    public boolean checkMemberName(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Memberss WHERE Name = ?", new String[]{name});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    public class MemberDetails {
        String name;
        Integer birthDay;
        Integer deathDay;
        String pname1;
        String pname2;
        String qualification;

        public MemberDetails(String name, Integer birthDay, Integer deathDay, String pname1, String pname2, String qualification) {
            this.name = name;
            this.birthDay = birthDay;
            this.deathDay = deathDay;
            this.pname1 = pname1;
            this.pname2 = pname2;
            this.qualification = qualification;
        }

        // Getters
        public String getName() {
            return name;
        }

        public Integer getBirthDay() {
            return birthDay;
        }

        public Integer getDeathDay() {
            return deathDay;
        }
        public String getPname1() {
            return pname1;
        }
        public String getPname2() {
            return pname2;
        }
        public String getQualification() { return qualification; }
    }

    public MemberDetails getMemberDetailsById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT Name, birthDay, deathDay, pname1, pname2, qualification FROM Memberss WHERE id = ?", new String[]{String.valueOf(id)});
        MemberDetails memberDetails = null;
        if (cursor.moveToFirst()) {
            String name = cursor.getString(0);
            int birthDay = cursor.getInt(1);
            int deathDay = cursor.getInt(2);
            String pname1 = cursor.getString(3);
            String pname2 = cursor.getString(4);
            String qualification = cursor.getString(5);
            return new MemberDetails(name, birthDay, deathDay, pname1, pname2, qualification);
        }
        cursor.close();
        return null;
    }
    public int getMemberIdByName(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id FROM memberss WHERE name=?", new String[]{name});
        if (cursor != null && cursor.moveToFirst()) {
            @SuppressLint("Range")
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            cursor.close();
            return id;
        }
        return -1;
    }
}
