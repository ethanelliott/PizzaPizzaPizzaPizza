package ca.ethanelliott.pizzapizzapizzapizza;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public double runningTotal;
    double previousPizzaSize;
    String pizzaSize;
    double pizzaCost;

    Map<String, Double> charges;


    TextView runningTotalTxt;
    RadioGroup radioGroup;

    CheckBox addMushroomChk;
    CheckBox addTomatoesChk;
    CheckBox addChickenChk;
    CheckBox addBeefChk;
    CheckBox addShrimpChk;
    CheckBox addPineappleChk;
    CheckBox addSteakChk;
    CheckBox addAvocadoChk;
    CheckBox addTunaChk;
    CheckBox addBroccoliChk;
    CheckBox addExtraCheeseChk;
    CheckBox addDeliveryChk;

    EditText nameTxt;
    EditText addressTxt;
    EditText phoneTxt;
    EditText emailTxt;

    private final RadioGroup.OnCheckedChangeListener pizzaSizeHandler = new RadioGroup.OnCheckedChangeListener() {
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            runningTotal -= previousPizzaSize;
            switch (checkedId) {
                case R.id.size6Rdo:
                    runningTotal += 5.5;
                    previousPizzaSize = 5.5;
                    pizzaSize = "Small 6 Slice";
                    pizzaCost = 5.5;
                    break;
                case R.id.size8Rdo:
                    runningTotal += 7.99;
                    previousPizzaSize = 7.99;
                    pizzaSize = "Medium 8 Slice";
                    pizzaCost = 7.99;
                    break;
                case R.id.size10Rdo:
                    runningTotal += 9.5;
                    previousPizzaSize = 9.5;
                    pizzaSize = "Large 10 Slice";
                    pizzaCost = 9.5;
                    break;
                case R.id.size12Rdo:
                    runningTotal += 11.38;
                    previousPizzaSize = 11.38;
                    pizzaSize = "X-Large 12 Slice";
                    pizzaCost = 11.38;
                    break;
            }
            updateTotal(0);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        runningTotal = 5.5;
        previousPizzaSize = 5.5;
        pizzaSize = "Small 6 Slice";
        pizzaCost = 5.5;

        charges = new HashMap<>();

        // ID Lookups
        runningTotalTxt = findViewById(R.id.runningTotalTxt);
        radioGroup = findViewById(R.id.pizzaSizeGroup);
        addMushroomChk = findViewById(R.id.addMushroomChk);
        addTomatoesChk = findViewById(R.id.addTomatoesChk);
        addChickenChk = findViewById(R.id.addChickenChk);
        addBeefChk = findViewById(R.id.addBeefChk);
        addShrimpChk = findViewById(R.id.addShrimpChk);
        addPineappleChk = findViewById(R.id.addPineappleChk);
        addSteakChk = findViewById(R.id.addSteakChk);
        addAvocadoChk = findViewById(R.id.addAvocadoChk);
        addTunaChk = findViewById(R.id.addTunaChk);
        addBroccoliChk = findViewById(R.id.addBroccoliChk);
        addExtraCheeseChk = findViewById(R.id.addExtraCheeseChk);
        addDeliveryChk = findViewById(R.id.addDeliveryChk);

        nameTxt = findViewById(R.id.nameTxt);
        addressTxt = findViewById(R.id.addressTxt);
        phoneTxt = findViewById(R.id.phoneTxt);
        emailTxt = findViewById(R.id.emailTxt);

        // Handlers
        radioGroup.setOnCheckedChangeListener(pizzaSizeHandler);

        // Update the total
        updateTotal();
    }

    /* Methods on the view*/

    public void confirmOrder(View view) {
        String name = nameTxt.getText().toString();
        String address = addressTxt.getText().toString();
        String phoneNumber = phoneTxt.getText().toString();
        String email = emailTxt.getText().toString();

        Intent confirmationActivity = new Intent(MainActivity.this, ConfirmationActivity.class);
        confirmationActivity.putExtra("pizzaSize", pizzaSize);
        confirmationActivity.putExtra("pizzaCost", pizzaCost);
        confirmationActivity.putExtra("name", name);
        confirmationActivity.putExtra("address", address);
        confirmationActivity.putExtra("phoneNumber", phoneNumber);
        confirmationActivity.putExtra("email", email);
        confirmationActivity.putExtra("charges", (Serializable) this.charges);
        startActivityForResult(confirmationActivity, 13);
    }

    public void addMushroom(View view) {
        CheckBox checkBox = addMushroomChk;
        double cost = 5.0;
        String name = "Mushroom";
        modifyCharges(checkBox, cost, name);
    }

    public void addTomatoes(View view) {
        modifyCharges(addTomatoesChk, 5.0, "Sun Dried Tomatoes");
    }

    public void addChicken(View view) {
        modifyCharges(addChickenChk, 7.0, "Chicken");
    }

    public void addBeef(View view) {
        modifyCharges(addBeefChk, 8.0, "Ground Beef");
    }

    public void addShrimp(View view) {
        modifyCharges(addChickenChk, 10.0, "Shrimp");
    }

    public void addPineapple(View view) {
        WHERES_THE_TOAST("ERROR", "Gross! This is not allowed!", Toast.LENGTH_SHORT);
        Handler unCheckHandler = new Handler();
        unCheckHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                addPineappleChk.setChecked(false);
            }
        }, 500);

//        modifyCharges(addPineappleChk, 5.0, "Pineapple");
    }

    public void addSteak(View view) {
        modifyCharges(addSteakChk, 9.0, "Steak");
    }

    public void addAvocado(View view) {
        modifyCharges(addAvocadoChk, 5.0, "Avocado");
    }

    public void addTuna(View view) {
        modifyCharges(addTunaChk, 5.0, "Tuna");
    }

    public void addBroccoli(View view) {
        modifyCharges(addBroccoliChk, 8.0, "Broccoli");
    }

    public void addExtraCheese(View view) {
        modifyCharges(addExtraCheeseChk, 5.0, "Extra Cheese");
    }

    public void addDelivery(View view) {
        modifyCharges(addDeliveryChk, 5.0, "Delivery");
    }

    /* Local Methods */
    private void updateTotal() {
        updateTotal(0);
    }

    private void updateTotal(double amount) {
        this.runningTotal += amount;
        runningTotalTxt.setText(String.format(Locale.CANADA, "Total: $%.2f", this.runningTotal));
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

    private void modifyCharges(CheckBox checkBox, double cost, String name) {
        if (checkBox.isChecked()) {
            updateTotal(cost);
            charges.put(name, cost);
        } else {
            updateTotal(-cost);
            charges.remove(name);
        }
    }
}
