package com.example.libraryprojectpart.Views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.libraryprojectpart.MainActivityShetawy;
import com.example.libraryprojectpart.R;
import com.example.libraryprojectpart.model.Book;

import controller.DatabaseHelper;

public class EditData extends AppCompatActivity {


    private EditText title_edit , Author_edit , pages_edit ;
    private Button editButton;
    private DatabaseHelper databaseHelper;
    Book personInfo;
    int position;
    String str_position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_edit_data);



        Bundle bundle = getIntent().getExtras();
        str_position = bundle.getString("position");
        position = Integer.parseInt(str_position);
        int my = position;
        Log.d( "id --->" , String.valueOf( my ) );
//       personInfo.set_id( position );
        databaseHelper = new DatabaseHelper( this );
        personInfo = databaseHelper.getBookByID(position);


        title_edit = findViewById(R.id.title_edit);
        Author_edit = findViewById(R.id.Author_edit);
        pages_edit = findViewById(R.id.pages_edite);
       // descriptionText   = findViewById(R.id.description1);
        editButton = findViewById(R.id.buttonedit);


        if(personInfo != null){
            title_edit.setText(personInfo.getTitle());
            Author_edit.setText(personInfo.getAuthor());
            pages_edit.setText(String.valueOf( personInfo.getPages()));
           // descriptionText.setText(personInfo.getDescription());
        }



        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                personInfo.setTitle(  title_edit.getText().toString() );
                personInfo.setAuthor(  Author_edit.getText().toString() );
               // personInfo.setDescription(  descriptionText.getText().toString() );
                personInfo.setPages( Integer.parseInt( pages_edit.getText().toString()  ) ) ;

                databaseHelper.updateBookByID(personInfo);
                MainActivityShetawy.notifyAdapter();
                Intent intent = new Intent(EditData.this, MainActivityShetawy.class);
                startActivity(intent);
                finish();

            }
        });


    }
}
