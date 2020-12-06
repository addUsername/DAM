package com.addusername.pmdm5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    private Button btnSelected;
    private int frameLayoutId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frameLayoutId = R.id.whereFragmentsArePlaced;
    }
    public void click(View view){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        if ((view.getId() == R.id.fragment1))
            ft.replace(frameLayoutId, new FragmentOne());
        else
            ft.replace(frameLayoutId, new FragmentTwo());
        ft.commit();
        return;
    }
}