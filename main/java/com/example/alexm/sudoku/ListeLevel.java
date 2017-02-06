package com.example.alexm.sudoku;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Path;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.R.attr.level;

/**
 * Created by alexm on 02/02/2017.
 */

public class ListeLevel extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.liste_level);

        final ListView listGrille = (ListView) findViewById(R.id.listGrille);

        String level = (String) this.getIntent().getExtras().get("level");

        // Values généré selon le fichier de grilles
        final List<VGrille> grilles = new ArrayList();

        InputStream inputStream;
        if("1".equals(level)){
            inputStream = getResources().openRawResource(R.raw.fichier0);
        }else if("2".equals(level)){
            inputStream = getResources().openRawResource(R.raw.fichier1);
        }else{
            inputStream = getResources().openRawResource(R.raw.fichier2);
        }

        BufferedReader inputStreamReader = new BufferedReader(new InputStreamReader(inputStream));

        int i = 1;
        String line ="";

        do {
            try {
                line = inputStreamReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            grilles.add(new VGrille(Integer.parseInt(level), i, 0, line));
            i++;

        }while (line != null);


        monAdaptater adapter = new monAdaptater(this, grilles);
        listGrille.setAdapter(adapter);

        listGrille.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final VGrille grille = grilles.get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(ListeLevel.this);
                builder.setTitle("Information");
                builder.setMessage(grille.getNum() + ":" + grille.getDone());
                builder.setCancelable(false);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.setNegativeButton("Continue", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(ListeLevel.this, Grille.class);
                        intent.putExtra("grille", grille.getGrille());
                        startActivity(intent);
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }




}
