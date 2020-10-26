package com.example.additiontrivia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView addTextView;
    TextView resultTextView;
    TextView scoreTextView;
    TextView timerTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgain;
    int score = 0;
    int totalQuestion = 0;
    int locationOfCorrectAnswer;
    ConstraintLayout gameLayout;

    public void start(View view){
        Button startButton = findViewById(R.id.startButton);
        startButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.playAgainButton));

    }
    public void generateNumbers(){
        Random rand  = new Random();
        ArrayList<Integer> answers = new ArrayList<Integer>();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);
        addTextView.setText(Integer.toString(a) +" + "+ Integer.toString(b));
        locationOfCorrectAnswer = rand.nextInt(4);
        int wrongAnswer;
        for(int i =0; i < 4; i++){
            if( i == locationOfCorrectAnswer){
                answers.add(a+b);
            } else {
                wrongAnswer = rand.nextInt(41);
                while(wrongAnswer == a+b){
                    wrongAnswer = rand.nextInt(41);
                }
                answers.add(wrongAnswer);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }
    public void selection(View view){
        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){
            resultTextView.setText("Correct!");
            score++;
        } else {
            resultTextView.setText("Incorrect!");
        }
        totalQuestion++;
        scoreTextView.setText(score + "/" + totalQuestion);
        generateNumbers();
    }
    public void playAgain(View view){
        score = 0;
        totalQuestion = 0;
        resultTextView.setText("");
        timerTextView.setText("00:00:30");
        scoreTextView.setText("0/0");
        playAgain.setVisibility(View.INVISIBLE);

        new CountDownTimer(30100, 1000){
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText("00:00:" + millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                playAgain.setVisibility(View.VISIBLE);
                timerTextView.setText("00:00:00");
                resultTextView.setText("Your Score: " + score + "/" + totalQuestion);
            }
        }.start();
        generateNumbers();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addTextView = findViewById(R.id.addTextView);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        playAgain = findViewById(R.id.playAgainButton);
        resultTextView = findViewById(R.id.resultTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        timerTextView = findViewById(R.id.timerTextView);
        gameLayout = findViewById(R.id.gameLayout);

    }
}