
package com.example.android.naughtynoodles;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /**
     * This method is linked ot the activity main submit text, via the onlcick method
     */
    public void submitOrder(View view) {
        int noodleAmount = 2;
        display(noodleAmount);
        displayPrice(noodleAmount * 3);
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

