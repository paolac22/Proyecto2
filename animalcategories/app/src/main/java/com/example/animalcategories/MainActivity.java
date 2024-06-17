package com.example.animalcategories;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerCategory;
    private Spinner spinnerExamples;
    private TextView tvSelectedExample;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerCategory = findViewById(R.id.spinnerCategory);
        spinnerExamples = findViewById(R.id.spinnerExamples);
        tvSelectedExample = findViewById(R.id.tvSelectedExample);

        // Datos para el Spinner de Categorías
        String[] categories = {"Seleccione categoría", "Vertebrados", "Invertebrados"};
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(categoryAdapter);

        // Listeners
        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 1) { // Vertebrados
                    updateExamplesSpinner(new String[]{"Perro", "Gato", "Pez", "Águila", "Serpiente"});
                } else if (position == 2) { // Invertebrados
                    updateExamplesSpinner(new String[]{"Mariposa", "Araña", "Cangrejo", "Gusano", "Medusa"});
                } else {
                    spinnerExamples.setAdapter(null);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        spinnerExamples.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedExample = (String) spinnerExamples.getSelectedItem();
                tvSelectedExample.setText("Selected Example: " + selectedExample);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                tvSelectedExample.setText("Selected Example:");
            }
        });
    }

    private void updateExamplesSpinner(String[] examples) {
        ArrayAdapter<String> exampleAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, examples);
        exampleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerExamples.setAdapter(exampleAdapter);
    }
}
