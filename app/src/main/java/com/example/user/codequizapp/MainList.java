package com.example.user.codequizapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;

import com.daimajia.androidanimations.library.flippers.FlipInXAnimator;
import com.easyandroidanimations.library.AnimationListener;
import com.easyandroidanimations.library.BounceAnimation;
import com.easyandroidanimations.library.ExplodeAnimation;
import com.easyandroidanimations.library.FlipHorizontalToAnimation;
import com.easyandroidanimations.library.FoldAnimation;

/**
 * Created by USER on 9/14/2017.
 */

public class MainList extends AppCompatActivity implements View.OnClickListener {

   /* GridView mainGridView;
    String codeNames[] = {"Html","Java","Php","Sql","View All Answers"};
    int [] codeImages = {R.drawable.html5,R.drawable.java,R.drawable.php,R.drawable.sql,R.drawable.answers};*/

   CardView html,java,php,sql;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_list);

        html = (CardView)findViewById(R.id.html);
        java = (CardView)findViewById(R.id.java);
        php = (CardView)findViewById(R.id.php);
        sql = (CardView)findViewById(R.id.sql);

        //using easy android animation library


        new BounceAnimation(html).setNumOfBounces(20).setDuration(1000).animate();
        new BounceAnimation(java).setNumOfBounces(30).setDuration(1000).animate();
        new BounceAnimation(php).setNumOfBounces(40).setDuration(1000).animate();
        new BounceAnimation(sql).setNumOfBounces(50).setDuration(1000).animate();


        //android.view.animation.Animation animation = AnimationUtils.loadAnimation(MainList.this,R.anim.swing_up_left);
       // html.setAnimation(animation);
        //java.setAnimation(animation);
       // php.setAnimation(animation);
        //sql.setAnimation(animation);

        html.setOnClickListener(this);
        java.setOnClickListener(this);
        php.setOnClickListener(this);
        sql.setOnClickListener(this);

    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainList.this,R.style.MyDialogTheme);
        alertDialog.setIcon(R.mipmap.ic_exit);
        alertDialog.setTitle("Exit?");
        alertDialog.setMessage("are you sure you want to exit?");
        alertDialog.setPositiveButton("OK",new DialogInterface.OnClickListener(){


            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();
            }
        });

        alertDialog.setNegativeButton("NO",new DialogInterface.OnClickListener(){


            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog dialog = alertDialog.create();
        dialog.show();


    }

   /* @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        switch(position){

            case 0:

                startActivity(new Intent(getApplicationContext(),Html.class));


                break;

            case 1:

                startActivity(new Intent(getApplicationContext(),Java.class));

                break;

            case 2:

                startActivity(new Intent(getApplicationContext(),Php.class));
                break;

            case 3:

                startActivity(new Intent(getApplicationContext(),Sql.class));
                break;
        }

    }*/

    @Override
    public void onClick(View v) {


        switch(v.getId()){

            case R.id.html:

                startActivity(new Intent(MainList.this,Html.class));

                break;

            case R.id.java:

                startActivity(new Intent(MainList.this,Java.class));

                break;

            case R.id.php:

                startActivity(new Intent(MainList.this,Php.class));

                break;

            case R.id.sql:

                startActivity(new Intent(MainList.this,Sql.class));

                break;
        }
    }
}

