package com.example.foodorder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText etPrice;
    private CheckBox cbSeniorDiscount;
    private TextView tvTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etPrice = findViewById(R.id.etPrice);
        cbSeniorDiscount = findViewById(R.id.cbSeniorDiscount);
        tvTotal = findViewById(R.id.tvTotal);

        // Listeners
        etPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calculateTotal();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        cbSeniorDiscount.setOnCheckedChangeListener((buttonView, isChecked) -> calculateTotal());
    }

    private void calculateTotal() {
        String priceStr = etPrice.getText().toString();
        if (priceStr.isEmpty()) {
            tvTotal.setText("Total a pagar: B/. 0.00");
            return;
        }

        double price = Double.parseDouble(priceStr);
        double total;

        if (cbSeniorDiscount.isChecked()) {
            total = price * 0.75; // Aplicar 25% de descuento
        } else {
            total = price; // Sin descuento
        }

        tvTotal.setText(String.format("Total a pagar: B/. %.2f", total));
    }
}
