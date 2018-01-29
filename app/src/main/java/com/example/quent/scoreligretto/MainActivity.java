package com.example.quent.scoreligretto;

import android.database.MatrixCursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button ajouterJoueur;
        final EditText nomJoueur;

        // Example of a call to a native method
        /*TextView tv = (TextView) findViewById(R.id.sample_text);
        tv.setText(stringFromJNI());*/
        // Définition des colonnes
// NB : SimpleCursorAdapter a besoin obligatoirement d'un ID nommé "_id"
        String[] columns = new String[] { "_id", "col1", "col2" };
        final ArrayList<String> nom = new ArrayList<String>();
        ajouterJoueur = (Button) findViewById(R.id.ajouter);
        nomJoueur = (EditText) findViewById(R.id.text_nom);

// Définition des données du tableau
// les lignes ci-dessous ont pour seul but de simuler
// un objet de type Cursor pour le passer au SimpleCursorAdapter.
// Si vos données sont issues d'une base SQLite,
// utilisez votre "cursor" au lieu du "matrixCursor"
        final MatrixCursor matrixCursor= new MatrixCursor(columns);

        ajouterJoueur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(verifnom(nom,nomJoueur.getText().toString()) ){
                    nom.add(nomJoueur.getText().toString());
                    refresh(matrixCursor,nom);
                    nomJoueur.setText("");
                }
            }
        });
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    public void refresh(MatrixCursor matrixCursor,ArrayList<String>nom){
        startManagingCursor(matrixCursor);

        matrixCursor.addRow(new Object[] { nom.size(),nom.size(), nom.get(nom.size()-1)});


// on prendra les données des colonnes 1 et 2...
        String[] from = new String[] {"col1", "col2"};

// ...pour les placer dans les TextView définis dans "row_item.xml"
        int[] to = new int[] { R.id.textViewCol1, R.id.textViewCol2};

// création de l'objet SimpleCursorAdapter...
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.row_item, matrixCursor, from, to, 0);

// ...qui va remplir l'objet ListView
        ListView lv = (ListView) findViewById(R.id.lv);
        lv.setAdapter(adapter);
    }

    public boolean verifnom(ArrayList<String> noms,String nomTest){
        for (String nom: noms
             ) {
            System.out.println("nom " + nom + " nom 2 " + nomTest);
            if(nomTest.equals(nom))
                return false;
        }
        return true;
    }


}
