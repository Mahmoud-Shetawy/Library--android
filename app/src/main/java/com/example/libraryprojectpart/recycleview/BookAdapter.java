package com.example.libraryprojectpart.recycleview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.libraryprojectpart.MainActivityShetawy;
import com.example.libraryprojectpart.R;
import com.example.libraryprojectpart.Views.EditData;
import com.example.libraryprojectpart.model.Book;

import java.util.List;

import controller.DatabaseHelper;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.bookviewwholder>  {
    private static Context context;
    private DatabaseHelper databaseHelper;
    private List<Book> list;
    int position;



    public BookAdapter(Context context , List<Book> allData) {
        this.context = context;
        this.list = allData;
    }

    public BookAdapter( Context context, List< Book > list, DatabaseHelper databaseHelper) {
        this.context = context;
       this.list = list;
        this.databaseHelper = databaseHelper;
    }

    public static Context getContext() {
        return context;
    }

    public BookAdapter( List < Book > List ) {
        this.list = List;
    }

    @NonNull
    @Override
    public bookviewwholder onCreateViewHolder( @NonNull ViewGroup parent , int viewType ) {
        View view = LayoutInflater.from( parent.getContext() )
                .inflate( R.layout.items_book,parent
                ,false );
        return new bookviewwholder( view );
    }

    @Override
    public void onBindViewHolder( @NonNull final bookviewwholder holder
            , @SuppressLint( "RecyclerView" ) final int position) {
        holder.ratingBar.setRating( ( float ) 4.3 );
        this.position = (position);



        final Book book = list.get(position);
        holder.title.setText( book.getTitle() );
        holder.author.setText( book.getAuthor() );
        holder.pages.setText( String.valueOf( book.getPages()) );




/*
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // Log.d( "id --->" , String.valueOf( book.get_id() ) );
              try {
                  deleteData(position);
              } catch ( Exception e ) {
                  e.printStackTrace();
              }
            }
        });*/



        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent( BookAdapter.getContext() , EditData.class);
                intent.putExtra("position",String.valueOf(book.get_id()));
                context.startActivity(intent);


            }});
    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    public class bookviewwholder extends RecyclerView.ViewHolder{

        ImageView imgBook,imgFav;
        TextView title , description , author , pages , rate ;
        RatingBar ratingBar;
         ImageView delete,edit;

        public bookviewwholder( @NonNull View itemView ) {
            super( itemView );
            imgBook=itemView.findViewById( R.id.item_book_img );
            title=itemView.findViewById( R.id.item_book_tittle );
            author=itemView.findViewById( R.id.item_book_author );
            pages=itemView.findViewById( R.id.item_book_pagesview );
            rate=itemView.findViewById( R.id.item_book_score );
            ratingBar=itemView.findViewById( R.id.ratingBar );
           edit=itemView.findViewById( R.id.item_edit );
            delete=itemView.findViewById( R.id.item_delete );
            delete.setOnClickListener( new View.OnClickListener( ) {
                @Override
                public void onClick( View view ) {
                  try {
                      deleteData(position) ;
                  } catch ( Exception e ) {
                      e.printStackTrace( );
                  }
                   /* list.remove(position);
                    MainActivityShetawy.notifyAdapter();
                */}
            } );

        }
    }


   /* private void deleteData( int id ) {
        Book book = new Book();
        databaseHelper.deleteBookByID( id );
        Log.d( "id --->" , String.valueOf( list.get( id ) ) );
        //list.remove( id );
        MainActivityShetawy.notifyAdapter();
    }*/

    private  void deleteData(int position){
        databaseHelper.deleteBookByID(list.get(position));
        list.remove(position);
        MainActivityShetawy.notifyAdapter();
    }
}
