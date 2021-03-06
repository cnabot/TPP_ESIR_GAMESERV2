package com.mycompany.myapp.web.rest.hex;

import java.util.ArrayList;
import java.util.Random;

import com.mycompany.myapp.web.rest.hex.Main.Fill;

public class RandomAI implements AI {
    private int size;
    private Fill myFill;
    private Fill oppFill;

    RandomAI(int size, Fill myFill) {
        this.size = size;
        this.myFill = myFill;
        oppFill = myFill == Fill.r ? Fill.b : Fill.r;
    }

    @Override
    public Case chooseCaseToFill(Case[][] table) {
        ArrayList<Case> movelist = moveList(table, myFill);
        Random rand = new Random();
        int m = rand.nextInt(movelist.size());
        play(table, movelist.get(m), myFill);
        return movelist.get(m);
    }

    /**
     * 
     * @param table
     * @param move
     * @param fill
     */
    private void play(Case[][] table, Case move, Fill fill) {
        if (move != null) {
            table[move.row()][move.col()].fill(fill);
        }
    }

    /**
     * 
     * @param table
     * @param fill
     * @return
     */
    private ArrayList<Case> moveList(Case[][] table, Fill fill) {
        ArrayList<Case> moveList = new ArrayList<>();
        if (fill == myFill) {
            for (int i = 0; i < table.length - 1; i++) {
                for (int j = 0; j < table.length; j++) {
                    if (table[i][j].filledBy() == Fill.blank) {
                        moveList.add(table[i][j]);
                    }
                }
            }
        }
        return moveList;
    }
}
