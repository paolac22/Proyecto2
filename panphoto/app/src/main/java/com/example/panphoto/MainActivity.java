package com.example.panphoto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etAmount;
    private Spinner spinnerPaymentMethod;
    private RadioGroup rgCreditOptions;
    private RadioButton rbOneYear, rbTwoYears;
    private CheckBox cbHomeDelivery;
    private Button btnCalculate;

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
        btnCalculate = findViewById(R.id.btnCalculate);

        // Configurar el Spinner
        String[] paymentMethods = {"Seleccione forma de pago", "Contado", "Crédito"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, paymentMethods);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPaymentMethod.setAdapter(adapter);

        // Ocultar opciones de crédito inicialmente
        rgCreditOptions.setVisibility(View.GONE);

        // Mostrar opciones de crédito solo si se selecciona "Crédito"
        spinnerPaymentMethod.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 2) { // Índice 2 corresponde a "Crédito"
                    rgCreditOptions.setVisibility(View.VISIBLE);
                } else {
                    rgCreditOptions.clearCheck();
                    rgCreditOptions.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                rgCreditOptions.setVisibility(View.GONE);
            }
        });

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateTotal();
            }
        });
    }

    private void calculateTotal() {
        String amountStr = etAmount.getText().toString();
        if (amountStr.isEmpty()) {
            Toast.makeText(this, "Please enter the amount", Toast.LENGTH_SHORT).show();
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
            } else {
                Toast.makeText(this, "Please select a credit option", Toast.LENGTH_SHORT).show();
                return;
            }
        } else {
            Toast.makeText(this, "Please select a payment method", Toast.LENGTH_SHORT).show();
            return;
        }

        if (cbHomeDelivery.isChecked()) {
            total += 25.00; // Costo de entrega a domicilio
        }

        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
        intent.putExtra("total", total);
        startActivity(intent);
    }
}
