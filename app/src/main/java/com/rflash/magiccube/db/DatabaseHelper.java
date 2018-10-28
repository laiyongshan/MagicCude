package com.rflash.magiccube.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.rflash.magiccube.Config;
import com.rflash.magiccube.ui.cardrecord.CardRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangfan on 2017/11/30.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String TAG = "DatabaseHelper";

    /**
     * 构造方法
     * 每次创建DatabaseHelper对象时，若本应用无该数据库，则新建数据库并调用onCreate方法；
     * 若该数据库已创建则直接使用已存在的数据库且跳过onCreate方法
     * factory : 当打开的数据库执行查询语句的时候 会创建一个Cursor对象, 这时会调用Cursor工厂类 factory, 可以填写null默认值
     *
     * @param context 上下文
     */

    public DatabaseHelper(Context context) {
        super(context, Config.DB_NAME, null, Config.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.beginTransaction();
        //创建user表，属性：id（用户id，主键）、date（时间）、money（消费金额 单位：元）、bankcard(卡号)
        try {
            db.execSQL("CREATE TABLE " + Config.DB_TABLE + " (id INTEGER PRIMARY KEY AUTOINCREMENT, date TEXT,money TEXT,bankcard LONG)");
            db.setTransactionSuccessful();
        }catch (Exception e){
            e.printStackTrace();
            Log.i(TAG,"创建表失败");
        }
        db.endTransaction();
    }

    /**
     * 更新数据库
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void insertAUser(CardRecord user) {
        SQLiteDatabase database = getReadableDatabase();
        try {
            database.beginTransaction();
            //向user表插入一条数据
            database.execSQL(
                    "INSERT INTO "+Config.DB_TABLE+" (money, date,bankcard) VALUES(?,?,?)",
                    new Object[]{user.getMoney(), user.getDate(), user.getBankcard()});
            database.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
            Log.i(TAG,"插入数据失败");
        }
        database.endTransaction();
    }

    /**
     * 获取整个用户列表
     *
     * @return
     */
    public List<CardRecord> readAllUser() {
        SQLiteDatabase database = getReadableDatabase();
        database.beginTransaction();
        List<CardRecord> list = new ArrayList<>();
        try {
            Cursor cursor = database.rawQuery("SELECT * FROM " + Config.DB_TABLE +" order by id desc", new String[]{});
            while (cursor.moveToNext()) {
                CardRecord user = new CardRecord();
                user.setMoney(cursor.getString(cursor.getColumnIndex("money")));
                user.setBankcard(cursor.getString(cursor.getColumnIndex("bankcard")));
                user.setDate(cursor.getString(cursor.getColumnIndex("date")));
                list.add(user);
            }
            cursor.close();
            database.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
            Log.i(TAG,"查询列表失败");
        }
        database.endTransaction();
        return list;
    }

}
