package com.example.mentor.debitmaneger;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelpher extends SQLiteOpenHelper {

public static final String DATABSE_NAME= "DebitManeger.db";


    private final String MY_QUERY = "SELECT Amount,Amount1 FROM GIVEMONEY a INNER JOIN TAKEMONEY b ON a.Id=b.Id WHERE b.Name1=?";
//GiveMoney Table

    public static final String TABLE_GIVE="GIVEMONEY";
    public static final String COL0_GIVE="Id";
    public static final String COL1_GIVE="Name";
    public static final String COL2_GIVE="Amount";
    public static final String COL3_GIVE="Des";
    public static final String COL4_GIVE="Dateg";


    //TakeMoney Table

    public static final String TABLE_TAKE="TAKEMONEY";
    public static final String COL0_TAKE= "Id";
    public static final String COL1_TAKE= "Name1";
    public static final String COL2_TAKE= "Amount1";
    public static final String COL3_TAKE= "Des1";
    public static final String COL4_TAKE= "Date1";

    //Combine Table

    public static final String TABLE_GT="GTMONEY";
    public static final String COL0_GT="Id";
    public static final String COL1_GT="Nameg";
    public static final String COL2_GT="Amountg";
    public static final String COL3_GT="Desg";
    public static final String COL4_GT= "Namet";
    public static final String COL5_GT= "Amountt";
    public static final String COL6_GT= "Dest";
    public static final String COL7_GT= "Gdate";
    public static final String COL8_GT= "Tdate";

    public DatabaseHelpher(Context context) {
        super(context, DATABSE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL("create table " +TABLE_GIVE+ "(Id Integer  primary key autoincrement, Name Text ,Amount int, Des Text,Dateg Text) " );
    db.execSQL(" create table " +TABLE_TAKE+ "(Id Integer primary key autoincrement, Name1 Text, Amount1 Text, Des1 Text,Date1 Text)");
    db.execSQL(" create table " +TABLE_GT+ "(Id Integer primary key autoincrement, Nameg Text, Amountg Text, Desg Text,Namet Text,Amountt Text,Dest Text,Gdate Text,Tdate Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(" DROP TABLE "+TABLE_GIVE);
        db.execSQL(" DROP TABLE " +TABLE_TAKE);
        db.execSQL(" DROP TABLE " +TABLE_GT);
    }
//    public Cursor GetListLogin() {
//
//        SQLiteDatabase db = getWritableDatabase();
//        Cursor result = db.rawQuery("select * from " + TABLE_GIVE, null);
//        return result;
//
//    }

    //Givemoney Activity

public Boolean GIVEMONEY(String name,int amount,String des,String gdate)
{
    SQLiteDatabase db= getWritableDatabase();
    ContentValues contentValues=new ContentValues();

    contentValues.put(COL1_GIVE,name);
    contentValues.put(COL2_GIVE,amount);
    contentValues.put(COL3_GIVE,des);
    contentValues.put(COL4_GIVE,gdate);

    long result = db.insert(TABLE_GIVE, null, contentValues);

    if (result == -1) {
        return false;
    } else {
        return true;
    }
}

    public Boolean saveData(String nameg,int amountg,String desg,String namet,int amountt,String dest,String dategive,String datetake)
    {
        SQLiteDatabase db= getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put(COL1_GT,nameg);
        contentValues.put(COL2_GT,amountg);
        contentValues.put(COL3_GT,desg);
        contentValues.put(COL4_GT,namet);
        contentValues.put(COL5_GT,amountt);
        contentValues.put(COL6_GT,dest);
        contentValues.put(COL7_GT,dategive);
        contentValues.put(COL8_GT,datetake);

        long result = db.insert(TABLE_GT, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

//Planet List GiveMoney

    public List<Planet> getList() {
        SQLiteDatabase db = getWritableDatabase();
        Planet planet = null;
        List<Planet>  mPlanetlis = new ArrayList<>();
        Cursor cursor = db.rawQuery("select Id,Name,Amount,Des,Dateg from " + TABLE_GIVE, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            planet = new Planet(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3),cursor.getString(4));
            mPlanetlis.add(planet);
            cursor.moveToNext();
        }

        cursor.close();
        return  mPlanetlis;

    }
// Combine List
    public List<RepoetPlanet> getcombList() {
        SQLiteDatabase db = getWritableDatabase();
        RepoetPlanet planet = null;
        List<RepoetPlanet>  mPlanetlis = new ArrayList<>();


//        Cursor cursor = db.rawQuery(MY_QUERY, new String[]{String.valueOf(name)});
        Cursor cursor = db.rawQuery("select Nameg,Namet,Amountg,Amountt from " + TABLE_GT, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            planet = new RepoetPlanet(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
            mPlanetlis.add(planet);
            cursor.moveToNext();
        }

        cursor.close();
        return  mPlanetlis;

    }

//TakeMoney Activity

    public Boolean TAKEMONEY(String name1,int amount1,String des1,String datet)
    {
        SQLiteDatabase db= getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL1_TAKE,name1);
        contentValues.put(COL2_TAKE,amount1);
        contentValues.put(COL3_TAKE,des1);
        contentValues.put(COL4_TAKE,datet);

        long result = db.insert(TABLE_TAKE,null, contentValues);

        if(result == -1)
        {
            return false;
        }
        else
            {
                return true;
            }

    }

    //Take List...........


    public List<Planettake> getList1() {
        SQLiteDatabase db = getWritableDatabase();
        Planettake planettake = null;
        List<Planettake>  Planetlist = new ArrayList<>();
        Cursor cursor = db.rawQuery("select Id,Name1,Amount1,Des1,Date1 from " + TABLE_TAKE, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            planettake = new Planettake(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3),cursor.getString(4));
            Planetlist.add(planettake);
            cursor.moveToNext();
        }
        cursor.close();
        return  Planetlist;
    }

     int amount;
    public int sumData()
    {
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("select sum(Amount1) from TAKEMONEY;", null);
        if(c.moveToFirst())
            amount = c.getInt(0);
        else
            amount = -1;
        c.close();
        //return c;
        return amount;
    }

    int amount1;
    public int sumDatagive()
    {
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("select sum(Amount) from GIVEMONEY;", null);
        if(c.moveToFirst())
            amount1 = c.getInt(0);
        else
            amount1 = -1;
        c.close();
        //return c;
        return amount1;
    }

    public Cursor filllist()
    {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("select Nameg,Namet from " + TABLE_GT, null);
        return cursor;
    }


}
