package com.example.leyohm;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Resistencia extends AppCompatActivity {

    private EditText volta,cor;
    private Button calcular;
    private TextView resultado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_resistencia);

        volta = findViewById(R.id.volt);
        cor = findViewById(R.id.corr);
        resultado = findViewById(R.id.resul);
        calcular = findViewById(R.id.calcu);
        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double volt = Double.parseDouble(volta.getText().toString());
                double corri = Double.parseDouble(cor.getText().toString());
                double resistencia = volt / corri;
                resultado.setText("La Resistencia es: " + resistencia + " R");
            }
        });
    }
}