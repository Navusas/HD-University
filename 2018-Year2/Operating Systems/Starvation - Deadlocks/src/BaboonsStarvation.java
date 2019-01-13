import java.util.concurrent.Semaphore;

/**
 * @author Domantas Giedraitis (student id: u1757704 )
 * @version 1
 * @since 2018-12-07
 */

public class BaboonsStarvation {
    public static final int MAX_PERMITS = 3;
    private static final int NB_BABOONS = 5;

    public static void main(String[] args) {
        System.out.println("Start");

        Semaphore sem = new Semaphore(MAX_PERMITS); // resource to be used by both baboons

        /*threads representing baboons, sharing the same memory
         * 0 - random time. It can be changed iwth any desired number in milliseconds. ( for sleeping)
         * */
        Baboon baboons[] = new Baboon[NB_BABOONS * 2];
        for (int i = 0; i < NB_BABOONS; i++) {
            baboons[i] = new Baboon(i, "East", sem, 1000,3);
            baboons[i + NB_BABOONS] = new Baboon(i, "West", sem, 1000,3);
        }
        for (int i = 0; i < NB_BABOONS * 2; i++) {
            baboons[i].start();
        }

        for (int i = 0; i < NB_BABOONS * 2; i++) {
            try {
                baboons[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
