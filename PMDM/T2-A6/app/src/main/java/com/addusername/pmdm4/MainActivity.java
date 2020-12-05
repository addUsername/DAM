package com.addusername.pmdm4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private WebView webview;
    private EditText input;
    private Toast toast;

    private String BOOKMARK1 = "http://www.google.com";
    private String BOOKMARK2 = "http://www.stackoverflow.com";

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mymenu, menu);
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = findViewById(R.id.input);

        webview = findViewById(R.id.webView);
        webview.setWebViewClient(new WebViewClient());

        toast = Toast.makeText(getApplicationContext(),
                getString(R.string.error),
                Toast.LENGTH_SHORT);
    }
    public void click(View view){
        String url = String.valueOf(input.getText());
        if(url.matches("^http:\\/\\/.*")) webview.loadUrl(url);
        else toast.show();
    }
    public boolean bookmarkClick(MenuItem item){
        if(String.valueOf(item.getTitle()).equals("Google")){
            webview.loadUrl(BOOKMARK1);
            input.setText(BOOKMARK1);
        }
        else {
            webview.loadUrl(BOOKMARK2);
            input.setText(BOOKMARK2);
        }
        return true;
    }
}