package com.example.leyohm_2;

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
        
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Intent intent;
                if (checkedId == R.id.voltaje){
                    intent = new Intent(MainActivity.this, Voltaje.class);
                } else if (checkedId == R.id.corriente) {
                    intent = new Intent(MainActivity.this, Corriente.class);
                } else if (checkedId == R.id.resistencia) {
                    intent = new Intent(MainActivity.this, Resistencia.class);
                } else {
                    return;
                }
                startActivity(intent);
            }
        });
    }
}