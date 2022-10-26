package com.example.libraryprojectpart;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class login extends AppCompatActivity {
    public static final String SHARED_PREFERENCES = "SHARED_PREFERENCES";
    public static final String USERNAME = "USERNAME";
    public static final String PASSWORD = "PASSWORD";
       private TextView tvErrorMessage;
       private EditText etUserName,etPassword;
       private CheckBox checkBoxSaveLogin;
       Account account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(loadFromSharedPreferences().isEmpty()) {
           setContentView(R.layout.activity_login);
        }else{

            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        tvErrorMessage=(TextView)findViewById(R.id.tvErrorMessage);
        etUserName=(EditText)findViewById(R.id.etUserName);
        etPassword=(EditText)findViewById(R.id.etPassword);
        checkBoxSaveLogin=(CheckBox)findViewById(R.id.checkBoxSaveLogin);
    }


    public void loginButton(View view) {
        if(etUserName.getText().toString().isEmpty()){
            tvErrorMessage.setText(R.string.username_required);
        }else if(etPassword.getText().toString().isEmpty()||etPassword.getText().toString().length()<8){
            if(etPassword.getText().toString().isEmpty())tvErrorMessage.setText(R.string.Password_required);
            else tvErrorMessage.setText(R.string.Password_not_correct);
        }else{
            account=new Account(etUserName.getText().toString(),etPassword.getText().toString());
            checkLogin(account);
        }
    }

    public void checkLogin(Account account){
        AccountDatabase dp=new AccountDatabase(this);
        if(dp.selectUniqueAccount(account)){
            if (checkBoxSaveLogin.isChecked()) {saveInSharedPreferences();}
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            Toast.makeText(this, getString(R.string.welcome) +" "+loadFromSharedPreferences(), Toast.LENGTH_LONG).show();
            setEditTextEmpty(etUserName);
            setEditTextEmpty(etPassword);
            setTextViewEmpty(tvErrorMessage);
        }else {
            setEditTextEmpty(etUserName);
            setEditTextEmpty(etPassword);
            tvErrorMessage.setText(R.string.username_or_password_is_wrong);
        }

    }

    public void saveInSharedPreferences(){
        SharedPreferences sharedPreferences =  getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USERNAME, etUserName.getText().toString());
        editor.putString(PASSWORD, etPassword.getText().toString());
        editor.commit();
    }
    public String loadFromSharedPreferences(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
        return sharedPreferences.getString(USERNAME,"");
    }
    public void signUpTxtView(View view) {

        startActivity(new Intent(getApplicationContext(),Register.class));
        finish();
    }
    public void setEditTextEmpty(EditText editText){
        editText.setText("");
    }
    public void setTextViewEmpty(TextView textView){
        textView.setText("");
    }

}

