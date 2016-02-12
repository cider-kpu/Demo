package com.example.user.ch_mapver;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by user on 2016-01-20.
 */
public class Login extends AppCompatActivity {
    EditText id, password;
    Button login_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(0xFF116911));

        String usrid, usrpw;
        id=(EditText)findViewById(R.id.id);
        password=(EditText)findViewById(R.id.pw);
        login_button=(Button)findViewById(R.id.login_button);

        usrid=id.getText().toString();
        usrpw=password.getText().toString();

        try {
            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            if (menuKeyField != null) {
                menuKeyField.setAccessible(true);
                menuKeyField.setBoolean(config, false);
            }

        } catch (Exception ex) {

        }
    }

    private void SendByHttp(String msg1, String msg2){
        String address="";

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml

        //noinspection SimplifiableIfStatement
        switch(item.getItemId()){
            case R.id.info:
                Intent intent1=new Intent(this, Search.class);
                startActivity(intent1);
                return true;

            case R.id.login:
                Intent intent2=new Intent(this, Login.class);
                startActivity(intent2);
                return true;

            case R.id.recode:
                Intent intent3=new Intent(this, Recode.class);
                startActivity(intent3);
                return true;

            case R.id.group:
                Intent intent4=new Intent(this, Group.class);
                startActivity(intent4);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

