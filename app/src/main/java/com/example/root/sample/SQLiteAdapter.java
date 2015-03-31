package com.example.root.sample;

/**
 * Created by root on 30/3/15.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

public class SQLiteAdapter {

    public static final String MYDATABASE_NAME = "MY_DATABASE";
    public static final String MYDATABASE_TABLE = "MYTABLE";
    public static final int MYDATABASE_VERSION = 1;
    public static final String KEY_ID = "_id";
    public static final String KEY_CONTENT = "Content";
    public static final String KEY_CODE   = "xml";

    //create table MY_DATABASE (ID integer primary key, Content text not null);
    private static final String SCRIPT_CREATE_DATABASE =
            "create table " + MYDATABASE_TABLE + " ("
                    + KEY_ID + " integer primary key , "
                    + KEY_CONTENT + " text not null, "
                    + KEY_CODE + " text not null);";

    private SQLiteHelper sqLiteHelper;
    private SQLiteDatabase sqLiteDatabase;

    private Context context;

    public SQLiteAdapter(Context c){

        context = c;

    }

    public SQLiteAdapter openToRead() throws android.database.SQLException {
        sqLiteHelper = new SQLiteHelper(context, MYDATABASE_NAME, null, MYDATABASE_VERSION);
        sqLiteDatabase = sqLiteHelper.getReadableDatabase();
        return this;
    }

    public SQLiteAdapter openToWrite() throws android.database.SQLException {
        sqLiteHelper = new SQLiteHelper(context, MYDATABASE_NAME, null, MYDATABASE_VERSION);
        sqLiteDatabase = sqLiteHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        sqLiteHelper.close();
    }

    public long insert(String id, String content, String xml){

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_ID, id );
        contentValues.put(KEY_CODE, xml );
        contentValues.put(KEY_CONTENT, content);


        return sqLiteDatabase.insert(MYDATABASE_TABLE, null, contentValues);
    }

    public int deleteAll(){
        return sqLiteDatabase.delete(MYDATABASE_TABLE, null, null);
    }
    public String getFormCode(String id) {

        int fid= Integer.parseInt(id);

        Cursor cursor = sqLiteDatabase.query(MYDATABASE_TABLE, new String[] { KEY_ID,
                        KEY_CONTENT, KEY_CODE }, KEY_ID + "=?",
                new String[] { String.valueOf(fid) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Log.d("__---id asked for:", "" + fid);

        // return form code

        return cursor.getString(2);
    }
    public Cursor queueAll(){
        String[] columns = new String[]{KEY_ID, KEY_CONTENT};
        Cursor cursor = sqLiteDatabase.query(MYDATABASE_TABLE, columns,
                null, null, null, null, null);

        return cursor;
    }

    public class SQLiteHelper extends SQLiteOpenHelper {

        public SQLiteHelper(Context context, String name,
                            CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // TODO Auto-generated method stub
            db.execSQL(SCRIPT_CREATE_DATABASE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO Auto-generated method stub

        }

    }


}