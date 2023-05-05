
import examples.StarterGhostComm.Blinky;
import examples.StarterGhostComm.Inky;
import examples.StarterGhostComm.Pinky;
import examples.StarterGhostComm.Sue;
import examples.StarterPacMan.*;
import examples.demo.QLearner;
import examples.demo.QTable;
import pacman.Executor;
import pacman.controllers.Controller;
import pacman.controllers.IndividualGhostController;
import pacman.controllers.MASController;
import pacman.controllers.examples.StarterGhosts;
import pacman.controllers.examples.po.POCommGhosts;
import pacman.game.Constants.*;
import pacman.game.Game;
import pacman.game.internal.POType;
import pacman.game.util.Stats;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Random;

import static pacman.game.Constants.DELAY;


/**
 * Created by pwillic on 06/05/2016.
 */
public class Main {

    public static String reverseString(String str) {
        if (str == null || str.isEmpty())
            return str;
        char[] charAry = str.toCharArray();
        int left = 0;
        int right = charAry.length - 1;
        while (left < right) {
            char temp = charAry[left];
            charAry[left] = charAry[right];
            charAry[right] = temp;
            left++;
            right--;
        }
        return new String(charAry);
    }
    public static void main(String[] args) {
        String input = "abcd";
        String reversed = reverseString(input);
        System.out.println("Input: " + input);
        System.out.println("Reversed: " + reversed);


//        int numTrials=100;
//
//        Executor executor = new Executor.Builder()
//                .setVisual(true)
//                .setPacmanPO(false)
//                .setTickLimit(10000)
//                .setScaleFactor(2) // Increase game visual size
//                .setPOType(POType.RADIUS) // pacman sense objects around it in a radius wide fashion instead of straight line sights
//                .setSightLimit(5000) // The sight radius limit, set to maximum
//                .build();
//
//        EnumMap<GHOST, IndividualGhostController> controllers = new EnumMap<>(GHOST.class);
//
//        controllers.put(GHOST.INKY, new Inky());
//        controllers.put(GHOST.BLINKY, new Blinky());
//        controllers.put(GHOST.PINKY, new Pinky());
//        controllers.put(GHOST.SUE, new Sue());
//
//        MASController ghosts = new POCommGhosts(50);
//        executor.runGame(new QLearner(new QTable()), ghosts, 1); // delay=10; smaller delay for faster gameplay
        // executor.runGame(new CustomTreeSearchPacMan(), ghosts, 10);
//        Stats[] stats = executor.runExperiment(new TreeSearchPacMan(),ghosts, 10000, "test 1");
//        for (Stats stat : stats) {
//            System.out.println(stat.toString());
//        }


    }
}
