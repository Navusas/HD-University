package ferrocarrilesDeAmericaDelSur.railways;

/**
 * @author Domantas Giedraitis (student id: u1757704 )
 * @version 1
 * @since 2019-02-25
 */
public class Priority {
    private static int turn; // variable to determine who's turn is now to pass the 'critical control'
    // 0 - Peru / 1 - Basket
    private int limit; // amount of threads will be running. Required only when using increaseTurn() function.

    /**
     * Setter for Priority class.
     *
     * @param startTurn describe which one goes first
     */
    Priority(int startTurn) {
        this.turn = turn;
    }

    /**
     * Sets start point at 0 by default.
     */
    Priority() {
        turn = 0;
        limit = 0;
    }

    /**
     * Checks whether current turn is for given id
     *
     * @param id to be checked
     * @return true if it is given id turn
     */
    public boolean checkTurn (int id) {
        return turn == id;
    }

    /**
     * Returns turn variable
     *
     * @return value to show who's turn is it now
     */
    public int getTurn() {
        return turn;
    }

    /**
     * Amend turn manually
     *
     * @param id value will change current turn value
     */
    public void setTurn(int id) {
        turn = id;
    }

    /**
     * increase turn by 1 if limit has been given
     * @throws NullPointerException if limit has not been set or set to 0
     */
    public void increaseTurn() throws NullPointerException {
        if(limit <= 0) throw new NullPointerException("Limit has not been set! ");
        else if((turn+1) < limit) turn++;
        else turn = 0;
    }
}
