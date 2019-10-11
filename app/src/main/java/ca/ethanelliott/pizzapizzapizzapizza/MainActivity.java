package ca.ethanelliott.pizzapizzapizzapizza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import javax.crypto.Mac;

public class MainActivity extends AppCompatActivity {

    Button orderBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        orderBtn = findViewById(R.id.orderBtn);
    }

    public void confirmOrder(View view) {
        Intent confirmationActivity = new Intent(MainActivity.this, ConfirmationActivity.class);
        startActivityForResult(confirmationActivity, 13);
    }
}
