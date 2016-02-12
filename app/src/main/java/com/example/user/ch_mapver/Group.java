package com.example.user.ch_mapver;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by user on 2016-02-11.
 */
public class Group extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.group);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(0xFF116911));
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
