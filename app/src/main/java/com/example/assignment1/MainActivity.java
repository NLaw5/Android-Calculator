package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    //Properties to be set outside of class, global variables:
    String tag = "Assignment 1";
    List<String> advancedHistory = new ArrayList<String>();
    String calculation;
    //Button initalization:
    Button one;
    Button two;
    Button three;
    Button four;
    Button five;
    Button six;
    Button seven;
    Button eight;
    Button nine;
    Button zero;
    Button plus;
    Button divide;
    Button multiply;
    Button minus;
    Button clear;
    Button equals;
    Button advance;

    //Textview:
    TextView result;
    TextView history;
    //Flags:
    boolean isAdvanced = false;

    //Set new calculator instance
    private Calculator myCalculator = new Calculator();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(tag,"in OnCreate");
        setContentView(R.layout.activity_main);

        //Set all button properties
        one = (Button) findViewById(R.id.one);
        two = (Button) findViewById(R.id.two);
        three = (Button) findViewById(R.id.three);
        four = (Button) findViewById(R.id.four);
        five = (Button) findViewById(R.id.five);
        six = (Button) findViewById(R.id.six);
        seven = (Button) findViewById(R.id.seven);
        eight = (Button) findViewById(R.id.eight);
        nine = (Button) findViewById(R.id.nine);
        zero = (Button) findViewById(R.id.zero);
        clear = (Button) findViewById(R.id.clear);
        equals = (Button) findViewById(R.id.equals);
        advance = (Button) findViewById(R.id.advanced);
        plus = (Button) findViewById(R.id.plus) ;
        divide = (Button) findViewById(R.id.divide);
        multiply = (Button) findViewById(R.id.multiply);
        minus = (Button) findViewById(R.id.minus);

        //Set all textview properties
        result = (TextView) findViewById(R.id.result);
        history = (TextView) findViewById(R.id.history);



        //Function call:
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        zero.setOnClickListener(this);
        result.setOnClickListener(this);
        clear.setOnClickListener(this);
        advance.setOnClickListener(this);
        plus.setOnClickListener(this);
        minus.setOnClickListener(this);
        multiply.setOnClickListener(this);
        divide.setOnClickListener(this);
        equals.setOnClickListener(this);
    }

    //One function for all buttons
    @Override
    public void onClick(View view)
    {
        //Advanced Buttons
        if(((Button)view).getText().toString().equals("ADVANCE - WITH HISTORY")
                && isAdvanced == false)
        {
            advance.setText("STANDARD - NO HISTORY");
            advance.setBackgroundTintList(ContextCompat.getColorStateList(MainActivity.this,
                    R.color.Gray));
            isAdvanced = true;
        }
        else if(((Button)view).getText().toString().equals("STANDARD - NO HISTORY"))
        {
            advance.setText("ADVANCE - WITH HISTORY");
            advance.setBackgroundTintList(ContextCompat.getColorStateList(MainActivity.this,
                    R.color.BlueViolet));
            isAdvanced = false;
            advancedHistory.clear();
            //Clear both result and history (more preference than anything
            // since I want the UI clean when switching
            result.setText("");
            history.setText("");
        }
        else
        {
            prepareForCalc(view);
        }
    }

    //Functions depending on buttons
    //All logic for buttons will be done here in terms of function call:
    void prepareForCalc(View view)
    {
        //1. If it is not C, or = then push to myCalculator.push
        //2. If it is C, clear the string
        //3. If it is equal, send to setCalculation

        //Note: Step 1 and 2 will result in textview of result being changed no matter what
        boolean clear = false;
        if(((Button)view).getText().toString().equals("C"))
        {
            clear = true;
            calculation = myCalculator.push("", clear);
            result.setText(calculation);
        }
        else if(((Button)view).getText().toString().equals("="))
        {
            setCalculation(view);
        }
        else
        {
            clear = false;
            calculation = myCalculator.push(((Button)view).getText().toString(), clear);
            result.setText(calculation);
        }
    }

    void setCalculation(View view)
    {
        Integer results;
        results = myCalculator.calculate();
        calculation = calculation + " = " + results.toString();
        result.setText(calculation);

        if(isAdvanced == true)
        {
            advancedHistory.add(calculation + '\n');
            String historyOfResults = "";
            for(String historyResult: advancedHistory)
            {
                historyOfResults = historyOfResults + historyResult;
            }
            history.setText(historyOfResults);
        }

        //must clear the string
        calculation = myCalculator.push("", true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(tag,"in OnResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(tag,"in OnPause");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(tag,"in OnStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(tag,"in OnStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(tag,"in OnDestroy");
    }
}