package isfaaghyth.app.note;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Isfahani on 28-Agu-16.
 */
public class SQLiteNote extends SQLiteOpenHelper {

    private static final String TAG = SQLiteNote.class.getSimpleName();
    // Versi Database
    private static final int DATABASE_VERSION = 1;

    // Nama Database
    private static final String DATABASE_NAME = "sttnf_result";

    // Nama Table
    private static final String TABLE_NOTE = "tbl_note_daeng";

    // Kolom - kolom table
    public static final String KEY_ID = "_id";
    public static final String KEY_WAKTU = "waktu";
    public static final String KEY_JUDUL = "judul";
    public static final String KEY_KONTEN = "konten";
    public static final String KEY_GAMBAR = "gambar";
    public static final String KEY_LOKASI = "lokasi";

    public SQLiteNote(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_NOTE + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + KEY_WAKTU + " TEXT,"
                + KEY_JUDUL + " TEXT,"
                + KEY_KONTEN + " TEXT,"
                + KEY_GAMBAR + " TEXT,"
                + KEY_LOKASI + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_LOGIN_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXIST " + TABLE_NOTE);
        onCreate(sqLiteDatabase);
    }

    public void addData(String waktu, String judul, String konten, String gambar, String lokasi) {
        SQLiteDatabase db = this.getWritableDatabase();

            if (!CheckData(judul)) {
                ContentValues values = new ContentValues();
                values.put(KEY_WAKTU, waktu);
                values.put(KEY_JUDUL, judul);
                values.put(KEY_KONTEN, konten);
                values.put(KEY_GAMBAR, gambar);
                values.put(KEY_LOKASI, lokasi);

                db.insert(TABLE_NOTE, null, values);
            } else {
                try {
                    db.beginTransaction();
                    db.execSQL("UPDATE " + TABLE_NOTE +
                            " SET waktu='" + waktu + "', judul='" + judul + "', konten='" + konten +
                            "', gambar='" + gambar + "', lokasi='" + lokasi + "' WHERE judul='" + judul + "'");
                    db.setTransactionSuccessful();
                } finally {
                    db.endTransaction();
                }
            }

        db.close();
    }

    public List<ItemObject> getData() {
        List<ItemObject> usersdetail = new ArrayList<>();
        String USER_DETAIL_SELECT_QUERY = "SELECT * FROM " + TABLE_NOTE;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(USER_DETAIL_SELECT_QUERY, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    ItemObject itemObject = new ItemObject();
                    itemObject.waktu = cursor.getString(cursor.getColumnIndex(KEY_WAKTU));
                    itemObject.judul = cursor.getString(cursor.getColumnIndex(KEY_JUDUL));
                    itemObject.konten = cursor.getString(cursor.getColumnIndex(KEY_KONTEN));
                    itemObject.gambar = cursor.getString(cursor.getColumnIndex(KEY_GAMBAR));
                    itemObject.lokasi = cursor.getString(cursor.getColumnIndex(KEY_LOKASI));
                    usersdetail.add(itemObject);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "error bro");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return usersdetail;
    }

    public boolean CheckData(String judul) {
        SQLiteDatabase sqldb = this.getReadableDatabase();
        String Query = "SELECT * FROM " + TABLE_NOTE + " WHERE judul='" + judul + "'";
        Cursor cursor = sqldb.rawQuery(Query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    public void deleteItemSelected(String waktu) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            db.beginTransaction();
            db.execSQL("DELETE from " + TABLE_NOTE + " WHERE waktu ='" + waktu + "'");
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            Log.d(TAG, "Error while trying to delete  users detail");
        } finally {
            db.endTransaction();
        }
    }

}
