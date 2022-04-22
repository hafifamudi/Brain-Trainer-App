package com.docoding.braintrainerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.RelativeLayout;

import com.docoding.braintrainerapp.databinding.MainGameActivityBinding;

import java.util.ArrayList;
import java.util.Random;

public class MainGameActivityActivity extends AppCompatActivity {
    private MainGameActivityBinding binding;

    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationCorrect;
    int score = 0;
    int quesNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = MainGameActivityBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.playBtn.setVisibility(View.INVISIBLE);
                binding.playGame.setVisibility(RelativeLayout.VISIBLE);

                playAgain(binding.againBtn);
            }
        });

        binding.button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseAnswer(binding.button0);
            }
        });

        binding.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseAnswer(binding.button0);
            }
        });

        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseAnswer(binding.button0);
            }
        });

        binding.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseAnswer(binding.button0);
            }
        });

        binding.againBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playAgain(binding.againBtn);
            }
        });
    }


    public void generateQuest() {
        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        binding.sumView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationCorrect = rand.nextInt(4);
        answers.clear();

        int incorrectAnswer;

        for (int i = 0; i < 4; i++) {
            if (i == locationCorrect) {
                answers.add(a + b);
            } else {
                incorrectAnswer = rand.nextInt(41);
                while (incorrectAnswer == a + b) {
                    incorrectAnswer = rand.nextInt(41);
                }
                answers.add(incorrectAnswer);
            }
        }
        binding.button0.setText(Integer.toString(answers.get(0)));
        binding.button1.setText(Integer.toString(answers.get(1)));
        binding.button2.setText(Integer.toString(answers.get(2)));
        binding.button3.setText(Integer.toString(answers.get(3)));
    }


    public void chooseAnswer(View view) {
        if (view.getTag().toString().equals(Integer.toString(locationCorrect))) {
            score++;
            binding.resultView.setText("Correct !");
        } else {
            binding.resultView.setText("Wrong !");
        }

        quesNum++;
        binding.pointsView.setText(Integer.toString(score) + "/" + Integer.toString(quesNum));
        generateQuest();
    }

    public void playAgain(View view) {
        score = 0;
        quesNum = 0;

        binding.timerView.setText("30s");
        binding.pointsView.setText("0/0");
        binding.resultView.setText("");
        binding.againBtn.setVisibility(View.INVISIBLE);
        binding.button0.setEnabled(true);
        binding.button1.setEnabled(true);
        binding.button2.setEnabled(true);
        binding.button3.setEnabled(true);

        generateQuest();

        new CountDownTimer(30100, 1000) {

            @Override
            public void onTick(long l) {
                binding.timerView.setText(String.valueOf(l / 1000) + "s");
            }

            @Override
            public void onFinish() {
                binding.againBtn.setVisibility(View.VISIBLE);
                binding.button0.setEnabled(false);
                binding.button1.setEnabled(false);
                binding.button2.setEnabled(false);
                binding.button3.setEnabled(false);
                binding.timerView.setText("0s");
                binding.resultView.setText("Your Score : " + Integer.toString(score) + "/" + Integer.toString(quesNum));
            }
        }.start();
    }
}