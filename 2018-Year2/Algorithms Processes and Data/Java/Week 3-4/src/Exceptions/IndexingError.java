package Exceptions;

/**
 *  Exception handles all Indexing  (Index Out Of Bounds) Exceptions
 *
 * @author          Domantas Giedraitis (student id: u1757704 )
 * @version         1
 * @since           2018-10-24
 */

public class IndexingError extends Exception {

    /**
    * Constructor extending the Exception without arguments
    */
    public IndexingError() { super(); };

    /**
     * Constructor extending the Exception with message argument
     *
     * @param message   Throws exception and specifies desired message in console.
     */
    public IndexingError(String message) { super(message); };
}
