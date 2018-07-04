package com.example.quent.scoreligretto;

import android.content.Context;
import android.content.Intent;
import android.database.MatrixCursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Intent intent;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        this.setTitle("Accueil");

        Button btnCreer = (Button) findViewById(R.id.buttonCreer);
        Button btnHisto = (Button) findViewById(R.id.buttonHisto);

        btnCreer.setOnClickListener(this);
        btnHisto.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.buttonCreer){
            intent = new Intent(context, Joueur.class);
        }
        if(v.getId() == R.id.buttonHisto){
            intent = new Intent(context, Historique.class);
        }
        context.startActivity(intent);
    }
}
