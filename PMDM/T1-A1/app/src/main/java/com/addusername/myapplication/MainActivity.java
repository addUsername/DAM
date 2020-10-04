package com.addusername.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private Spinner options;
    private EditText url;
    private EditText headers;
    private EditText body;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        options = (Spinner) findViewById(R.id.options);
        url = (EditText) findViewById(R.id.url);
        headers = (EditText) findViewById(R.id.headers);
        body = (EditText) findViewById(R.id.body);
    }

    public void myFancyMethod(View v) {
        //Here we are going to instantiate restService.java
        Log.d("myTag", "This is my message");
        Log.d("Spinner", options.getSelectedItem().toString());
        Log.d("url",url.getText().toString());
    }

    public void saveRequest(View v){

        File root = new File(Environment.getExternalStorageDirectory(), "Request");
        Log.d("creating folder",Environment.getExternalStorageDirectory().getPath());
        if (!root.exists()) {
            Log.d("creating folder","yay");
            Boolean created = root.mkdirs();
            Log.d("creating folder",created.toString());
        }
        // ojo cambiar esto para añadir diferentes colecciones
            /*File collectionRequest = new File(root, "myRequest.json");
            FileWriter writer = new FileWriter(gpxfile);
            writer.append(sBody);
            writer.flush();
            writer.close();
             */
        Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
    }
}