package com.example.asignment1_jathavan;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{
    Button button;
    Spinner toppingSpinner;
    String toppingSelected, sizeSelected, xCheese="No Extra Cheese", yDelivery="No Delivery";
    CheckBox extraCheese, Delivery;
    RadioGroup pizzaSize;
    EditText fullName,homeAddress,phoneNumber,Email;
    DecimalFormat df = new DecimalFormat("0.00");
    double cost = 0.0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toppingSpinner = findViewById(R.id.toppingSpinner);
        extraCheese = findViewById(R.id.checkbox_extraCheese);
        Delivery = findViewById(R.id.checkbox_delivery);
        button = findViewById(R.id.order);
        pizzaSize = findViewById(R.id.pizzaSizeRG);


        //calculate price
        pizzaSize.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId)
            {
                switch(checkedId){
                    case R.id.smallPizza:
                        cost+=5.50;
                        sizeSelected="Six Slices";
                        break;
                    case R.id.mediumPizza:
                        cost+=7.99;
                        sizeSelected="Eight Slices";
                        break;
                    case R.id.largePizza:
                        cost+=9.50;
                        sizeSelected="Ten Slices";
                        break;
                    case R.id.XlargePizza:
                        cost+=11.38;
                        sizeSelected="Twelve Slices";
                        break;
                    default:
                        break;
                }
            }
        });

        //calculates price if extra cheese is selected
        extraCheese.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked)
            {
                if(isChecked)
                {
                    cost+=5;
                    xCheese = "Extra Cheese($5)";
                }
                else
                    cost-=5;

            }
        });
        //calculated cost if delivery is needed
        Delivery.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked)
            {
                if(isChecked)
                {
                    cost += 5;
                    yDelivery = "Delivery($5)";
                }
                else
                    cost-=5;
            }
        });

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                fullName= findViewById(R.id.nameInput);
                homeAddress= findViewById(R.id.address);
                phoneNumber=findViewById(R.id.phone);
                Email=findViewById(R.id.email);
                String name = fullName.getText().toString();
                String address = homeAddress.getText().toString();
                String pNumber = phoneNumber.getText().toString();
                String email = Email.getText().toString();
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("keyName", name);
                intent.putExtra("keyAddress", address);
                intent.putExtra("keyNumber", pNumber);
                intent.putExtra("keyEmail", email);
                intent.putExtra("keyTopping",toppingSelected);
                intent.putExtra("keyExtraCheese",xCheese);
                intent.putExtra("keyDelivery",yDelivery);
                intent.putExtra("keySize",sizeSelected);
                intent.putExtra("keyCost", df.format(cost));
                startActivity(intent);
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.Toppings, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        toppingSpinner.setAdapter(adapter);

        toppingSpinner.setOnItemSelectedListener(this);


    }
    @Override
    //calculates cost on which toppings picked
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        toppingSelected = toppingSpinner.getItemAtPosition(position).toString();
        if(toppingSelected.equalsIgnoreCase("Mushroom($5)")||toppingSelected.equalsIgnoreCase("Sun Dried Tomatoes($5)")||toppingSelected.equalsIgnoreCase("Pineapple($5)")||toppingSelected.equalsIgnoreCase("Avocado($5)")||toppingSelected.equalsIgnoreCase("Tuna($5)"))
            cost += 5;
        else if(toppingSelected.equalsIgnoreCase("Chicken($7)"))
            cost+=7;
        else if(toppingSelected.equalsIgnoreCase("Ground Beef($8)")||toppingSelected.equalsIgnoreCase("Broccoli($8)"))
            cost+=8;
        else if(toppingSelected.equalsIgnoreCase("Steak($9)"))
            cost+=9;
        else if(toppingSelected.equalsIgnoreCase("Shrimp($10)"))
            cost+=10;
    }

    @Override
    //option for no topping
    public void onNothingSelected(AdapterView<?> adapterView)
    {
        toppingSelected = "No Topping";
    }
}