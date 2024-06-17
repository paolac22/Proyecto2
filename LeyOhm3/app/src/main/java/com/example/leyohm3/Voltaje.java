package com.example.leyohm3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Voltaje extends AppCompatActivity {

    private EditText c, resis;
    private Button calcular;
    private TextView resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_voltaje);

        c = findViewById(R.id.i);
        resis = findViewById(R.id.r);
        resultado = findViewById(R.id.resultado);
        calcular = findViewById(R.id.calc);

        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double corriente = Double.parseDouble(c.getText().toString());
                double resistencia = Double.parseDouble(resis.getText().toString());
                double voltaje = corriente * resistencia;
                resultado.setText("El Voltaje es: " + voltaje + " V");
            }
        });

    }
}