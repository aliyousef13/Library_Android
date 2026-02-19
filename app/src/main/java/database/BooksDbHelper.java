package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import models.AllBooks;

public class BooksDbHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "cyberlearn.db";
    public static final int DB_VERSION = 1;

    private SQLiteDatabase db;

    public BooksDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(AllBooks.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + AllBooks.TABLE_BOOKS);
        onCreate(sqLiteDatabase);
    }

    // insertBook
    public boolean insertBook(String name, int img) {
        ContentValues cv = new ContentValues();
        cv.put(AllBooks.COL_NAME, name);
        cv.put(AllBooks.COL_IMG, img);
        long res = db.insert(AllBooks.TABLE_BOOKS, null, cv);
        return res != -1;
    }

    //  Insert OR Replace
    public long insertOrReplace(AllBooks book) {
        ContentValues cv = new ContentValues();
        cv.put(AllBooks.COL_NAME, book.getName());
        cv.put(AllBooks.COL_IMG, book.getImg());
        return db.insertWithOnConflict(AllBooks.TABLE_BOOKS, null, cv, SQLiteDatabase.CONFLICT_REPLACE);
    }

    //  Update
    public boolean updateBook(int id, String name, int img) {
        ContentValues cv = new ContentValues();
        cv.put(AllBooks.COL_NAME, name);
        cv.put(AllBooks.COL_IMG, img);
        int rows = db.update(AllBooks.TABLE_BOOKS, cv, AllBooks.COL_ID + "=?",
                new String[]{String.valueOf(id)});
        return rows > 0;
    }

    //  Delete
    public boolean deleteBook(int id) {
        int rows = db.delete(AllBooks.TABLE_BOOKS, AllBooks.COL_ID + "=?",
                new String[]{String.valueOf(id)});
        return rows > 0;
    }



    //  Get all
    public ArrayList<AllBooks> getAllBooks() {
        ArrayList<AllBooks> list = new ArrayList<>();

        String query = "SELECT * FROM " + AllBooks.TABLE_BOOKS + " ORDER BY " + AllBooks.COL_ID + " DESC";
        Cursor c = db.rawQuery(query, null);

        if (c.moveToFirst()) {
            do {
                AllBooks b = new AllBooks();
                b.setId(c.getInt(c.getColumnIndexOrThrow(AllBooks.COL_ID)));
                b.setName(c.getString(c.getColumnIndexOrThrow(AllBooks.COL_NAME)));
                b.setImg(c.getInt(c.getColumnIndexOrThrow(AllBooks.COL_IMG)));
                list.add(b);
            } while (c.moveToNext());
        }
        c.close();
        return list;
    }



    //  Close DB
    public void closeDB() {
        if (db != null && db.isOpen()) db.close();
    }
}
