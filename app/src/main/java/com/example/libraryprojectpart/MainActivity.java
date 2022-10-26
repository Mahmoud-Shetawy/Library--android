package com.example.libraryprojectpart;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener  {



    public static final String LIBRARY_NAME="LIBRARY_NAME";
    TextView textView;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.options_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main2 );
        textView = ( TextView ) findViewById( R.id.textView9 );
    }



    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.popup_menu, popup.getMenu());
        popup.show();
    }



    public void logoutButton(View view) {
        saveSharedPreferencesEmpty();
        startActivity(new Intent(getApplicationContext(), com.example.libraryprojectpart.login.class));
        finish();
    }
    public void setDefaultLibraryNameSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences( com.example.libraryprojectpart.login.SHARED_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(MainActivity.LIBRARY_NAME,"Books");
        editor.commit();
        textView.setText(sharedPreferences.getString(MainActivity.LIBRARY_NAME,""));
    }
    public void renameLibraryNameSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences( com.example.libraryprojectpart.login.SHARED_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(MainActivity.LIBRARY_NAME,"nader");
        editor.commit();
        textView.setText(sharedPreferences.getString(MainActivity.LIBRARY_NAME,""));
    }
    public void saveSharedPreferencesEmpty() {
        SharedPreferences sharedPreferences = getSharedPreferences( com.example.libraryprojectpart.login.SHARED_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString( com.example.libraryprojectpart.login.USERNAME, "");
        editor.putString( com.example.libraryprojectpart.login.PASSWORD, "");
        editor.commit();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.theme_light:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                return false;
            case R.id.theme_dark:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                return false;
            case R.id.setting:
                Toast.makeText(this, "setting", Toast.LENGTH_SHORT).show();
                return false;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.theme_light:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                return false;
            case R.id.theme_dark:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                return false;
            default:
                Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    public void moveListView(View view){
        Intent intent = new Intent(this,MainActivityShetawy.class  );
        startActivity(intent);
    }

}