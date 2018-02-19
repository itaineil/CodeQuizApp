package com.example.user.codequizapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spash_screen);


        Thread thread = new Thread(){

            public void run(){

                try{

                    Thread.sleep(3000);

                }catch(Exception e){

                    e.printStackTrace();

                }finally{

                    Intent intent = new Intent(SplashScreen.this,MainList.class);
                    startActivity(intent);
                    finish();
                }


            }



        };
        thread.start();

    }

    }

