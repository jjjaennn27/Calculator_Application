package com.example.calculatorapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    boolean isFirstInput = true; //입력 값이 처음 입력되는가를 체크 (기본변수)
    int resultNumber = 0; //계산된 결과 값을 저장하는 변수
    char operator = '+'; //입력된 연산자를 저장하는 변수

    final String CLEAR_INPUT_TEXT = "0"; //상수

    TextView resultText; //레퍼런스 변수(주소)


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultText = findViewById(R.id.result_text);
    }

    public void ButtonClick(View view) {

        Button getButton = findViewById(view.getId()); //버튼 생성

        Log.e("buttonClick", "buttonClick 시작 : " + getButton.getText().toString() + " 버튼이 클릭되었습니다."); //Logcat에 어떤 버튼이 눌렸는지 나옴

        switch(getButton.getId()) {
            case R.id.all_clear_button: //AC 버튼
                isFirstInput = true;
                resultNumber = 0;
                operator = '+';
                resultText.setTextColor(0xFFA8A8A8);
                resultText.setText(CLEAR_INPUT_TEXT); //string으로 resultNumber를
                break;
            case R.id.clear_entry_button: //CE 버튼
                isFirstInput = true;
                resultText.setTextColor(0xFFA8A8A8);
                resultText.setText(CLEAR_INPUT_TEXT);
                break;
            case R.id.back_space_button: //BS 버튼
                if(resultText.getText().toString().length() > 1) //문자 길이가 1보다 크면
                {
                    String getResultText = resultText.getText().toString();
                    String subString = getResultText.substring(0,getResultText.length()-1);
                    resultText.setText(subString);
                } else {
                    resultText.setTextColor(0xFFA8A8A8);
                    resultText.setText(CLEAR_INPUT_TEXT);
                    isFirstInput=true;
                }
                break;
            case R.id.decimal_button: //정수 계산기라서 아직 필요 없음
                Log.e("buttonClick", getButton.getText().toString() + "버튼이 클릭되었습니다.");
                break;

            // 부호 버튼
            case R.id.Addition_button:
            case R.id.Subtraction_button:
            case R.id.Division_button:
            case R.id.Multiply_button:
                int lastNum = Integer.parseInt(resultText.getText().toString());

                if(operator == '+'){
                    resultNumber = resultNumber + lastNum;
                }else if(operator == '-') {
                    resultNumber = resultNumber - lastNum;
                }else if(operator == '/') {
                    resultNumber = resultNumber / lastNum;
                }else if(operator == '*') {
                    resultNumber = resultNumber * lastNum;
                }
                operator = getButton.getText().toString().charAt(0); // charAt(0) 0번째 문자를 char로 바꿔줌
                resultText.setText(String.valueOf(resultNumber)); //값 저장
                isFirstInput = true;
                break;

            // = 버튼
            case R.id.Result_button:
                if(operator == '+'){
                    resultNumber = resultNumber + Integer.parseInt(resultText.getText().toString());
                }else if(operator == '-') {
                    resultNumber = resultNumber - Integer.parseInt(resultText.getText().toString());
                }else if(operator == '/') {
                    resultNumber = resultNumber / Integer.parseInt(resultText.getText().toString());
                }else if(operator == '*') {
                    resultNumber = resultNumber * Integer.parseInt(resultText.getText().toString());
                }
                operator = getButton.getText().toString().charAt(0); // charAt(0) 0번째 문자를 char로 바꿔줌
                resultText.setText(String.valueOf(resultNumber)); //값 저장
                isFirstInput = true;
                Log.d("buttonClick", "resultNumber = " + resultNumber); // resultNumber 값 표시
                break;

            // 0~9 버튼
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

            //정의 되지 않은 버튼을 눌렀을 때
            default:
                //Toast.makeText(getApplicationContext(), getButton.getText().toString() + " 버튼이 클릭되었습니다.", Toast.LENGTH_LONG).show(); //토스트메시지
                Log.e("buttonClick", "default : " + getButton.getText().toString() + " 버튼이 클릭되었습니다."); //Logcat (사용자는 볼 수 없음)
                break;
        }
    }
}