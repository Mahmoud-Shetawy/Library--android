package com.example.libraryprojectpart;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.libraryprojectpart.Views.addBook;
import com.example.libraryprojectpart.model.Book;
import com.example.libraryprojectpart.recycleview.BookAdapter;

import java.util.List;

import controller.DatabaseHelper;

public class MainActivityShetawy extends AppCompatActivity {

    Button add;
    AlertDialog dialog;
    private ListView listView;
    private RecyclerView rvBooks;
    private static BookAdapter bookAdapter;
    private List < Book > mdata;
    private Object BookAdapter;

    DatabaseHelper db ;


    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        rvBooks = findViewById( R.id.recView );


        db = new DatabaseHelper( this);
      //  db.addBook( new Book( "MAhmoud" ,"Shetawy",123 ) );
        List<Book>bookList = db.getAllBook();

/*        Book book1 = db.getBookByID( 0 );
                db.deleteBookByID( book1 );*/
        for ( Book b : bookList ){
            String myInfo =" ID: "+b.get_id() +"  Title: "+b.getTitle()
                    +"  author: "+b.getAuthor()+"  page: "+b.getPages();
            Log.d( "Book Data" ,myInfo );
        }

        Log.d( "Number Data" ,String.valueOf( db.getNumBook() ) );



      //  initViews();
     //   initmdataBooks();
      //  setupBookAdapter();



        add = findViewById(R.id.add);
       // layout = findViewById(R.id.item_book_tittle);

        //buildDialog();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* dialog.show();
                TextView txt_name = findViewById( R.id.item_book_tittle );
                TextView txt_author = findViewById( R.id.item_book_author );
*/
                Intent intent = new Intent( MainActivityShetawy.this, addBook.class);
                startActivity(intent);
                finish();
            }


        });

        bookAdapter =new BookAdapter( this,db.getAllBook(),db );
        bookAdapter = new BookAdapter( bookList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager( getApplicationContext() );
        rvBooks.setLayoutManager( layoutManager );
        rvBooks.setItemAnimator( new DefaultItemAnimator() );
        rvBooks.setAdapter( bookAdapter );


    }

 /*   private void setupBookAdapter() {
        bookAdapter = new BookAdapter( mdata );
        rvBooks.setAdapter( bookAdapter );
        rvBooks.setItemAnimator( new DefaultItemAnimator() );
        rvBooks.setAdapter( bookAdapter );

    }*/

    public static void notifyAdapter(){
        bookAdapter.notifyDataSetChanged();;
    }




/*
    private void initmdataBooks() {

        mdata = new ArrayList <>(  );
        mdata.add( new Book( R.drawable.book1 ) );
        mdata.add( new Book( R.drawable.book2 ) );
        mdata.add( new Book( R.drawable.book3 ) );
    }


    private void initViews() {

        rvBooks = findViewById( R.id.recView );
        rvBooks.setLayoutManager( new LinearLayoutManager( this ) );
        rvBooks.setHasFixedSize( true );

    }*/


/*
    public void buildDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.activity_edit_data, null);

        final EditText name = view.findViewById(R.id.title_edit);
        final EditText author = view.findViewById(R.id.authorEdit);

        builder.setView(view);
        builder.setTitle("update information")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       // addCard(name.getText().toString(),author.getText().toString());
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick( DialogInterface dialog, int which) {

                    }
                });

        dialog = builder.create();
    }*/
/*

    private void addCard(String name,String author) {
        final View view = getLayoutInflater().inflate(R.layout.items_book, null);

        TextView nameView = view.findViewById(R.id.item_book_tittle);
        TextView authorView = view.findViewById(R.id.item_book_author);
        //Button delete = view.findViewById(R.id.delete);

        nameView.setText(name);
        authorView.setText(author);

        Book std = new Book( name,author );
       // studentDataSource .addStudent( std );



      //  layout.addView(view);
    }
*/




}

