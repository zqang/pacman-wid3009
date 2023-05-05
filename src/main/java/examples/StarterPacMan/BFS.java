package examples.StarterPacMan;

import pacman.controllers.Controller;
import pacman.controllers.examples.StarterGhosts;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by QuanhaoLi on 10/17/16.
 */
public class BFS extends Controller<MOVE>{

    private Stack<NodeTree> possibleMoves = new Stack<NodeTree>();
    public Queue<Game> games = new LinkedList<Game>();
    public Stack<MOVE> bestMoves = new Stack<MOVE>();

    private StarterGhosts ghosts = new StarterGhosts();
    private NodeTree nodeTree = new NodeTree();

    public Game bestGameState;
    public int bestScore;

    private void BFSRecursive (Game game) {

        for (MOVE move : game.getPossibleMoves(game.getPacmanCurrentNodeIndex())) {

            Game copy = game.copy();
            nodeTree.previousGame = copy;
            copy.advanceGame(move, ghosts.getMove());

            games.add(copy);

            // Handle game over
            // Win or lose

            if (copy.gameOver()) {
                if (copy.getNumberOfActivePills() == 0 && copy.getNumberOfActivePowerPills() == 0) {
                    nodeTree.currentGame = copy;
                    bestScore = copy.getScore();
                    bestGameState = copy;
                    possibleMoves.push(nodeTree);
                }
                return;
            }

            // update score

            if (copy.getScore() > bestScore) {
                nodeTree.currentGame = copy;
                possibleMoves.push(nodeTree);
                bestGameState = copy;
                bestScore = copy.getScore();
            }

        }

    }

    public void BFSDriver (Game game) {
        int numOfIter = 0;
        int numOfLoop = 0;
        bestGameState = game;
        Game copy = game.copy();
        games.add(copy);
        while (numOfIter < 20) {
            if (numOfLoop == 0){
                numOfIter++;
                numOfLoop = games.size();
            }
            BFSRecursive(games.remove());
            numOfLoop-=1;
        }

        while (!possibleMoves.isEmpty()) {
            if (bestGameState == possibleMoves.peek().currentGame) {
                bestMoves.push(possibleMoves.peek().currentGame.getPacmanLastMoveMade());
                bestGameState = possibleMoves.peek().previousGame;
            }
            possibleMoves.pop();
        }
        return;

    }

    @Override
    public MOVE getMove(Game game, long timeDue) {
        if (bestMoves.isEmpty()) BFSDriver(game);
        if (bestMoves.isEmpty()) return MOVE.NEUTRAL;
        return bestMoves.pop();
    }
}
