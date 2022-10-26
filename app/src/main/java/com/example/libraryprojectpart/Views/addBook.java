package com.example.libraryprojectpart.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


import com.example.libraryprojectpart.MainActivityShetawy;
import com.example.libraryprojectpart.R;
import com.example.libraryprojectpart.model.Book;

import controller.DatabaseHelper;

public class addBook extends AppCompatActivity {
    private EditText titleText , authorText , pagesText ;
    private Button saveButton;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_add_book);

        databaseHelper = new DatabaseHelper(this);
        titleText = findViewById(R.id.booktitle_Add);
        authorText = findViewById(R.id.Author_Add);
        pagesText = findViewById(R.id.pages_Add);
        saveButton = findViewById(R.id.buttonsave);
        //descriptionText   = findViewById(R.id.description);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title_book_add = titleText.getText().toString();
                String author_book_add = authorText.getText().toString();
               // String description = descriptionText.getText().toString();
                int pagesnum_add = Integer.parseInt(  pagesText.getText().toString());

                long id = databaseHelper.addBook(new Book(title_book_add,author_book_add,pagesnum_add));
                Intent intent = new Intent(addBook.this, MainActivityShetawy.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
