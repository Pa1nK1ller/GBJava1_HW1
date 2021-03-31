package com.example.myfirstapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {
    private Button zero;
    private Button one;
    private Button two;
    private Button three;
    private Button four;
    private Button five;
    private Button six;
    private Button seven;
    private Button eight;
    private Button nine;
    private Button dot;
    private Button delete;
    private Button clear;
    private Button addition;
    private Button subtraction;
    private Button multiplication;
    private Button division;
    private Button equals;
    private Button percent;
    private TextView textCalc;
    private TextView operand;
    private TextView result;
    String deleteText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        setContentView(R.layout.task2);
        setContentView(R.layout.activity_calc);
        initView();
        initButtonOnClick();
    }

    private void initView() {
        zero = findViewById(R.id.zero);
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        six = findViewById(R.id.six);
        seven = findViewById(R.id.seven);
        eight = findViewById(R.id.eight);
        nine = findViewById(R.id.nine);
        dot = findViewById(R.id.dot);
        delete = findViewById(R.id.delete);
        clear = findViewById(R.id.clear);
        addition = findViewById(R.id.addition);
        textCalc = findViewById(R.id.textCalc);
        operand = findViewById(R.id.operand);
        result = findViewById(R.id.result);
        subtraction = findViewById(R.id.subtraction);
        multiplication = findViewById(R.id.multiplication);
        division = findViewById(R.id.division);
        equals = findViewById(R.id.equality);
        percent = findViewById(R.id.percent);
    }

    private void initButtonOnClick() {
        zero.setOnClickListener(v -> textCalc.append(zero.getText()));
        one.setOnClickListener(v -> textCalc.append(one.getText()));
        two.setOnClickListener(v -> textCalc.append(two.getText()));
        three.setOnClickListener(v -> textCalc.append(three.getText()));
        four.setOnClickListener(v -> textCalc.append(four.getText()));
        five.setOnClickListener(v -> textCalc.append(five.getText()));
        six.setOnClickListener(v -> textCalc.append(six.getText()));
        seven.setOnClickListener(v -> textCalc.append(seven.getText()));
        eight.setOnClickListener(v -> textCalc.append(eight.getText()));
        nine.setOnClickListener(v -> textCalc.append(nine.getText()));
        dot.setOnClickListener(v -> textCalc.append(dot.getText()));
        delete.setOnClickListener(v -> {
            System.out.println(textCalc.getText());
            if (textCalc.getText().length() > 0) {
                deleteText = "" + textCalc.getText();
                textCalc.setText(deleteText.substring(0, deleteText.length() - 1));
            }
        });
        clear.setOnClickListener(v -> {
            textCalc.setText("");
            operand.setText("");
            result.setText("");
        });
        addition.setOnClickListener(v -> {
            if (result.getText().length() <= 0) {
                result.setText(textCalc.getText());
                textCalc.setText("");
                operand.setText("+");
            }
        });
        subtraction.setOnClickListener(v -> {
            if (result.getText().length() <= 0) {
                result.setText(textCalc.getText());
                textCalc.setText("");
                operand.setText("-");
            }
        });
        multiplication.setOnClickListener(v -> {
            if (result.getText().length() <= 0) {
                result.setText(textCalc.getText());
                textCalc.setText("");
                operand.setText("*");
            }
        });
        division.setOnClickListener(v -> {
            if (result.getText().length() <= 0) {
                result.setText(textCalc.getText());
                textCalc.setText("");
                operand.setText("/");
            }
        });
        equals.setOnClickListener(v -> {
            double res = Double.parseDouble(String.valueOf(result.getText()));
            double number2 = Double.parseDouble(String.valueOf(textCalc.getText()));
            if (operand.getText().length() < 1) {
                result.setText(textCalc.getText());
                textCalc.setText("");
            } else {
                switch (operand.getText().toString()) {
                    case "/":
                        if (number2 == 0) {
                            result.setText("division by zero");
                        } else {
                            result.setText(String.valueOf(res / number2));
                        }
                        operand.setText("");
                        textCalc.setText("");
                        break;
                    case "*":
                        result.setText(String.valueOf(res * number2));
                        operand.setText("");
                        textCalc.setText("");
                        break;
                    case "+":
                        result.setText(String.valueOf(res + number2));
                        operand.setText("");
                        textCalc.setText("");
                        break;
                    case "-":
                        result.setText(String.valueOf(res - number2));
                        operand.setText("");
                        textCalc.setText("");
                        break;
                }
            }
        });
        percent.setOnClickListener(v -> {
            double res;
            double number2;
            if (result.getText().length() > 0) {
                res = Double.parseDouble(String.valueOf(result.getText()));
                number2 = Double.parseDouble(String.valueOf(textCalc.getText()));
                if ((operand.getText().length() < 1) || result.getText().length() < 1) {
                    result.setText("0");
                    textCalc.setText("0");
                } else {
                    switch (operand.getText().toString()) {
                        case "/":
                        case "*":
                            textCalc.setText(String.valueOf((number2 / 100)));
                            break;
                        case "+":
                        case "-":
                            textCalc.setText(String.valueOf(res / 100 * number2));
                            break;
                    }
                }
            } else {
                result.setText("0");
                textCalc.setText("0");
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString("NUMBER", String.valueOf((textCalc.getText())));
        outState.putString("RESULT", String.valueOf((result.getText())));
        if (operand.getText().length()<0)
            outState.putString("OPERAND", String.valueOf(operand.getText()));
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        textCalc.setText(savedInstanceState.getString("NUMBER"));
        result.setText(savedInstanceState.getString("RESULT"));
        operand.setText(savedInstanceState.getString("OPERAND"));

    }
}