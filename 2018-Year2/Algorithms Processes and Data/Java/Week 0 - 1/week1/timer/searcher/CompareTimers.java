package searcher;

import java.util.ArrayList;

/**
 * @author Domantas Giedraitis (student id: u1757704 )
 * @version 1
 * @since 2018-11-10
 */
public class CompareTimers {
    public static void main(String[] args) {
        CleverSearcherTimer cleverTimer = new CleverSearcherTimer(null);
        SimpleSearcherTimer simpleTimer = new SimpleSearcherTimer(null);

        ArrayList<Double> cleverTimerTimes;
        ArrayList<Double> simpleTimerTimes;

        cleverTimerTimes = cleverTimer.timingSequenceToArray(); // get timers for clever searcher
        simpleTimerTimes = simpleTimer.timingSequenceToArray(); // get timers for simple searcher

        // check which is faster
        for(int i = 0 ; i < simpleTimerTimes.size()-1; i++) {
            System.out.print("Clever: " + cleverTimerTimes.get(i) + " | Simple: " + simpleTimerTimes.get(i));
            if(cleverTimerTimes.get(i) < simpleTimerTimes.get(i)) {
                // calculate how many times one searcher is faster then other one
                double faster = Math.round(simpleTimerTimes.get(i)/ cleverTimerTimes.get(i));
                System.out.println(" | Clever is faster  " + faster + " times");
            }
            else {
                // calculate how many times one searcher is faster then other one
                double faster = Math.round(cleverTimerTimes.get(i)/ simpleTimerTimes.get(i));
                System.out.println(" | Simple is faster! " + faster + " times");
            }
        }

    }
}
