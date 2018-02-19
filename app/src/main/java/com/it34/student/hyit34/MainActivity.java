package com.it34.student.hyit34;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    TextView displayPrimary, displaySecondary, operationSign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayPrimary = findViewById(R.id.display_primary);
        displaySecondary = findViewById(R.id.display_secondary);
        operationSign = findViewById(R.id.operation_sign);

        final String[] numbers = new String[] { "9", "8", "7", "6", "5", "4", "3", "2", "1", "0", "."};
        final ArrayList<String> numberGridList = new ArrayList<String>();
        Collections.addAll(numberGridList, numbers);

        final GridView numberGrid = (GridView) findViewById(R.id.number_grid);
        ArrayAdapter<String> numberGridAdapter =
                new ArrayAdapter<String>(this, R.layout.keypad_item_view, numberGridList);
        numberGrid.setAdapter(numberGridAdapter);

        numberGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                displayPrimary.append(numbers[position]);
            }
        });

        final ListView operationsList = findViewById(R.id.operations);
        final String[] operationsListArray = new String[] {"+","-","/","*","="};
        ArrayAdapter<String> operationsListAdapter =
                new ArrayAdapter<String>(this, R.layout.keypad_item_view, operationsListArray);
        operationsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==4){
                    float num1 = Float.parseFloat(displaySecondary.getText().toString());
                    float num2 = Float.parseFloat(displayPrimary.getText().toString());
                    float result = result(num1, num2, operationSign.getText().toString());
                    displayPrimary.setText(String.valueOf(result));
                    displaySecondary.setText("");
                }
                else {
                    displaySecondary.setText(displayPrimary.getText());
                    displayPrimary.setText("");
                }
                operationSign.setText(operationsListArray[position]);
            }
        });
        operationsList.setAdapter(operationsListAdapter);
    }

    public float result(float num1, float num2, String operationSign){
        switch (operationSign){
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "/":
                return num1 / num2;
            case "*":
                return num1 * num2;
        }
        return 0;
    }

    public void clear(View view){
        displayPrimary.setHint("0");
        displaySecondary.setText("");
        operationSign.setText("");
    }

    public void back(View view){
        String previous = displayPrimary.getText().toString();
        if(!Objects.equals(previous, ""))
            displayPrimary.setText(previous.substring(0, previous.length()-1));
    }
}
