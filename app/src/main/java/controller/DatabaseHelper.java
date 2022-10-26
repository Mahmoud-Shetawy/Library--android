package controller;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import Utils.Utils;
import com.example.libraryprojectpart.model.Book;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private Context context;

    public DatabaseHelper( @Nullable Context context ) {
        super( context , Utils.DATABASE_NAME , null , Utils.DATABASE_VERSION );
        this.context = context;
    }

    @Override
    public void onCreate( SQLiteDatabase sqLiteDatabase ) {

        String CREATE_LESTBOOK = "CREATE TABLE "+Utils.TABLE_NAME + " (" +
                Utils.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Utils.KEY_title + " TEXT, "
                + Utils.KEY_author + " TEXT, "
                + Utils.KEY_pages + " TEXT);";
        sqLiteDatabase.execSQL( CREATE_LESTBOOK );
    }

    @Override
    public void onUpgrade( SQLiteDatabase sqLiteDatabase , int i , int i1 ) {
        sqLiteDatabase.execSQL( "DROP TABLE IF EXISTS "+ Utils.TABLE_NAME );
        onCreate( sqLiteDatabase );
    }


    public long addBook( Book book ){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues(  );

        contentValues.put( Utils.KEY_title,book.getTitle() );
        contentValues.put( Utils.KEY_author,book.getAuthor() );
        contentValues.put( Utils.KEY_pages,String.valueOf( book.getPages()) );


        long id = database.insert(Utils.TABLE_NAME , null , contentValues);
        // database.close();
        return id;
    }


    public Book getBookByID( int id){
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query( Utils.TABLE_NAME,
                new String[]{Utils.KEY_ID,Utils.KEY_title , Utils.KEY_author , Utils.KEY_pages}
                , Utils.KEY_ID+ "=?"
                ,new String[]{String.valueOf( id )}
                ,null,null,null,null);

        if ( cursor != null )
            cursor.moveToFirst();
        Book book
                =new Book(
                Integer.parseInt( cursor.getString(  0) )
                ,cursor.getString(  1)
                ,cursor.getString(  2)
                , Integer.parseInt( cursor.getString(  3) ));

        return book;

    }



    @SuppressLint( "Range" )
    public List <Book> getAllBook(){

        List<Book>  allbook = new ArrayList <>(  );

        String getAll = "SELECT * FROM "+ Utils.TABLE_NAME
                +" ORDER BY " + Utils.KEY_ID ;

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery( getAll ,null );


        if ( cursor.moveToFirst() )

            do {
                Book book = new Book( );

                book.set_id(cursor.getInt(cursor.getColumnIndex(Utils.KEY_ID)));
                book.setTitle( cursor.getString(cursor.getColumnIndex(Utils.KEY_title)));
                book.setAuthor( cursor.getString(cursor.getColumnIndex(Utils.KEY_author)));
                book.setPages(cursor.getInt(cursor.getColumnIndex(Utils.KEY_pages)));

              /*  book.set_id( Integer.parseInt( cursor.getString(  0) ) );
                book.setTitle(  cursor.getString(  1));
                book.setAuthor( cursor.getString(  2)  );
                book.setPages( Integer.parseInt( cursor.getString(  3) ));
*/
                allbook.add( book);
            }while ( cursor.moveToNext() );
        database.close();
        return allbook;
    }



    public int updateBookByID( Book book){
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues(  );
        // contentValues.put( Utils.KEY_ID,book.get_id() );
        contentValues.put( Utils.KEY_title,book.getTitle() );
        contentValues.put( Utils.KEY_author,book.getAuthor() );
        contentValues.put( Utils.KEY_pages,String.valueOf( book.getPages()) );

        int result = database.update( Utils.TABLE_NAME,contentValues,
                Utils.KEY_ID + " =?",
                new String[]{String.valueOf(book.get_id() )});

        database.close();

        return result;
    }



    public void deleteBookByID( Book book){

        Log.d( "id --->" , String.valueOf( Utils.KEY_ID ) );

        SQLiteDatabase database = this.getWritableDatabase();
         database.delete(Utils.TABLE_NAME,
                Utils.KEY_ID+"=?",
                new String[]{String.valueOf(book.get_id())});
        database.close();
        
    }
    public  void deleteData(Book book){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Utils.TABLE_NAME ,  Utils.KEY_ID + " =?",
                new String[]{String.valueOf(book.get_id())});
        db.close();
    }


    public int getNumBook( ){
        String query = "SELECT * FROM "+Utils.TABLE_NAME;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery( query ,null );
        int count = cursor.getCount();
        cursor.close();
        // cursor.moveToFirst();
        return count ;
    }



    public void deleteAllData(){
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("DELETE FROM " + Utils.TABLE_NAME);
    }
}
