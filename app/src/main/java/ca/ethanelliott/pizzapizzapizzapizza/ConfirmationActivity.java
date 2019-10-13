package ca.ethanelliott.pizzapizzapizzapizza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Map;

public class ConfirmationActivity extends AppCompatActivity {

    double subtotal;
    double tax;
    double total;
    String name;

    TextView pizzaSizeTxt;
    TextView pizzaSizeCostTxt;
    TextView chargesTxt;
    TextView chargesCostTxt;
    TextView taxTxt;
    TextView taxCostTxt;
    TextView totalTxt;
    TextView totalCostTxt;

    TextView nameTxt;
    TextView addressTxt;
    TextView phoneNumberTxt;
    TextView emailTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        pizzaSizeTxt = findViewById(R.id.pizzaSizeTxt);
        pizzaSizeCostTxt = findViewById(R.id.pizzaSizeCostTxt);
        chargesTxt = findViewById(R.id.chargesTxt);
        chargesCostTxt = findViewById(R.id.chargesCostTxt);
        taxTxt = findViewById(R.id.taxTxt);
        taxCostTxt = findViewById(R.id.taxCostTxt);
        totalTxt = findViewById(R.id.totalTxt);
        totalCostTxt = findViewById(R.id.totalCostTxt);

        nameTxt = findViewById(R.id.nameTxt);
        addressTxt = findViewById(R.id.addressTxt);
        phoneNumberTxt = findViewById(R.id.phoneNumberTxt);
        emailTxt = findViewById(R.id.emailTxt);

        Intent thisIntent = getIntent();

        pizzaSizeTxt.setText(String.format("%s\n", thisIntent.getStringExtra("pizzaSize")));
        pizzaSizeCostTxt.setText(String.format(Locale.CANADA, "$%.2f\n", thisIntent.getDoubleExtra("pizzaCost", 0)));

        subtotal += thisIntent.getDoubleExtra("pizzaCost", 0);

        Map<String, Double> charges = (Map<String, Double>) thisIntent.getSerializableExtra("charges");

        StringBuilder tableItems = new StringBuilder();
        StringBuilder chargesCosts = new StringBuilder();
        for (String charge : charges.keySet()) {
            tableItems.append(String.format("%s\n", charge));
            chargesCosts.append(String.format(Locale.CANADA, "$%.2f\n", charges.get(charge)));
            subtotal += charges.get(charge);
        }
        tax = subtotal * 0.13;
        total = subtotal + tax;
        chargesTxt.setText(tableItems.toString());
        chargesCostTxt.setText(chargesCosts.toString());
        taxCostTxt.setText(String.format(Locale.CANADA, "$%.2f\n", tax));
        totalCostTxt.setText(String.format(Locale.CANADA, "$%.2f\n", total));

        nameTxt.setText(thisIntent.getStringExtra("name"));
        addressTxt.setText(thisIntent.getStringExtra("address"));
        phoneNumberTxt.setText(thisIntent.getStringExtra("phoneNumber"));
        emailTxt.setText(thisIntent.getStringExtra("email"));
    }

    public void back(View view) {
        finish();
    }

    public void submitOrder(View view) {
        WHERES_THE_TOAST("Order Sent!", "Your order has been submitted successfully!", Toast.LENGTH_LONG);
    }

    private void WHERES_THE_TOAST(String title, String message, int length) {
        LayoutInflater inflater = getLayoutInflater();
        View toastLayout = inflater.inflate(R.layout.my_toast, (ViewGroup) findViewById(R.id.toast_root_view));
        TextView titleTxt = toastLayout.findViewById(R.id.toast_title);
        titleTxt.setText(title);
        TextView bodyTxt = toastLayout.findViewById(R.id.toast_body);
        bodyTxt.setText(message);
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(length);
        toast.setView(toastLayout);
        toast.show();
    }
}
