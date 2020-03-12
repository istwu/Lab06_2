package com.wuisabella.lab06;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    Button submitButton;
    EditText editText;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submitButton = findViewById(R.id.clickButton);
        editText = findViewById(R.id.responseEditText);
        textView = findViewById(R.id.textBox);

        submitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                System.out.println("hi");
                Log.i("testButton", "hello " + editText.getText().toString());
                textView.setText(textView.getText() + " " + editText.getText());
            }
        });

        editText.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

            }
        });
    }
}
