package com.addusername.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView helloWorld;
    private Random rd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helloWorld = (TextView) findViewById(R.id.textView);
        rd = new Random();
    }
    public void onClickEvent(View view){
        int primaryColor = Color.rgb(rd.nextInt(255),rd.nextInt(255),rd.nextInt(255));
        int secondaryColor = Color.rgb(rd.nextInt(255),rd.nextInt(255),rd.nextInt(255));
        helloWorld.setText(rdText());
        helloWorld.setTextColor(primaryColor);
        helloWorld.setBackgroundColor(secondaryColor);
        helloWorld.setTextSize(rd.nextInt(20)+6);
        helloWorld.setTypeface(null, rdType());
    }
    private String rdText(){
        String[] text ={
                "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s",
                "Lorem Ipsum ka qenë teksti shabllon i industrisë që nga vitet 1500",
                "използван в печатарската и типографската индустрия. Lorem Ipsum е индустриален стандарт от около 1500 година",
                "Lorem Ipsum ist in der Industrie bereits der Standard Demo-Text seit 1500"
        };
        return text[rd.nextInt(4)];
    }
    private int rdType(){
        int[] type = {
                Typeface.BOLD_ITALIC,
                Typeface.BOLD,
                Typeface.ITALIC,
                Typeface.NORMAL
        };
        return type[rd.nextInt(4)];
    }
}