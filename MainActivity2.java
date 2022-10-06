package com.example.assignment1_100749883;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity
{
    //initialize variables
    Button button;
    TextView nameTextView, addressTextView,phoneTextView,emailTextView,toppingTextView,sizeTextView,xCheeseTextView,deliveryTextView,costTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        String name = getIntent().getStringExtra("keyName");
        String address = getIntent().getStringExtra("keyAddress");
        String number = getIntent().getStringExtra("keyNumber");
        String email = getIntent().getStringExtra("keyEmail");
        String topping = getIntent().getStringExtra("keyTopping");
        String size = getIntent().getStringExtra("keySize");
        String extraCheese = getIntent().getStringExtra("keyExtraCheese");
        String delivery = getIntent().getStringExtra("keyDelivery");
        String cost = getIntent().getStringExtra("keyCost");

        nameTextView = findViewById(R.id.nameText);
        addressTextView = findViewById(R.id.addressText);
        phoneTextView = findViewById(R.id.phoneText);
        emailTextView = findViewById(R.id.emailText);
        toppingTextView = findViewById(R.id.toppingSelected);
        sizeTextView = findViewById(R.id.sizeOfPizza);
        xCheeseTextView = findViewById(R.id.extraCheese);
        deliveryTextView = findViewById(R.id.deliveryCharge);
        costTextView = findViewById(R.id.totalCost);

        //display confirmation page
        nameTextView.setText(name);
        addressTextView.setText(address);
        phoneTextView.setText(number);
        emailTextView.setText(email);
        toppingTextView.setText(topping);
        sizeTextView.setText(size);
        xCheeseTextView.setText(extraCheese);
        deliveryTextView.setText(delivery);
        costTextView.setText(cost);

        //button to reorder pizza
        button = findViewById(R.id.backButton);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //used to switch between activities
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}

