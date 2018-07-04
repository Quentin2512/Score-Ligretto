package com.example.quent.scoreligretto;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Joueur extends AppCompatActivity {

    Intent intent;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joueur);

        context = this;
        this.setTitle("Joueurs");
    }
}
