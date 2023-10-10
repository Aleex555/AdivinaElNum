package com.example.ejercicio2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.icu.text.AlphabeticIndex;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class RankingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        TableLayout tableLayout = new TableLayout(this);
        ArrayList<MainActivity.Record> records = MainActivity.getRecords();
        final Button volverr1 = findViewById(R.id.button4);

        // Crear filas y celdas para cada registro en la lista
        for (MainActivity.Record record : records) {
            TableRow tableRow = new TableRow(this);

            TextView nombreTextView = new TextView(this);
            nombreTextView.setText(record.getNom());

            TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT
            );
            layoutParams.setMargins(0, 0, 100, 0);
            nombreTextView.setLayoutParams(layoutParams);

            TextView intentosTextView = new TextView(this);
            intentosTextView.setText(String.valueOf(record.getIntents()));

            // Agregar las celdas al TableRow
            tableRow.addView(nombreTextView);
            tableRow.addView(intentosTextView);

            // Agregar la fila al TableLayout
            tableLayout.addView(tableRow);
        }
        Button volverButton = new Button(this);
        volverButton.setText("Volver");
        volverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Agregar el botón de "Volver" al TableLayout (como una nueva fila)
        TableRow buttonRow = new TableRow(this);
        buttonRow.addView(volverButton);
        tableLayout.addView(buttonRow);
        setContentView(tableLayout);
    }
}