package com.addusername.pmdm2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText input;
    private TextView output;
    private Integer[] primeNumbers = {1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input = (EditText) findViewById(R.id.input);
        output = (TextView) findViewById(R.id.output);
    }

    public void calc(View view){

        int index = Integer.parseInt(input.getText().toString());
        Maths maths =  new Maths(index, primeNumbers);
        Thread thread = new Thread(maths);

        thread.start();
        try {
            thread.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        primeNumbers = maths.primeNumbers;
        output.setText("El primo numero "+ index+" es el "+maths.result);
    }

    private class Maths implements Runnable {

        private String TAG = "myApp";
        private int index, result;
        private Integer [] primeNumbers;

        public Maths(int index, Integer[] primeNumbers) {
            this.index = index;
            this.primeNumbers = primeNumbers;
        }

        @Override
        public void run() {
            if (primeNumbers.length > index ){
                Log.e(TAG, "Reading stored int!");
                result = primeNumbers[index-1];
                return;
            }
            //if not
            Log.e(TAG, "Doing maths!");
            doMaths();
        }

        private void doMaths() {
            List<Integer> primeNumbersList = new ArrayList(Arrays.asList(primeNumbers));
            int num, j;
            boolean isPrime; //= false is redundant

            for (int i = primeNumbersList.size() - 1; i < index; i++) {
                j = 1;
                while (true) {
                    num = primeNumbersList.get(i) + j;
                    isPrime = true;

                    for (int k = 2; k < num; k++) {
                        if (num % k == 0) {
                            isPrime = false;
                            break;
                        }
                    }

                    if (isPrime) {
                        primeNumbersList.add(num);
                        break;
                    }
                    j++;
                }
            }

            primeNumbers = primeNumbersList.toArray(primeNumbers);
            result = primeNumbers[index - 1];
        }
    }
}