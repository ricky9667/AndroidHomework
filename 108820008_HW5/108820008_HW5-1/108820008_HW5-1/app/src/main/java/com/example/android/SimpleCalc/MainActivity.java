/*
 * Copyright 2018, Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.SimpleCalc;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * SimpleCalc is the initial version of SimpleCalcTest.  It has
 * a number of intentional oversights for the student to debug/fix,
 * including input validation (no input, bad number format, div by zero)
 *
 * In addition there is only one (simple) unit test in this app.
 * All the input validation and the unit tests are added as part of the lessons.
 *
 */
public class MainActivity extends Activity {

    private static final String TAG = "CalculatorActivity";

    private Calculator mCalculator;

    private EditText mOperandOneEditText;
    private EditText mOperandTwoEditText;

    private TextView mResultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the calculator class and all the views
        mCalculator = new Calculator();
        mResultTextView = findViewById(R.id.operation_result_text_view);
        mOperandOneEditText = findViewById(R.id.operand_one_edit_text);
        mOperandTwoEditText = findViewById(R.id.operand_two_edit_text);
    }

    /**
     * OnClick method called when the add Button is pressed.
     */
    public void onAdd(View view) {
        compute(Calculator.Operator.ADD);
    }

    /**
     * OnClick method called when the subtract Button is pressed.
     */
    public void onSub(View view) {
        compute(Calculator.Operator.SUB);
    }

    /**
     * OnClick method called when the divide Button is pressed.
     */
    public void onDiv(View view) {
        try {
            compute(Calculator.Operator.DIV);
        } catch (IllegalArgumentException iae) {
            Log.e(TAG, "IllegalArgumentException", iae);
            mResultTextView.setText(getString(R.string.computationError));
        }
    }

    /**
     * OnClick method called when the multiply Button is pressed.
     */
    public void onMul(View view) {
        compute(Calculator.Operator.MUL);
    }

    /**
     * OnClick method called when the power Button is pressed.
     */
    public void onPow(View view) {
        compute(Calculator.Operator.POW);
    }

    private void compute(Calculator.Operator operator) {
        double operandOne;
        double operandTwo;
        try {
            operandOne = getOperand(mOperandOneEditText);
            operandTwo = getOperand(mOperandTwoEditText);
        } catch (NumberFormatException nfe) {
            Log.e(TAG, "NumberFormatException", nfe);
            mResultTextView.setText(getString(R.string.computationError));
            return;
        }

        String result;
        switch (operator) {
            case ADD:
                result = String.valueOf(
                        mCalculator.add(operandOne, operandTwo));
                break;
            case SUB:
                result = String.valueOf(
                        mCalculator.sub(operandOne, operandTwo));
                break;
            case DIV:
                result = String.valueOf(
                        mCalculator.div(operandOne, operandTwo));
                break;
            case MUL:
                result = String.valueOf(
                        mCalculator.mul(operandOne, operandTwo));
                break;
            case POW:
                result = String.valueOf(
                        mCalculator.pow(operandOne, operandTwo));
                break;
            default:
                result = getString(R.string.computationError);
                break;
        }
        mResultTextView.setText(result);
    }

    /**
     * @return the operand value entered in an EditText as double.
     */
    private static Double getOperand(EditText operandEditText) {
        String operandText = getOperandText(operandEditText);
        return Double.valueOf(operandText);
    }

    /**
     * @return the operand text which was entered in an EditText.
     */
    private static String getOperandText(EditText operandEditText) {
        return operandEditText.getText().toString();
    }
}