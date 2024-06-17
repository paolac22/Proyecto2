package com.example.panphoto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tvResult = findViewById(R.id.tvResult);

        double total = getIntent().getDoubleExtra("total", 0.0);
        tvResult.setText("Total a pagar: B/. " + String.format("%.2f", total));
    }
}
