package com.example.calculatorapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    boolean isFirstInput = true; //입력 값이 처음 입력되는가를 체크 (기본변수)
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

        Button getButton = findViewById(view.getId()); //버튼 생성


        switch(getButton.getId()) {
            case R.id.all_clear_button:
                isFirstInput = true;
                resultNumber = 0;
                operator = '+';
                resultText.setTextColor(0xFFA8A8A8);
                resultText.setText(String.valueOf(resultNumber)); //string으로 resultNumber를
                break;

            case R.id.num_0_button:
            case R.id.num_1_button:
            case R.id.num_2_button:
            case R.id.num_3_button:
            case R.id.num_4_button:
            case R.id.num_5_button:
            case R.id.num_6_button:
            case R.id.num_7_button:
            case R.id.num_8_button:
            case R.id.num_9_button:
                if (isFirstInput) {
                    resultText.setTextColor(0xFF000000); //16진수로
                    resultText.setText(getButton.getText().toString()); //setText 처음 입력되는 경우
                    isFirstInput = false;
                } else {
                    resultText.append(getButton.getText().toString()); //append 추가 되어 입력 (처음 입력되는 경우 x)
                }
                break;

            default: //정의 되지 않은 버튼을 눌렀을 때
                Toast.makeText(getApplicationContext(),getButton.getText().toString() + " 버튼이 클릭되었습니다.", Toast.LENGTH_LONG).show();
                break;
        }
    }
}