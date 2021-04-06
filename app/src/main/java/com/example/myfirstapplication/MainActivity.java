package com.example.myfirstapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Layout;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private final static String RESULT = "RESULT";
    private final static String NUMBER = "NUMBER";
    private final static String OPERAND = "OPERAND";
    private static final String NameSharedPreference = "THEME";
    private static final String AppTheme = "APP_THEME";
    private static final String DIVISION = "÷";
    private static final String MULTIPLICATION = "×";
    private static final String ADDITION = "+";
    private static final String SUBTRACTION = "−";

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
    private String deleteText;
    private double res;
    private double number2;

    protected static final int AppThemeLightCodeStyle = 0;
    protected static final int AppThemeCodeStyle = 1;
    protected static final int AppThemeDarkCodeStyle = 2;
    private static final int REQUEST_CODE_SETTING_ACTIVITY = 99;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(getAppTheme(R.style.MyStyle));
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
        findViewById(R.id.settings).setOnClickListener(v -> {
            Intent runSettings = new Intent(MainActivity.this, Settings.class);
            startActivityForResult(runSettings, REQUEST_CODE_SETTING_ACTIVITY);
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode != REQUEST_CODE_SETTING_ACTIVITY) {
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }
        if (resultCode == RESULT_OK) {
            recreate();
        }
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
            if (!textCalc.getText().toString().isEmpty()) {
                deleteText = textCalc.getText().toString();
                textCalc.setText(deleteText.substring(0, deleteText.length() - 1));
            }
        });
        clear.setOnClickListener(v -> {
            textCalc.setText("");
            operand.setText("");
            result.setText("");
        });
        addition.setOnClickListener(v -> transferValue(addition.getText().toString()));
        subtraction.setOnClickListener(v -> transferValue(subtraction.getText().toString()));
        multiplication.setOnClickListener(v -> transferValue(multiplication.getText().toString()));
        division.setOnClickListener(v -> transferValue(division.getText().toString()));
        equals.setOnClickListener(v -> {

            if (operand.getText().toString().isEmpty()) {
                result.setText(textCalc.getText());
                textCalc.setText("");
            } else {
                res = Double.parseDouble(String.valueOf(result.getText()));
                number2 = Double.parseDouble(String.valueOf(textCalc.getText()));
                operations(operand.getText().toString(), res, number2);
            }
        });
        percent.setOnClickListener(v -> {
            if (!result.getText().toString().isEmpty()) {
                if ((operand.getText().toString().isEmpty() || result.getText().toString().isEmpty())) {
                    result.setText("0");
                    textCalc.setText("0");
                } else {
                    res = Double.parseDouble(String.valueOf(result.getText()));
                    number2 = Double.parseDouble(String.valueOf(textCalc.getText()));
                    performingDivision(operand.getText().toString(), res, number2);
                }
            } else {
                result.setText("0");
                textCalc.setText("0");
            }
        });
    }

    private void transferValue(String sign) {
        if (result.getText().toString().isEmpty()) {
            result.setText(textCalc.getText());
            textCalc.setText("");
            operand.setText(sign);
        }
    }

    private void operations(String sign, double res, double number2) {
        switch (sign) {
            case DIVISION:
                try {
                    result.setText(new DecimalFormat("#.##").format(res / number2));
                    operand.setText("");
                    textCalc.setText("");
                } catch (ArithmeticException e) {
                    result.setText(R.string.divisionByZero);
                }
                break;
            case MULTIPLICATION:
                result.setText(new DecimalFormat("#.##").format(res * number2));
                operand.setText("");
                textCalc.setText("");
                break;
            case ADDITION:
                result.setText(new DecimalFormat("#.##").format(res + number2));
                operand.setText("");
                textCalc.setText("");
                break;
            case SUBTRACTION:
                result.setText(new DecimalFormat("#.##").format(res - number2));
                operand.setText("");
                textCalc.setText("");
                break;
        }
    }

    private void performingDivision(String sign, double res, double number2) {
        switch (sign) {
            case DIVISION:
            case MULTIPLICATION:
                textCalc.setText(new DecimalFormat("#.####").format(number2 / 100));
                break;
            case ADDITION:
            case SUBTRACTION:
                textCalc.setText(new DecimalFormat("#.####").format(res / 100 * number2));
                break;
        }
    }

    private int codeStyleToStyleId(int codeStyle) {
        switch (codeStyle) {
            case AppThemeCodeStyle:
                return R.style.AppTheme;
            case AppThemeLightCodeStyle:
                return R.style.AppThemeLight;
            case AppThemeDarkCodeStyle:
                return R.style.AppThemeDark;
            default:
                return R.style.MyStyle;
        }
    }

    private int getAppTheme(int codeStyle) {
        return codeStyleToStyleId(getCodeStyle(codeStyle));
    }

    protected int getCodeStyle(int codeStyle) {
        SharedPreferences sharedPref = getSharedPreferences(NameSharedPreference, MODE_PRIVATE);
        return sharedPref.getInt(AppTheme, codeStyle);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString(NUMBER, String.valueOf((textCalc.getText())));
        outState.putString(RESULT, String.valueOf((result.getText())));
        if (operand.getText().length() < 0) {
            outState.putString(OPERAND, String.valueOf(operand.getText()));
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        textCalc.setText(savedInstanceState.getString(NUMBER));
        result.setText(savedInstanceState.getString(RESULT));
        operand.setText(savedInstanceState.getString(OPERAND));

    }
}