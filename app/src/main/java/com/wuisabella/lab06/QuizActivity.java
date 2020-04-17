package com.wuisabella.lab06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizActivity extends AppCompatActivity {
    private TextView textViewQuestion;
    private TextView textViewScore;
    private TextView textViewQuestionCount;
    private RadioGroup radioGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private Button confirm;

    private ColorStateList defaultTextColor;

    private List<Question> questionList;
    private int questionCounter;
    private int questionCountTotal;
    private Question currentQuestion;

    private int score;
    private boolean answered;

    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Gson gson = new Gson();

        textViewQuestion = findViewById(R.id.text_question);
        textViewScore = findViewById(R.id.text_score);
        textViewQuestionCount = findViewById(R.id.text_question_count);
        radioGroup = findViewById(R.id.radiogroup);
        rb1 = findViewById(R.id.radiobutton1);
        rb2 = findViewById(R.id.radiobutton2);
        rb3 = findViewById(R.id.radiobutton3);
        confirm = findViewById(R.id.button_confirm);

        defaultTextColor = rb1.getTextColors();

        Type questionType = new TypeToken<ArrayList<Question>>(){}.getType();
        questionList = gson.fromJson(read_json(), questionType);
        Collections.shuffle(questionList);
        questionCountTotal = questionList.size();

        next_question();

        confirm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(!answered) {
                    if(rb1.isChecked() || rb2.isChecked() || rb3.isChecked()) {
                        check_answer();
                    } else {
                        Toast.makeText(QuizActivity.this, "Please select an answer.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    next_question();
                }
            }
        });

    }

    public String read_json() {
        String json = null;
        try {
            InputStream is = QuizActivity.this.getAssets().open("questions.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    private void next_question(){
        rb1.setTextColor(defaultTextColor);
        rb2.setTextColor(defaultTextColor);
        rb3.setTextColor(defaultTextColor);
        radioGroup.clearCheck();

        if(questionCounter < questionCountTotal){
            currentQuestion = questionList.get(questionCounter);

            textViewQuestion.setText(currentQuestion.getQuestion());
            rb1.setText(currentQuestion.getOption1());
            rb2.setText(currentQuestion.getOption2());
            rb3.setText(currentQuestion.getOption3());

            questionCounter++;
            textViewQuestionCount.setText("Question: " + questionCounter + "/" + questionCountTotal);
            answered = false;
            confirm.setText("Confirm");
        } else {
            finish_quiz();
        }
    }

    private void check_answer() {
        answered = true;

        RadioButton selected = findViewById(radioGroup.getCheckedRadioButtonId());
        int num = radioGroup.indexOfChild(selected) + 1;

        if (num == currentQuestion.getAnswer()) {
            score++;

        }
    }

    private void finish_quiz() {
        finish();
    }


}
