/**
 * @description: Implements Genetic algorithm as a controller for PacMan_v6.2.
 * NOTE: This is part of a comparative study on performance of algorithms as a game controller. Evolutionary algorithms
 *  are pretty broad in their selection of successful population and mutation implementation. This particular implementation
 *  performed quite randomly achieving a great score on some runs but a bad score on others.
 * @author: Noel David Lobo
 * @date: 11/05/2016
 */
package examples.StarterPacMan;

import pacman.controllers.Controller;
import pacman.game.Game;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.PriorityQueue;
import java.util.Random;

import static pacman.game.Constants.GHOST;
import static pacman.game.Constants.MOVE;

public class GeneticAlgoPacman extends Controller<MOVE>{
    Controller<EnumMap<GHOST, MOVE>> ghostMoves;
    private int numGenerations = 10;
    private int populationSize = 50;
    private Game game;

    public GeneticAlgoPacman(Controller<EnumMap<GHOST, MOVE>> ghostMoves) {
        this.ghostMoves = ghostMoves;
    }

    public MOVE getMove(Game game, long timeDue) {
        this.game = game;
        PriorityQueue<Genome1> population = new PriorityQueue<>();
        PriorityQueue<Genome1> compete = new PriorityQueue<>();

        for(int i = 0 ; i < populationSize; i++){
            population.add(new Genome1(game.copy(), getGenes(20), 0));
        }

        for(int x = 0; x<numGenerations; x++) {
            for (Genome1 gen : population) {
                ArrayList<MOVE> moves = gen.genesequence;
                Game copy = gen.game.copy();

                for (MOVE move : moves)
                    copy.advanceGame(move, ghostMoves.getMove(copy.copy(), -1));

                gen.fitness = copy.getScore();
                compete.add(gen);
            }
            population.clear();

            for (int i = 0; i < populationSize/4; i++) {
                Genome1 mother = compete.remove();
                Genome1 father = compete.remove();
                population.add(mother);
                population.add(father);
                population.add(crossover(mother, father));
            }

            while (!compete.isEmpty())
                population.add(compete.remove());
        }

        return population.remove().genesequence.remove(0);
    }

    public Genome1 crossover(Genome1 m, Genome1 d){
        Random random = new Random();
        int divide = random.nextInt(m.genesequence.size());
        ArrayList<MOVE> childGenes = new ArrayList<>();

        for(int x = 0; x< divide; x++)
            childGenes.add(m.genesequence.get(x));

        for(int x = divide; x<m.genesequence.size(); x++)
            childGenes.add(d.genesequence.get(x));

        Genome1 child = new Genome1(this.game,childGenes,0);

        return child;
    }

    public ArrayList<MOVE> getGenes(int length) {
        ArrayList<MOVE> moves = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(5);
            moves.add(MOVE.values()[index]);
        }
        return moves;
    }
}
