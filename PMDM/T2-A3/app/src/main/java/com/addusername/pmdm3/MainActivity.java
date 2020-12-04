package com.addusername.pmdm3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private RadioGroup baseInput, baseOutput;
    private EditText input;
    private TextView output;
    private String checkedInput = "bin";
    private String checkedOutput = "bin";
    public String numToConver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        baseInput = (RadioGroup) findViewById(R.id.radioGroupInput);
        baseOutput = (RadioGroup) findViewById(R.id.radioGroupOutput);
        input = (EditText) findViewById(R.id.input);
        output = (TextView) findViewById(R.id.output);

        applyOnChangeListener();
    }
    public void convert(View view){
        numToConver = input.getText().toString();
        try{
            output.setBackgroundColor(0);
            output.setText(doMaths());
        }catch (Exception e){
            output.setBackgroundColor(Color.RED);
            output.setText("bad writting");
        }
    }
    private String doMaths() {

        switch(checkedInput){
            case "bin":
                if(checkedOutput.equals("dec"))
                    return Integer.toString(Integer.parseInt(numToConver,2));
                if(checkedOutput.equals("hex"))
                    return Integer.toHexString(Integer.parseInt(numToConver,2));
                return numToConver;
            case "dec":
                if(checkedOutput.equals("bin"))
                    return Integer.toBinaryString(Integer.parseInt(numToConver));
                if(checkedOutput.equals("hex"))
                    return Integer.toHexString(Integer.parseInt(numToConver));
                return numToConver;
            case "hex":
                if(checkedOutput.equals("bin"))
                    return Integer.toBinaryString(Integer.parseInt(numToConver,16));
                if(checkedOutput.equals("dec"))
                    return Integer.toString(Integer.parseInt(numToConver,16));
                return numToConver;
        }
        return null;
    }

    private void applyOnChangeListener() {
        baseInput.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == R.id.binary){
                    checkedInput = "bin";
                }else if (checkedId == R.id.decimal){
                    checkedInput = "dec";
                }else if (checkedId == R.id.hexadecimal){
                    checkedInput = "hex";
                }
            }
        });
        baseOutput.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == R.id.binaryOut){
                    checkedOutput = "bin";
                }else if (checkedId == R.id.decimalOut){
                    checkedOutput = "dec";
                }else if (checkedId == R.id.hexadecimalOut){
                    checkedOutput = "hex";
                }
            }
        });
    }
}