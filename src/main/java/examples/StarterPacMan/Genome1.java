package examples.StarterPacMan;

import pacman.game.Constants;
import pacman.game.Game;

import java.util.ArrayList;

/**
 * Created by noel on 10/25/16.
 */
public class Genome1 implements Comparable<Genome1> {
    public Game game;
    public ArrayList<Constants.MOVE> genesequence;
    public float fitness;

    public Genome1(Game game , ArrayList<Constants.MOVE> genesequence, int fitness){
        this.game = game;
        this.genesequence = genesequence;
        this.fitness = fitness;
    }

    public int compareTo(Genome1 other) {
        return Float.compare(this.fitness, other.fitness);
    }

}