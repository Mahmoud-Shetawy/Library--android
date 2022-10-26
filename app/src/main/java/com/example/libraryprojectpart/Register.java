package com.example.libraryprojectpart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity{

    private TextView errorTxtView;
    private EditText etUserName,etPassword,etConfirmPassword,etId;
    Account account;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        errorTxtView=(TextView)findViewById(R.id.errorTxtView) ;
        etUserName=(EditText)findViewById(R.id.etUserName);
        etId=(EditText)findViewById(R.id.etId);
        etPassword=(EditText)findViewById(R.id.etPassword);
        etConfirmPassword=(EditText)findViewById(R.id.etConfirmPassword) ;
    }

    public void logInTxtView(View view) {

        startActivity(new Intent(getApplicationContext(), com.example.libraryprojectpart.login.class));
        finish();
    }
    public void signUpButton(View v){


        if(etUserName.getText().toString().isEmpty()){
            errorTxtView.setText(R.string.username_required);
        }else if(etId.getText().toString().isEmpty()||etId.getText().toString().length()!=14){
            if(etId.getText().toString().isEmpty())errorTxtView.setText(R.string.ID_is_required);
            else errorTxtView.setText(R.string.ID_is_not_correct);
        }else if(etPassword.getText().toString().isEmpty()){
            errorTxtView.setText(R.string.Password_required);
        }else if(etConfirmPassword.getText().toString().isEmpty()){
            errorTxtView.setText(R.string.confirm_password_is_required);
        }else  if(!etPassword.getText().toString().equals(etConfirmPassword.getText().toString())
                ||etConfirmPassword.getText().toString().length()<8){
            if(etConfirmPassword.getText().toString().length()<8)
             errorTxtView.setText(R.string.password_should_more_than_or_equals_8_character);
            else errorTxtView.setText(R.string.password_should_equals_confirm_password);
        }else{
            account=new Account(etUserName.getText().toString(),etPassword.getText().toString(),etId.getText().toString());

            saveInDatabase(account);
            }

    }
    public void saveInDatabase(Account account){
        AccountDatabase dp=new AccountDatabase(this);
        if(dp.selectUniqueID(account)) errorTxtView.setText(R.string.ID_is_Exists_before);
        else if(!dp.insertAccount(account))errorTxtView.setText(R.string.USERNAME_is_Exists_before);
        else{
            etUserName.setText("");
            etPassword.setText("");
            etConfirmPassword.setText("");
            etId.setText("");
            errorTxtView.setText("");
            Toast.makeText(this, R.string.done_you_can_login_now, Toast.LENGTH_LONG).show();
            startActivity(new Intent(getApplicationContext(), com.example.libraryprojectpart.login.class));
        }

    }

}
