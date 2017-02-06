package com.example.alexm.sudoku;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonLvl1 = (Button) findViewById(R.id.buttonLVL1);
        Button buttonLvl2 = (Button) findViewById(R.id.buttonLVL2);
        Button buttonLvl3 = (Button) findViewById(R.id.buttonLVL3);

        buttonLvl1.setOnClickListener(this);
        buttonLvl2.setOnClickListener(this);
        buttonLvl3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, ListeLevel.class);

        Button b = (Button) v;
        // On passe le numéro correspondant au niveau de difficulté
        intent.putExtra("level",""+ b.getText().charAt(b.length()-1));
        startActivity(intent);
    }
}
