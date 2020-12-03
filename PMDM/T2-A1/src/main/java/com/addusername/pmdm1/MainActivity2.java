package com.addusername.pmdm1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("SecondaryActivity", "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }
    @Override
    protected void onStart() {
        Log.i("SecondaryActivity", "onStart");
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.i("SecondaryActivity", "onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.i("SecondaryActivity", "onDestroy");
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        Log.i("SecondaryActivity", "onPause");
        super.onPause();
    }

    @Override
    protected void onResume() {
        Log.i("SecondaryActivity", "onResume");
        super.onResume();
    }

    public void changeActivity2 (View view){
        Log.i("SecondaryActivity", "changeActivity2()");
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}