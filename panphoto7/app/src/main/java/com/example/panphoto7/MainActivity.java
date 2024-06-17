package com.example.panphoto7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.text.Editable;
import android.text.TextWatcher;

public class MainActivity extends AppCompatActivity {

    private EditText etAmount;
    private Spinner spinnerPaymentMethod;
    private RadioGroup rgCreditOptions;
    private RadioButton rbOneYear, rbTwoYears;
    private CheckBox cbHomeDelivery;
    private TextView tvTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etAmount = findViewById(R.id.etAmount);
        spinnerPaymentMethod = findViewById(R.id.spinnerPaymentMethod);
        rgCreditOptions = findViewById(R.id.rgCreditOptions);
        rbOneYear = findViewById(R.id.rbOneYear);
        rbTwoYears = findViewById(R.id.rbTwoYears);
        cbHomeDelivery = findViewById(R.id.cbHomeDelivery);
        tvTotal = findViewById(R.id.tvTotal);

        // Configurar el Spinner
        String[] paymentMethods = {"Seleccione forma de pago", "Contado", "Crédito"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, paymentMethods);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPaymentMethod.setAdapter(adapter);

        // Ocultar opciones de crédito inicialmente
        rgCreditOptions.setVisibility(View.GONE);

        // Listeners
        etAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calculateTotal();
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });

        spinnerPaymentMethod.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 2) { // Índice 2 corresponde a "Crédito"
                    rgCreditOptions.setVisibility(View.VISIBLE);
                } else {
                    rgCreditOptions.clearCheck();
                    rgCreditOptions.setVisibility(View.GONE);
                }
                calculateTotal();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                rgCreditOptions.setVisibility(View.GONE);
            }
        });

        rgCreditOptions.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                calculateTotal();
            }
        });

        cbHomeDelivery.setOnCheckedChangeListener((buttonView, isChecked) -> calculateTotal());
    }

    private void calculateTotal() {
        String amountStr = etAmount.getText().toString();
        if (amountStr.isEmpty()) {
            tvTotal.setText("Total: B/. 0.00");
            return;
        }

        double amount = Double.parseDouble(amountStr);
        double total = 0.0;

        String selectedPaymentMethod = spinnerPaymentMethod.getSelectedItem().toString();
        if (selectedPaymentMethod.equals("Contado")) {
            total = amount + (amount * 0.07); // 7% adicional
        } else if (selectedPaymentMethod.equals("Crédito")) {
            if (rbOneYear.isChecked() || rbTwoYears.isChecked()) {
                total = amount + (amount * 0.12); // 12% impuesto
            }
        }

        if (cbHomeDelivery.isChecked()) {
            total += 25.00; // Costo de entrega a domicilio
        }

        tvTotal.setText(String.format("Total: B/. %.2f", total));
    }
}

