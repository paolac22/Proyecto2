package com.example.leyohm3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Spinner opciones;
    private Button siguiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        siguiente = findViewById(R.id.siguiente);
        opciones = findViewById(R.id.opciones);
        String[] seleccion = {"--Seleccionar", "Voltaje", "Corriente", "Resistencia"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, seleccion);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        opciones.setAdapter(adapter);

        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String select = opciones.getSelectedItem().toString();
                if (select.equals("Voltaje")) {
                    Intent intent = new Intent(MainActivity.this, Voltaje.class);
                    startActivity(intent);
                } else if (select.equals("Corriente")) {
                    Intent intent = new Intent(MainActivity.this, Corriente.class);
                    startActivity(intent);
                } else if (select.equals("Resistencia")) {
                    Intent intent = new Intent(MainActivity.this, Resistencia.class);
                    startActivity(intent);
                }
            }
        });
    }
}
