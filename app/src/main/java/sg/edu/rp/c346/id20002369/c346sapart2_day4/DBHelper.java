package sg.edu.rp.c346.id20002369.c346sapart2_day4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "PSI.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_PSI = "PSI Reading";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_INDEX = "index";
    private static final String COLUMN_PLACE = "place";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // CREATE TABLE Song
        // (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT,
        // singers TEXT, stars INTEGER, year INTEGER );
        String createPSITableSql = "CREATE TABLE " + TABLE_PSI + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_INDEX + " INTEGER, "
                + COLUMN_PLACE + " TEXT )";
        db.execSQL(createPSITableSql);
        Log.i("info", createPSITableSql + "\ncreated tables");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PSI);
        onCreate(db);
    }

    public long insertReading(int index, String place) {
        // Get an instance of the database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_INDEX, index);
        values.put(COLUMN_PLACE, place);

        // Insert the row into the TABLE_SONG
        long result = db.insert(TABLE_PSI, null, values);
        // Close the database connection
        db.close();
        Log.d("SQL Insert","" + result);
        return result;
    }

    public ArrayList<PSIReading> getAllReadings() {
        ArrayList<PSIReading> PSIReadingslist = new ArrayList<PSIReading>();
        String selectQuery = "SELECT " + COLUMN_ID + ","
                + COLUMN_INDEX + "," + COLUMN_PLACE + " FROM " + TABLE_PSI;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                int index = cursor.getInt(1);
                String place = cursor.getString(2);
                PSIReading newPSIReading = new PSIReading(id, index, place);
                PSIReadingslist.add(newPSIReading);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return PSIReadingslist;
    }

}
