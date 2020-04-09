package com.wuisabella.lab06;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {
    private TextView question;
    private TextView score;
    private TextView questionCount;
    private RadioGroup radioGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private Button confirm;

    private List<Question> questionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Gson gson = new Gson();

        question = findViewById(R.id.text_question);
        score = findViewById(R.id.text_score);
        questionCount = findViewById(R.id.text_question_count);
        radioGroup = findViewById(R.id.radiogroup);
        rb1 = findViewById(R.id.radiobutton1);
        rb2 = findViewById(R.id.radiobutton2);
        rb3 = findViewById(R.id.radiobutton3);
        confirm = findViewById(R.id.button_confirm);

        //questionList = new ArrayList<>();
        //questionList = gson.fromJson(new FileReader("questions.json"), new TypeToken<ArrayList<Question>>(){}.getType());

    }
}
