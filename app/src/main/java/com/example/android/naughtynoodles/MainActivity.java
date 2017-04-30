
package com.example.android.naughtynoodles;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static android.os.Build.VERSION_CODES.M;
import static com.example.android.naughtynoodles.R.drawable.noodle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /***
     *  image view transparancy
     */

    int noodleAmount = 1;

    /**
     * SUBMIT BUTTON LINKED TO THIS METHOD
     */
    public void submitOrder(View view) {
        displayPrice(noodleAmount * 3);
    }

    /**
     * PLUS BUTTON LINKED TO THIS METHOD
     */
    public void increment(View view) {

        if (noodleAmount < 10) {
            noodleAmount = noodleAmount + 1;
            display(noodleAmount);
        } else {
            //display in short period of time
            Toast.makeText(getApplicationContext(), "10 Coffees Max", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    /**
     * minus BUTTON LINKED TO THIS METHOD
     */

    public void decrement(View view) {

        if (noodleAmount > 1) {
            noodleAmount = noodleAmount - 1;
            display(noodleAmount);
        } else {
            //display in short period of time
            Toast.makeText(getApplicationContext(), "Minimum 1 coffee", Toast.LENGTH_SHORT).show();
            return;
        }

    }

    /**
     * This is a simple method that makes the input number the textview
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText("£" + number);
    }


 

}

