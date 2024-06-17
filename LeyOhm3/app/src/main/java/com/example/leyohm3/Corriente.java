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

public class Corriente extends AppCompatActivity {

    private EditText vol,r;
    private Button calcular;
    private TextView resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_corriente);

        vol = findViewById(R.id.voltaje);
        r = findViewById(R.id.resistencia);
        resultado = findViewById(R.id.result);
        calcular = findViewById(R.id.cal);
        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double volt = Double.parseDouble(vol.getText().toString());
                double res = Double.parseDouble(r.getText().toString());
                double corriente = volt / res;
                resultado.setText("La Corriente es: " + corriente + " I");
            }
        });

    }
}