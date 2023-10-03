package com.example.ejercicio2;

import static com.example.ejercicio2.R.*;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    static ArrayList<Record> records;
    int contador = 0;
    int numeroAleatorio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        final Button button = findViewById(id.button);
        final Button ranking = findViewById(id.button2);
        final EditText numero = findViewById(id.number);
        TextView texto = findViewById(id.textView2);
        TextView textViewScrollable = findViewById(id.textView3);
        TextView intentos = findViewById(id.textView4);
        records = new ArrayList<Record>();
        // Afegim alguns exemples
        records.add( new Record(33,"Manolo") );
        records.add( new Record(12,"Pepe") );
        records.add( new Record(42,"Laura") );
        generarNumeroAleatorio();
        Intent intent = new Intent(this, RankingActivity.class);


        button.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            public void onClick(View v) {

                try {

                    String texto1 = numero.getText().toString();
                    int numero1 = Integer.parseInt(texto1);
                    if (numeroAleatorio==numero1){
                        contador++;
                        mostrarDialogoAdivinanza();
                        generarNumeroAleatorio();
                        contador=0;
                    }else if(numeroAleatorio>numero1){
                        contador++;
                        intentos.setText("Intentos: "+contador);
                        //Toast.makeText(MainActivity.this, "El numero es mayor", Toast.LENGTH_SHORT).show();
                        textViewScrollable.append("El numero es mayor que: "+texto1+"\n");
                    }else if (numeroAleatorio<numero1){
                        contador++;
                        intentos.setText("Intentos: "+contador);
                        //Toast.makeText(MainActivity.this, "El numero es menor", Toast.LENGTH_SHORT).show();
                        textViewScrollable.append("El numero es menor que: "+texto1+"\n");
                    }
                }catch(NumberFormatException e){
                    Toast.makeText(MainActivity.this, "Ingrese un numero.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        ranking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
    }

    private void mostrarDialogoAdivinanza() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("¡Adivinaste!");
        builder.setMessage("¡Has adivinado el número con un total de "+contador+" intentos"+"\nQuieres guardar tu puntuacion?");
        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mostrarDialogoNombre();
            }
        });
        builder.setNegativeButton("No",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }

    public void mostrarDialogoNombre() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Introduce tu nombre");

        final EditText editTextName = new EditText(this);
        builder.setView(editTextName);

        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String nombre = editTextName.getText().toString();
                records.add(new Record(contador,nombre));
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Volver al juego", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    private void generarNumeroAleatorio() {
        // Genera un número aleatorio entre 1 y 100 (ajusta el rango según tus necesidades).
        Random random = new Random();
        //numeroAleatorio = random.nextInt(100) + 1;
        numeroAleatorio=10;
    }

    class Record {
        public int intents;
        public String nom;
        public Record (int intents, String nom){
            intents = this.intents;
            nom = this.nom;
        }
    }
}