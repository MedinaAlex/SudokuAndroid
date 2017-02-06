package com.example.alexm.sudoku;

/**
 * Created by alexm on 02/02/2017.
 */

public class VGrille  {

    private int level;
    private int num;
    private int done;
    private String grille;

    /**
     * Constructeur d'une VGrille
     * @param level le niveau de difficulté
     * @param num le numéro de la grille
     * @param done le pourcentage effectué
     * @param grille la grille de Sudoku
     */
    public VGrille(int level, int num, int done, String grille) {
        this.level = level;
        this.num = num;
        this.done = done;
        this.grille = grille;
    }

    public int getLevel() {
        return level;
    }

    public String getGrille() {
        return grille;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getDone() {
        return done;
    }

    public void setDone(int done) {
        this.done = done;
    }


}
