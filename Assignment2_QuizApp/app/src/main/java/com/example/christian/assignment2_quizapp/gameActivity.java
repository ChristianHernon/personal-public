package com.example.christian.assignment2_quizapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collections;

public class gameActivity extends Activity {

    private Button[] btnAnswers = new Button[4];
    private TextView tvQuestion;
    private TextView tvStats;
    private String[] question;
    private Game myGame;
    private int questionsAnswered = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Intent intent = getIntent();
        String name = intent.getStringExtra(MainActivity.PLAYER_NAME);
        tvQuestion = (TextView) findViewById(R.id.tvQuestion);
        tvQuestion.setMovementMethod(new ScrollingMovementMethod());
        tvStats = (TextView) findViewById(R.id.tvStats);
        Button btnOne = (Button) findViewById(R.id.btnOne);
        Button btnTwo = (Button) findViewById(R.id.btnTwo);
        Button btnThree = (Button) findViewById(R.id.btnThree);
        Button btnFour = (Button) findViewById(R.id.btnFour);
        btnAnswers[0] = btnOne;
        btnAnswers[1] = btnTwo;
        btnAnswers[2] = btnThree;
        btnAnswers[3] = btnFour;
        myGame = new Game(name);
        updateStats();

        for (final Button aButton : btnAnswers) {
            aButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (checkAnswer(aButton.getText().toString(), question[1])) {
                        customToast(true);
                        myGame.removeDefinition(question[0]);
                        myGame.setScore(myGame.getScore() + 1);
                        updateStats();
                        prepareNextQuestion();
                    } else {
                        customToast(false);
                        myGame.removeDefinition(question[0]);
                        updateStats();
                        prepareNextQuestion();
                    }
                }
            });
        }// end foreach Button listener assignment

        // declare file I/O
        InputStream inputStream = this.getResources().openRawResource(R.raw.quiz);
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String str = "";

        try {
            while ((str = br.readLine()) != null) {
                // fill arrayLists
                myGame.addDefinition(str.split("::")[0]);
                myGame.addTerm(str.split("::")[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("error", "Unable to read file");
        }//end catch

        myGame.fillHashMap();
        prepareNextQuestion();
    }// end onCreate

    private boolean checkAnswer(String selection, String answer) {
        return selection.equals(answer);
    }// end checkAnswer

    private void prepareNextQuestion() {
        question = myGame.nextQuestion();
        if (!question[0].equals("::GAME_COMPLETE::")) {
            Collections.shuffle(Arrays.asList(btnAnswers));
            tvQuestion.setText(question[0]);
            for (int i = 0; i < btnAnswers.length; i++) {
                btnAnswers[i].setText(question[i + 1]);
            }
        } else {
            gameComplete();
        }
    }// end prepareNextQuestion

    private void gameComplete() {
        String name = myGame.getName();
        String score = Integer.toString(myGame.getScore());
        float temp = ((float) myGame.getScore() / (float) questionsAnswered);
        //String accuracy = String.valueOf(temp);
        String message = name + ", you answered " + MessageFormat.format("{0,number,#.##%}", temp) + " of the questions correctly" +
                " with a score of " + score + "/" + questionsAnswered;
        tvQuestion.setText(message);
        for (Button aButton : btnAnswers) {
            aButton.setVisibility(View.INVISIBLE);
        }
        btnAnswers[3].setText(getString(R.string.restart));
        btnAnswers[3].setVisibility(View.VISIBLE);
        btnAnswers[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = getBaseContext().getPackageManager()
                        .getLaunchIntentForPackage(getBaseContext().getPackageName());
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });
    }// end gameComplete

    private void updateStats() {
        questionsAnswered += 1;
        String stats = myGame.getName();
        stats += "        Score: " + Integer.toString(myGame.getScore());
        stats += "/" + Integer.toString(questionsAnswered);
        tvStats.setText(stats);
    }// end updateStats

    private void customToast(boolean correct) {
        if (correct) {
            Toast toast = Toast.makeText(this, "CORRECT", Toast.LENGTH_SHORT);
            TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
            v.setTextColor(Color.GREEN);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        } else {
            Toast toast = Toast.makeText(this, "WRONG", Toast.LENGTH_SHORT);
            TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
            v.setTextColor(Color.RED);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }// end customToast
}
