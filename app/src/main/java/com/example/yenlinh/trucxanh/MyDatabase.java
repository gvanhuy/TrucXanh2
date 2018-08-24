package com.example.yenlinh.trucxanh;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by yenlinh on 11/16/2016.
 */

public class MyDatabase  {
    private static final String DATABASE_NAME = "DB_USER";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_ACCOUNT = "ACCOUNT";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_ACC = "tentaikhoan";
    public static final String COLUMN_PASSWORD = "matkhau";
    public static final String COLUMN_SCORE = "score";

    private static Context context;
    static SQLiteDatabase db;
    private OpenHelper openHelper;

    public MyDatabase (Context c) {
        MyDatabase.context = c;
    }

    public MyDatabase open() throws SQLException{
        openHelper = new OpenHelper(context);
        db = openHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        openHelper.close();
    }

    public long createData(String tenDN, String matKhau) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ACC, tenDN);
        cv.put(COLUMN_PASSWORD, matKhau);
        cv.put(COLUMN_SCORE, "0");
        return db.insert(TABLE_ACCOUNT, null, cv);
    }

    /* hàm lay tat cac danh sach dang chuoi*/
    public String getallData() {
        String[] columns = new String[] {COLUMN_ID,COLUMN_ACC,COLUMN_PASSWORD,COLUMN_SCORE};
        Cursor c = db.query(TABLE_ACCOUNT, columns, null, null, null, null, null);//may tham so sau la group by v..v
        if(c==null)
            Log.v("Cursor", "C is NULL");
        String result="";
        int iRow = c.getColumnIndex(COLUMN_ID);
        int iN = c.getColumnIndex(COLUMN_ACC);
        int iMK = c.getColumnIndex(COLUMN_PASSWORD);
        int iScore = c.getColumnIndex(COLUMN_SCORE);
        for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
            result = result +" "+ c.getString(iRow)
                    + " - id:" + c.getString(iN)
                    + " - pw:" + c.getString(iMK)
                    + " - diem:" + c.getString(iScore) + "\n";
        }
        c.close();
        Log.v("Result", result);
        return result;
    }

    /* ham tra vew mot mang cac nhan vien -*/
    /* hàm lay tat cac danh sach*/
    public ArrayList<String> getArrayData() {
        String[] columns = new String[] {COLUMN_ID,COLUMN_ACC,COLUMN_PASSWORD,COLUMN_SCORE};
        Cursor c = db.query(TABLE_ACCOUNT, columns, null, null, null, null, null);//may tham so sau la group by v..v
        if(c==null)
            Log.v("Cursor", "C is NULL");
        ArrayList<String> result=new ArrayList<String>();
        String temp = " ";
        int iRow = c.getColumnIndex(COLUMN_ID);
        int iN = c.getColumnIndex(COLUMN_ACC);
        int iMK = c.getColumnIndex(COLUMN_PASSWORD);
        int iScore = c.getColumnIndex(COLUMN_SCORE);
        for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
            temp =   c.getString(iRow)
                    + " - id:" + c.getString(iN)
                    + " - pw:" + c.getString(iMK)
                    + " - diem:" + c.getString(iScore);
            result.add(temp);
        }
        c.close();

        return result;
    }


    /* ham lay 1 nhan vien */
    public String getData(String id) {
        String[] columns = new String[] {COLUMN_ID,COLUMN_ACC,COLUMN_PASSWORD,COLUMN_SCORE};
        Cursor c = db.rawQuery("select * from "+TABLE_ACCOUNT+" where "+COLUMN_ID+" = ? ", new String[] { id });
        if(c==null)
            Log.v("Cursor", "C is NULL");

        String result="";

        if(c.getCount()==1){
            int iRow = c.getColumnIndex(COLUMN_ID);
            int iN = c.getColumnIndex(COLUMN_ACC);
            int iMK = c.getColumnIndex(COLUMN_PASSWORD);
            int iSCore = c.getColumnIndex(COLUMN_SCORE);

            c.moveToFirst();
            result = result +" "+ c.getString(iRow)
                    + " - ID:" + c.getString(iN)
                    + " - passWord:" + c.getString(iMK)
                    + " - diem:" + c.getString(iSCore)
                    + "\n";
            c.close();
            return result;
        }else{
            c.close();
            return "Ko Thay tai khoan";
        }

    }

    /* ham lay id */
    public String getId(String acc, String mk){
        String m_id = "";
        Cursor c = db.rawQuery("select * from "+TABLE_ACCOUNT+" where "+COLUMN_ACC+" = ? and "+COLUMN_PASSWORD+" = ?", new String[] { acc, mk });
        if(c==null)
            Log.v("Cursor", "C is NULL");


        if(c.getCount()==1){
            int keyid = c.getColumnIndex(COLUMN_ID);
            c.moveToFirst();
            m_id=c.getString(keyid);
            c.close();
            return m_id;
        }else{
            c.close();
            return "Ko Thay ID";
        }

    }

    /* ham lay role */
    public String getSCore(String acc, String mk){
        String m_id = "";
        Cursor c = db.rawQuery("select * from "+TABLE_ACCOUNT+" where "+COLUMN_ACC+" = ? and "+COLUMN_PASSWORD+" = ?", new String[] { acc, mk });
        if(c==null)
            Log.v("Cursor", "C is NULL");


        if(c.getCount()==1){
            int keyrole = c.getColumnIndex(COLUMN_SCORE);
            c.moveToFirst();
            m_id=c.getString(keyrole);
            c.close();
            return m_id;
        }else{
            c.close();
            return "Ko Thay Role";
        }

    }

    /*Hàm đăng nhập với đối số đầu vào là tên acc và mật khẩu*/
    public boolean kiemTraLogin(String acc,String mk){
        Cursor c = db.rawQuery("select * from "+TABLE_ACCOUNT+" where "+COLUMN_ACC+" = ? and "+COLUMN_PASSWORD+" = ?", new String[] { acc,mk });
        if(c.getCount()==1){
            c.close();
            return true;
        }else{
            c.close();
            return false;
        }
    }

    /* hàm kiêm tra có trùng tên */
    public boolean checkUser(String acc){
        Cursor c = db.rawQuery("select * from "+TABLE_ACCOUNT+" where "+COLUMN_ACC+" = ? ", new String[] { acc });
        if(c.getCount()==1){
            c.close();
            return true;
        }else{
            c.close();
            return false;
        }
    }

    /*Hàm xóa một tài khoản với đối số đầu vào là acc cần xóa*/
    public int deleteAcc(String acc) {
        return db.delete(TABLE_ACCOUNT, COLUMN_ACC + "='" + acc + "'", null);
    }

    /*Hàm xóa toàn bộ table ACCOUNT*/
    public int deleteAccountAll() {
        return db.delete(TABLE_ACCOUNT, null, null);
    }


    /*Hàm cập nhật tên người dùng với đầu vào là acc, mật khẩu cần thay đổi*/
    public boolean setScore(String id, String score){
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_SCORE, score);
        long kq = db.update(TABLE_ACCOUNT,cv,COLUMN_ID+"='"+id+"'", null);
        if (kq==0)
            return false;
        else
            return true;
    }




    //---------------- class OpenHelper ------------------
    private static class  OpenHelper extends SQLiteOpenHelper{
        public OpenHelper(Context context){
            super( context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate (SQLiteDatabase arg0){
            arg0.execSQL("CREATE TABLE "+ TABLE_ACCOUNT+" ("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_ACC + " TEXT NOT NULL, "
                    + COLUMN_PASSWORD + " TEXT NOT NULL, "
                    + COLUMN_SCORE + " TEXT NOT NULL);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
            arg0.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCOUNT);
            onCreate(arg0);
        }

    }

}


