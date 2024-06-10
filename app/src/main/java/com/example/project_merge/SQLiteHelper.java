package com.example.project_merge;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "icheonnational.db";
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
        sqLiteDatabase.execSQL("INSERT INTO Memberss VALUES (3,'이기윤', 19270227, 20100412, 20100414, '참전', '김수임', null, 1788, 371266029, 127.4905464);");
        sqLiteDatabase.execSQL("INSERT INTO Memberss VALUES (4,'정재만', 19490809, 20090107, 20090110, '참전', null, null, 169, 37.1267237, 127.4896283);");
        sqLiteDatabase.execSQL("INSERT INTO Memberss VALUES (5,'박주양', 19290325, 20070109, 20141030, '참전', null, null, 153, 37.1253002, 127.4918769);");
        sqLiteDatabase.execSQL("INSERT INTO Memberss VALUES (6,'이시태', 19300822, 20160505, 20160622, '참전', null, null, 3175, 37.125525469926, 127.49231985782);");
        sqLiteDatabase.execSQL("INSERT INTO Memberss VALUES (7,'이시환', 19281105, 19870819, 20090613, '제대군인(10년)', null, null, 588, 37.1267842, 127.4900984);");
        sqLiteDatabase.execSQL("INSERT INTO Memberss VALUES (8,'김일준', 19260610, 20081125, 20081127, '참전', null, null, 1521, 37.125573, 127.4918115);");
        sqLiteDatabase.execSQL("INSERT INTO Memberss VALUES (9,'한승현', 19270828, 20110819, 20110821, '참전', null, null, 1878, 37.1261357, 127.4908811);");
        sqLiteDatabase.execSQL("INSERT INTO Memberss VALUES (10,'김세현', 19270922, 20100819, 20100821, '참전', '김기일', null, 1102, 37.1266088, 127.4907831);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if (oldVersion < newVersion) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Memberss");
            onCreate(sqLiteDatabase);
        }
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
        Integer burrDay;
        Integer graveno;

        public MemberDetails(String name, Integer birthDay, Integer deathDay, String pname1, String pname2, String qualification, Integer burrDay, Integer graveno) {
            this.name = name;
            this.birthDay = birthDay;
            this.deathDay = deathDay;
            this.pname1 = pname1;
            this.pname2 = pname2;
            this.qualification = qualification;
            this.burrDay = burrDay;
            this.graveno = graveno;
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
        public Integer getBurrDay() { return burrDay; }
        public Integer getGraveno() { return  graveno; }
    }

    public MemberDetails getMemberDetailsById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT Name, birthDay, deathDay, pname1, pname2, qualification, burrDay, graveno FROM Memberss WHERE id = ?", new String[]{String.valueOf(id)});
        MemberDetails memberDetails = null;
        if (cursor.moveToFirst()) {
            String name = cursor.getString(0);
            int birthDay = cursor.getInt(1);
            int deathDay = cursor.getInt(2);
            String pname1 = cursor.getString(3);
            String pname2 = cursor.getString(4);
            String qualification = cursor.getString(5);
            int burryDay = cursor.getInt(6);
            int graveno = cursor.getInt(7);
            return new MemberDetails(name, birthDay, deathDay, pname1, pname2, qualification, burryDay, graveno);
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
