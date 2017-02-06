package com.example.alexm.sudoku;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by alexm on 03/02/2017.
 */



public class Grille extends Activity {

    int[][] grille = new int[9][9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grille);

        String maGrille = (String) this.getIntent().getExtras().get("grille");
        int cpt = 0;
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                grille[j][i] = Integer.parseInt(""+maGrille.charAt(cpt));
                cpt++;

            }
        }

        Dessin dessin = (Dessin) findViewById(R.id.Dessin);

        dessin.setGrille(grille);

    }
}
