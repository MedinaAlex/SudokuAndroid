package com.example.alexm.sudoku;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by alexm on 03/02/2017.
 */

public class Dessin extends View implements View.OnTouchListener {

    final static int NBCASES = 9;

    int num =0;

    int[][] grille = null;

    public Dessin(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
        this.setOnTouchListener(this);

    }

    public void setGrille(int[][] grille){
        this.grille = grille;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        int x =(int)event.getX();
        int y =(int)event.getY();

        int xUp;
        int yUp;
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                for(int i=1;i<=9;i++){
                    if(x >= getWidth()/10*i-50 && x <=  getWidth()/10*i+50 && y >=  getWidth()+50 && y <=  getWidth()+150){
                        num = i;
                    }
                }
                Log.d("num", String.valueOf(num));
                break;

            case MotionEvent.ACTION_UP:
                xUp = x / (getWidth() /9);
                yUp = y / (getWidth() /9);
                if (xUp <9 && yUp <9){
                    grille[xUp][yUp] = num;
                    Log.d("num", String.valueOf(num));
                }

                invalidate();

                Log.d("xUp", String.valueOf(xUp));
                Log.d("yUp", String.valueOf(yUp));
                num = 0;
                break;
        }
        this.invalidate();

        if(check()){
            Toast toast= new Toast(getContext());
            toast.makeText(getContext(), "fini", Toast.LENGTH_LONG);
            toast.show();
        }
        return true;

    }

    public boolean check(){
        Boolean retour = true;
        Set<Integer> monSetLigne;
        Set<Integer> monSetColonne;

        for (int i = 0; i < 9; i++){
            monSetLigne = new HashSet<>();
            monSetColonne = new HashSet<>();
            for (int j = 0; j < 9; j++){
                if(grille[i][j] != 0){
                    monSetLigne.add(grille[i][j]);
                    monSetColonne.add(grille[j][i]);
                }
            }
            if (monSetLigne.size() != 8 ||monSetColonne.size() != 8){
                retour = false;
                break;
            }
        }

        return retour;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paintLigne = new Paint();

        for(int i = 1; i<= NBCASES; i++){

            if(i % 3 == 0){
                paintLigne.setStrokeWidth(6);
            }
            else{
                paintLigne.setStrokeWidth(3);
            }
            canvas.drawLine(getWidth() / 9 *i, 0, getWidth() / 9 * i, getWidth(), paintLigne);
            canvas.drawLine(0, getWidth()/ 9 *i, getWidth(), getWidth() / 9 * i, paintLigne);
        }

        Paint paintRect = new Paint();
        paintRect.setStyle(Paint.Style.STROKE);
        paintLigne.setStrokeWidth(3);

        Paint paintString = new Paint();
        paintString.setTextSize(80);

        for (int i = 1; i<= NBCASES; i++){
            canvas.drawRect( getWidth() / 10 *i - 50, getWidth() + 50,  getWidth() / 10 * i + 50, getWidth()+ 150, paintRect);
            canvas.drawText(String.valueOf(i), getWidth() / 10 *i - 25, getWidth() + 125, paintString);
        }

        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                if(grille[i][j] != 0){
                    canvas.drawText(String.valueOf(grille[i][j]), getWidth() / 9 *i +65, getWidth() / 9 *j +85, paintString);
                }
            }
        }
    }


}