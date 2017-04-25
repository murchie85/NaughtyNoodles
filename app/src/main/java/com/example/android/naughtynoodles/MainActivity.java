package com.example.android.naughtynoodles;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 * <p>
 * package com.example.android.justjava;
 */

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.android.naughtynoodles.R.id.Name;
import static com.example.android.naughtynoodles.R.id.quantity_text_view;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity =1;

    /**
     * START UP CODE.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public  boolean onCreateOptionsMenu(Menu menu){
        // inflate the menu
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    public void submitOrder(View view) { // MAINLINE ON STARTUP
        //get name
        EditText CustomerName = (EditText) findViewById(Name);
        String ClientName = CustomerName.getText().toString();


        //=============================checkbox===================================
        // Define topping variables
        CheckBox WhippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = WhippedCreamCheckBox.isChecked();

        // get chocolate preference
        CheckBox ChocolateCheckBox = (CheckBox) findViewById(R.id.Chocolate_Checkbox);
        boolean hasChocolate = ChocolateCheckBox.isChecked();

        // get latte preference

        CheckBox LatteCheckBox = (CheckBox) findViewById(R.id.Latte_Checkbox);
        boolean hasLatte = LatteCheckBox.isChecked();



        //=============================checkbox===================================


        //calculate price
        int price = calculatePrice(hasWhippedCream,hasChocolate,hasLatte);  // taking the output of this method and passing into next method below
        String PriceMessage = createOrderSummary(price, hasWhippedCream, hasChocolate, hasLatte, ClientName);



        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"coffeeshop@gmail..com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Coffee order for " + ClientName);
        intent.putExtra(Intent.EXTRA_TEXT, PriceMessage);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
            //only starts activity if there is an available activity to send
        }


        //displayMessage(createOrderSummary(price, hasWhippedCream, hasChocolate, ClientName));
    }





    /**
     * gives a summary of the order
     * @param price this is the price fed in from previous method
     * @param addWhippedCream this is if the checkbox is ticked or not
     *  returns text summary
     */
    private String createOrderSummary(int price, boolean addWhippedCream, boolean addChocolate, boolean addLatte, String Clientname) {
        String priceMessage = getString(R.string.order_summary_name, Clientname);
        priceMessage += "\nLatte? " + addLatte;
        priceMessage += "\nAdd whipped Cream? " + addWhippedCream;
        priceMessage += "\nAdd Chocolate? " + addChocolate;
        priceMessage = priceMessage + "\nQuantity: " + quantity; // longhand
        priceMessage += "\nTotal: " + "£" + price;    // += shorthand for pm = pm
        priceMessage += "\n" + getString(R.string.thank_you);
        return priceMessage;
    }


    /**
     * This method is incrememnts the quantity.
     */
    public void increment(View view) {
        if(quantity < 100) {
            quantity = quantity + 1;
            displayQuantity(quantity);
        }
        else{
            //display in short period of time
            Toast.makeText(getApplicationContext(), "Maximum 100 coffees", Toast.LENGTH_SHORT).show();
            return;
        }

    }

    /**
     * This method decrements the quantity.
     */
    public void decrement(View view) {

        if(quantity>1){
            quantity = quantity -1;
            displayQuantity(quantity);}
        else{
            //display in short period of time
            Toast.makeText(getApplicationContext(), "Minimum 1 coffee", Toast.LENGTH_SHORT).show();
            return;
        }

    }


    /**
     * Calculates the price of the order.
     * @param hasChocolate takes boolean choc ys or no
     *@param  hasWhippedCream takes whipped cream yes/no val
     */
    private int calculatePrice(boolean hasWhippedCream,boolean hasChocolate, boolean hasLatte) {

        int basePrice = 0;

        if(hasWhippedCream){
            basePrice += 1;
        }
        if (hasChocolate){
            basePrice += 2;
        }

        if (hasLatte){
            basePrice += 3;
        }

        int price = quantity * basePrice;
        return price;
    }

    /**
     * This method displays the given quantity value on the screen.
     * each line explained
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(quantity_text_view);
        /* what is happening is it finds quantity_text_view and (Textview) just says
            I am a text view. passes it to working storage QuantityTextView that is also declared as
            Textview. The rhs (TextView) is casting it as a text view
         */
        quantityTextView.setText("" + number);  // use quantitytextview object and call set text method
    }







//                      TURNED OFF CODE A
//    /**
//     * This method displays the given text on the screen.
//     */
//    private void displayMessage(String message) {
//        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
//        orderSummaryTextView.setText(message); // use ordersumm.. object and call settext method
//    }





}
