package com.example.user.codequizapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.TimeUnit;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by USER on 9/14/2017.
 */

public class Html extends AppCompatActivity implements View.OnClickListener{

    //this class will show the html and display it int the


    List<Question> quesList;

    int score = 0;

    int qid = 0;

    Question currentQ;


    TextView txtQuestion, times, scored;


    FancyButton button1, button2, button3;
    CounterClass timer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);



            HtmlHelper db = new HtmlHelper(this);

            quesList = db.getAllQuestions();

            currentQ = quesList.get(qid);

            txtQuestion = (TextView) findViewById(R.id.txtQuestion);

            button1 = (FancyButton) findViewById(R.id.button1);

            button2 = (FancyButton) findViewById(R.id.button2);

            button3 = (FancyButton) findViewById(R.id.button3);

            scored = (TextView) findViewById(R.id.score);
            times = (TextView) findViewById(R.id.timers);
            scored.setVisibility(View.INVISIBLE);
            setQuestionView();
            times.setText("00:02:00");

            counter();

            button1.setOnClickListener(this);
            button2.setOnClickListener(this);
            button3.setOnClickListener(this);
        }

        public void counter() {
            timer = new CounterClass(600000, 1000);
            timer.start();
        }

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub

            switch (v.getId()) {

                case R.id.button1:

                    getAnswer(button1.getText().toString());

                    break;

                case R.id.button2:

                    getAnswer(button2.getText().toString());
                    break;

                case R.id.button3:

                    getAnswer(button3.getText().toString());

                    break;
            }

        }

        protected void getAnswer(String answer) {
            // TODO Auto-generated method stub

            if (currentQ.getANSWER().equals(answer)) {

                score++;
                scored.setVisibility(View.VISIBLE);
                scored.setText("Score is " + score);
                scored.setTextColor(Color.BLUE);

            } else if (!currentQ.getANSWER().equals(answer)) {


               AlertDialog.Builder dialog = new AlertDialog.Builder(Html.this,R.style.MyDialogTheme);
                dialog.setIcon(R.mipmap.ic_wrong_answer);
                dialog.setTitle("Wrong answer");
                dialog.setMessage("Incorrect Answer");
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {



                    }
                });

                AlertDialog diag = dialog.create();
                diag.show();

            }

            if (qid < 20) {

                // if questions are not over then do this

                currentQ = quesList.get(qid);

                setQuestionView();

            }
            if (qid >= 20) {

                scored.setText("your final score is" + " " + score + "/20");

                Thread thread = new Thread() {

                    public void run() {

                        try {

                            sleep(1000);

                        } catch (Exception e) {

                            e.printStackTrace();
                        } finally {

                            Intent intent = new Intent(Html.this, MainList.class);
                            finish();
                        }

                    }

                };

                thread.start();

            }
        }

        private void setQuestionView() {
            // TODO Auto-generated method stub

            txtQuestion.setText(currentQ.getQUESTION());

            button1.setText(currentQ.getOPTA());

            button2.setText(currentQ.getOPTB());

            button3.setText(currentQ.getOPTC());

            qid++;

        }

        public class CounterClass extends CountDownTimer {

            public CounterClass(long millisInFuture, long countDownInterval) {

                super(millisInFuture, countDownInterval);

            }

            @Override
            public void onTick(long millisUntilFinished) {
                // TODO Auto-generated method stub

                long millis = millisUntilFinished;

                String hms = String.format(

                        "%02d:%02d:%02d",

                        TimeUnit.MILLISECONDS.toHours(millis),

                        TimeUnit.MILLISECONDS.toMinutes(millis)

                                - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS

                                .toHours(millis)),

                        TimeUnit.MILLISECONDS.toSeconds(millis)

                                - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS

                                .toMinutes(millis)));

                System.out.println(hms);

                times.setText(hms);

            }

            @Override
            public void onFinish() {
                // TODO Auto-generated method stub

                times.setText("Time is up");
                times.setTextColor(Color.RED);
                finish();

            }

        }


    }

