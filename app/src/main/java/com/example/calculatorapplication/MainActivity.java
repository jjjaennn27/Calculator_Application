package com.example.calculatorapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final String CLEAR_INPUT_TEXT = "0"; //상수 선언

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

    // AC, CE, BS 버튼이 클릭 되었을 때 실행되는 메소드
    public void ButtonClick(View view) {

        switch(view.getId()) {
            case R.id.all_clear_button: //AC 버튼
                resultNumber = 0;
                operator = '+';
                setClearText(CLEAR_INPUT_TEXT);
                break;

            case R.id.clear_entry_button: //CE 버튼
                setClearText(CLEAR_INPUT_TEXT);
                break;

            case R.id.back_space_button: //BS 버튼
                if(resultText.getText().toString().length() > 1) //문자 길이가 1보다 크면
                {
                    String getResultText = resultText.getText().toString();
                    String subString = getResultText.substring(0,getResultText.length()-1);
                    resultText.setText(subString);
                } else {
                    setClearText(CLEAR_INPUT_TEXT);
                }
                break;

            case R.id.decimal_button: //정수 계산기라서 아직 필요 없음
                Log.e("buttonClick", "decimal_button 버튼이 클릭되었습니다.");
                break;

        }
    }

    // 입력된 숫자를 클리어 시켜주는 메소드
    public void setClearText(String clearText){
        isFirstInput = true;
        resultText.setTextColor(0xFFA8A8A8);
        resultText.setText(clearText);
    }

    // 0~9 버튼이 클릭 되었을 때 실행되는 메소드
    public void numButtonClick(View view) {
        Button getButton = findViewById(view.getId());

        if (isFirstInput) {
            resultText.setTextColor(0xFF000000); //16진수로
            resultText.setText(getButton.getText().toString()); //setText 처음 입력되는 경우
            isFirstInput = false;
        } else {
            //0 다음에 숫자가 입력 될 때
            if (resultText.getText().toString().equals("0")) {
                Toast.makeText(getApplicationContext(), "0으로 시작하는 정수는 입력될 수 없습니다.", Toast.LENGTH_SHORT).show();
                setClearText(CLEAR_INPUT_TEXT);
            } else {
                resultText.append(getButton.getText().toString()); //append 추가 되어 입력 (처음 입력되는 경우 x)
            }
        }
    }

    //연산자가 클릭 되었을 때 실행되는 메소드
    public void operatorClick(View view){
        Button getButton = findViewById(view.getId()); //버튼 생성

        if(view.getId() == R.id.Result_button) {
            // =을 두 번 이상 누를 때
            if(isFirstInput){
                resultNumber = '0';
                operator = '+';
                setClearText("0");
                // TODO: 2021-04-02 다음에 실수형 계산기 만들때 윈도우 계산기처럼 =을 두 번 이상 누를 때 실행 방법과 같이 구현 할 것!
            }else {
                resultNumber = intCal(resultNumber, Integer.parseInt(resultText.getText().toString()), operator);
                resultText.setText(String.valueOf(resultNumber)); //값 저장
                isFirstInput = true;
            }

        } else{
            if(isFirstInput){
                operator = getButton.getText().toString().charAt(0);
            } else {
                int lastNum = Integer.parseInt(resultText.getText().toString());
                resultNumber = intCal(resultNumber, lastNum, operator);

                operator = getButton.getText().toString().charAt(0); // charAt(0) 0번째 문자를 char로 바꿔줌
                resultText.setText(String.valueOf(resultNumber)); //값 저장
                isFirstInput = true;
            }
        }
    }

    //사칙연산을 해서 값을 반환해주는 메소드
    public int intCal(int result, int lastNum, char operator) {
        if(operator == '+'){
            result = resultNumber + lastNum;
        }else if(operator == '-') {
            result = resultNumber - lastNum;
        }else if(operator == '/') {
            result = resultNumber / lastNum;
        }else if(operator == '*') {
            result = resultNumber * lastNum;
        }
        return result; //값 반환
    }
}
