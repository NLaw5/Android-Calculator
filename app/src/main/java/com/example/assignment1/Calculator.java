package com.example.assignment1;

import java.util.ArrayList;
import java.util.List;

public class Calculator {
    List<String> calculation = new ArrayList<String>();
    //flag for single digit/single operator
    //first value must be singleDigit
    boolean doubleDigit = false;
    //Second value will be operator;therefore, set to false
    boolean doubleOperator = true;
    String push(String value, boolean clear)
    {
        if(!clear)
        {

            if(!(value.equals("-") || value.equals("+") || value.equals("*")
                    || value.equals("/")) && doubleDigit == false)
            {
                calculation.add(value);
                doubleDigit = true;
                doubleOperator = false;
            }
            else if ((value.equals("-") || value.equals("+")
                    || value.equals("*") || value.equals("/")) && doubleOperator == false)
            {
                calculation.add(value);
                doubleDigit = false;
                doubleOperator = true;
            }

            return calculation.toString().replace(",", "")
                    .replace("[","").replace("]","");
        }
        else
            //Clears the calculation
        {
            calculation.clear();
            doubleDigit = false;
            doubleOperator = true;
            return calculation.toString().replace(",", "")
                    .replace("[","").replace("]","");
        }
    }

    int calculate()
    {
        doubleDigit = false;
        doubleOperator = true;

        int result = -9999999; //our flag for initial value, since 0 cannot be used
        String savedOperator = "nothing";
        for(String number:calculation)
        {
            if(result == -9999999) //initial number
            {
                result = Integer.parseInt(number);
            }
            else
            {
                if(number.equals("+") || number.equals("-") || number.equals("*") || number.equals("/"))
                {
                    savedOperator = number;
                }
                else //if it's not operator but the next number
                {
                    if(savedOperator.equals("+"))
                    {
                        result = result + Integer.parseInt(number);
                    }
                    else if(savedOperator.equals("-"))
                    {
                        result = result - Integer.parseInt(number);
                    }
                    else if(savedOperator.equals("*"))
                    {
                        result = result * Integer.parseInt(number);
                    }
                    else
                    {
                        result = result / Integer.parseInt(number);
                    }
                }
            }
        }
        return result;
    }
}
