package com.example.calculatorapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    boolean inFirstInput = true; //입력 값이 처음 입력되는가를 체크 (기본변수)
    int resultNumber = 0; //계산된 결과 값을 저장하는 변수
    char operator = '+'; //입력된 연산자를 저장하는 변수

    TextView resultText; //레퍼런스 변수(주소)


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultText = findViewById(R.id.result_text);
    }

    public void ButtonClick(View view) {
        if(view.getId() == R.id.num_1_button){
            resultText.setText("1");
        } else {

        }
    }
}