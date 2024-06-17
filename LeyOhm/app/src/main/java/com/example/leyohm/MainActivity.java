package com.example.leyohm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private RadioGroup rg;
    private RadioButton volt, corrien, resi;
    private Button cal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        volt = findViewById(R.id.voltaje);
        corrien = findViewById(R.id.corriente);
        resi = findViewById(R.id.resistencia);
        rg = findViewById(R.id.rg);
        cal = findViewById(R.id.calc);

        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = rg.getCheckedRadioButtonId();

                if (selectedId == R.id.voltaje) {
                    Intent intent = new Intent(MainActivity.this, Voltaje.class);
                    startActivity(intent);
                }
                else if (selectedId == R.id.corriente) {
                    Intent intent = new Intent(MainActivity.this, Corriente.class);
                    startActivity(intent);
                }
                else if (selectedId == R.id.resistencia) {
                    Intent intent = new Intent(MainActivity.this, Resistencia.class);
                    startActivity(intent);
                }
            }
        });
    }
}