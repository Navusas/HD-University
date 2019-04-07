package ferrocarrilesDeAmericaDelSur.railways;

import ferrocarrilesDeAmericaDelSur.errors.RailwaySystemError;
import ferrocarrilesDeAmericaDelSur.errors.SetUpError;
import ferrocarrilesDeAmericaDelSur.tools.Clock;
import ferrocarrilesDeAmericaDelSur.tools.Delay;

/**
 * An implementation of a railway.  The runTrain method, should, in collaboration with Peru's runTrain(), guarantee
 * safe joint operation of the railways.
 */
public class Bolivia extends Railway {
    /**
     * Change the parameters of the Delay constructor in the call of the superconstructor to
     * change the behaviour of this railway.
     *
     * @throws SetUpError if there is an error in setting up the delay.
     */
    public Bolivia() throws SetUpError {
        super(1, "Bolivia", new Delay(0.1, 0.3));
    }

    /**
     * Run the train on the railway.
     * This method currently does not provide any synchronisation to avoid two trains being in the pass at the same time.
     */

    public void runTrain() throws RailwaySystemError {
        Clock clock = getRailwaySystem().getClock();

        Basket boliviaBasket = getBasket(); // individual basket for Peru
        Basket turnBasket = getSharedBasket(); // shared basket to determine turns

        while (!clock.timeOut()) {
            choochoo(); // the non-critical section
            // i.e. signal that you want to cross the pass
            while (turnBasket.hasStone()) { // if the other railway's basket has a stone in it
                // i.e. the other railway wants to cross the pass
                if(boliviaBasket.hasStone()) { // check if it is other persons turn
                    turnBasket.putStone();
                    while(turnBasket.hasStone()) {// wait for your turn
                        siesta(); // have a snack
                    };
                    getBasket().putStone(this); // put the stone back in your basket
                    // because you still want to cross the pass
                }
            }
            turnBasket.putStone();
            crossPass(); // the critical section
            turnBasket.takeStone();


            if(boliviaBasket.hasStone()) {
                boliviaBasket.takeStone();
            }
            // because you have now successfully crossed the pass
            // and do not (currently) want to cross it again
        }
    }

}