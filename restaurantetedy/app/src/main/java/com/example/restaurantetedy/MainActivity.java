package com.example.restaurantetedy;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private CheckBox cb1, cb2, cb3;
    private TextView total;
    private Button agregar;
    float sum;
    private String resu="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        cb1 = findViewById(R.id.cb1);
        cb2 = findViewById(R.id.cb2);
        cb3 = findViewById(R.id.cb3);
        total = findViewById(R.id.total);
        agregar = findViewById(R.id.button);

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sum=0;
                if (cb1.isChecked()==true) {sum = (float) (sum + 3.99);}
                if (cb2.isChecked()==true) { sum = (float) (sum + 1.5);}
                if (cb3.isChecked()==true) { sum = sum + 1;}
                resu=" Total: "+ sum;
                total.setText(resu);
            }
        });
    }
}