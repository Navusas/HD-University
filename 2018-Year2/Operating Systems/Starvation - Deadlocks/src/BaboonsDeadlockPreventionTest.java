import java.util.concurrent.Semaphore;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Domantas Giedraitis (student id: u1757704 )
 * @version 1
 * @since 2018-12-07
 */
class BaboonsDeadlockPreventionTest {

    private Semaphore rope;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        rope = new Semaphore(1);

    }
}