package examples.StarterPacMan;

import pacman.controllers.Controller;
import pacman.controllers.examples.StarterGhosts;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by QuanhaoLi on 10/19/16.
 */
public class Astar extends Controller<MOVE> {

    private final Queue<NodeTree> games = new LinkedList<NodeTree>();
    private final Queue<Double> scores = new LinkedList<Double>();

    private final Stack<MOVE> moves = new Stack<MOVE>();
    private final Stack<NodeTree> possibleMoves = new Stack<NodeTree>();

    private final StarterGhosts ghosts = new StarterGhosts();

    private double bestScore = 0;
    private Game bestGame = null;

    public void aStarFill (Game game, int maxDepth) {

        //Initialize value
        Game copy = game.copy();
        NodeTree head = new NodeTree();
        head.currentGame = copy;
        head.previousGame = null;
        double initScore = 0;

        games.add(head);
        scores.add(initScore);

        while (!games.isEmpty()){
            NodeTree currentNodeTree = games.remove();
            Game currentGame = currentNodeTree.currentGame;
            double newScore = scores.remove();

            if (newScore > bestScore) {
                bestScore = newScore;
                bestGame = currentGame;
                possibleMoves.push(currentNodeTree);

            }

            if (currentGame.gameOver()){
                if (currentGame.getNumberOfActivePills() == 0 && currentGame.getNumberOfActivePowerPills() == 0){
                    break;
                }
            } else {
                if (currentNodeTree.depth < maxDepth) {

                    LinkedList<NodeTree> n = new LinkedList<NodeTree>();
                    LinkedList<Double> d = new LinkedList<Double>();

                    for (MOVE move : currentGame.getPossibleMoves(currentGame.getPacmanCurrentNodeIndex())) {
                        Game newGame = currentGame.copy();
                        NodeTree newNode = new NodeTree();
                        newNode.previousGame = currentGame;
                        newNode.depth = currentNodeTree.depth +1;
                        newGame.advanceGame(move, ghosts.getMove());
                        newNode.currentGame = newGame;
                        n.add(newNode);
                        d.add((double)newGame.getScore() + Heuristic.heuristicFuncForNext(newGame));

                    }

                    while (!n.isEmpty()) {
                        int high = -1;
                        int highIndex = -1;
                        for (int i = 0; i < n.size(); i++) {
                            if (n.get(i).currentGame.getScore() > high) {
                                high = n.get(i).currentGame.getScore();
                                highIndex = i;
                            }
                        }
                        games.add(n.get(highIndex));
                        scores.add(d.get(highIndex));
                        n.remove(highIndex);
                        d.remove(highIndex);
                    }

                }
            }

        }

        while (!possibleMoves.isEmpty()) {
            if (possibleMoves.peek().currentGame == bestGame) {
                moves.push(possibleMoves.peek().currentGame.getPacmanLastMoveMade());
                bestGame = possibleMoves.peek().previousGame;
            }
            possibleMoves.pop();
        }

    }

    @Override
    public MOVE getMove(Game game, long timeDue) {
        if (moves.isEmpty()) aStarFill(game, 10);
        if (moves.isEmpty()) return MOVE.NEUTRAL;
        return moves.pop();
    }

}


