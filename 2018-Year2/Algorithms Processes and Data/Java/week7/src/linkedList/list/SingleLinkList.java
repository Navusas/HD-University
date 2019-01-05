package linkedList.list;

import linkedList.node.SingleLinkNode;

/**
 * SingleLinkList provides basic functionality to store, delete or get specified generic objects
 * to list. The NODES are being created with a reference scheme, which means:
 *      - The node knows everything about following nodes, but does not know anything about previous ones
 *      - There is only one-way connection meaning that from root node all nodes can be found, but
 *      - not visa-versa.
 *
 *
 * @param <T> the type of object to be stored in the list.
 *
 * @author Domantas Giedraitis (student id: u1757704 )
 * @version 2
 * @since 2018-12-27
 */
public class SingleLinkList<T>
        extends BasicList<SingleLinkNode<T>, T> implements List<T> {

    /**
     * Adds a given value and stores it to a Linked list at given index
     * checking if given index is within specifications, and then
     * looping through list consequently starting at root and checking if desired
     * element can be stored in a list.
     * <p>
     * new entry (link) during creation is given a reference to next link,
     * and then link (before new entry) is given reference to new entry.
     * <p>
     * In case list is empty, item is added as a root to a list
     * <p>
     * Throws exception if request is ambitious.
     *
     * @param index the index at which the new entry should be added.
     * @param value the value stored at givne index
     * @throws ListAccessError if index is negative, larger then list size.
     */
    @Override
    public void add(int index, T value) throws ListAccessError {

        /* confirm that index is non-negative and not larger then current list size
         * @see
         *       private void checkIndex(int index) throws ListAccessError
         * */
        checkIndex(index);
        /*
        * Store newly created node as a root if the list is empty
        */
        if (isEmpty()) {
            SingleLinkNode<T> newNode = new SingleLinkNode<T>(value); // create new node (with given value
            setRoot(newNode); // set the new node to be the root of all linked list
        } else {
            SingleLinkNode<T> nextLink = getRoot(); // Get root element
            int currentNodeIndex = 0; // to seek which node are we currently onto
            /*loop until end of list as
              last list element is going to have reference for next node equal to null */
            while (nextLink != null) {
                if ((nextLink == getRoot()) && (currentNodeIndex == index)) { // change root element, when trying to store value at position 0
                    SingleLinkNode<T> newNode = new SingleLinkNode<T>(value, getRoot()); // create new node holding reference to root
                    setRoot(newNode);  // override current root with a new node
                    break;
                }
                // For anything else. Once the node is found, add references to previous and new nodes
                else if ((currentNodeIndex + 1) == index) {
                    SingleLinkNode<T> newNode = new SingleLinkNode<T>(value, nextLink.getNext());  // create a new node having a reference to next node
                    nextLink.setNext(newNode);  // for current node add reference to newly created node
                    break;
                }
                nextLink = nextLink.getNext();
                /*
                 * handle any unexpected interrupt or scenario
                */
                if (nextLink == null) {
                    throw new ListAccessError("Something unexpected happened.. \n" +
                            "Please see '../linkedList/list/SingleLinkList.java file \n" +
                            "function <public void add(int index, T value) throws ListAccessError\n");
                }
                currentNodeIndex++;
            }
        }
    }

    /**
     * Public Linked List function outputing list to a screen.
     * Created in order to make the output to screen reachable for other classes
     */
    public void outputList() {
        System.out.println(toString());
    }

    /**
     * loops through list starting at root consequently until end is reached.
     *
     * @return size of list
     */
    public int getSize() {
        if (isEmpty()) return 0;

        int sizeOfList = 1;
        SingleLinkNode<T> node = getRoot(); // start at root

        /*
        * while end of file has not been reached, increment the variable
        * */
        while (node.getNext() != null) {
            sizeOfList++;
            node = node.getNext();
        }
        return sizeOfList;
    }

    /**
     * Removes the list entry (node) from list returning the removed
     * value as confirmation.
     * <p>
     * The node becomes a 'garbage' after removing it from list,
     * but Java does it owns 'garbage collection', therefore it
     * has not been re-invented again here.
     *
     * @param index the index of the entry to be removed.
     * @return generic value of element requested to be removed
     * @throws ListAccessError if index is negative, bigger then size of list or if list is empty
     * @see List interface
     */
    @Override
    public T remove(int index) throws ListAccessError {
        /* confirm that index is non-negative and not larger then current list size
         * @see
         *       private void checkIndex(int index) throws ListAccessError
         * */
        checkIndex(index);
        T nodeValue = null; // return value to be stored (creating one function-local variable to save memory allocations.
        if (isEmpty()) {
            throw new ListAccessError("List is empty!");
        } else {
            SingleLinkNode<T> nextLink = getRoot();
            int currentNodeIndex = 0; // to know what index node currently is used
            while (nextLink != null) { // loop until end of list
                if (getSize() == 1 && currentNodeIndex == index) { // If there is only 1 item in the list
                    setRoot(null);  // make list 'empty'
                    break;
                } else if (nextLink == getRoot() && currentNodeIndex == index) { // when size of list is bigger then 1 element
                    nodeValue = nextLink.getValue(); // store current node value
                    setRoot(nextLink.getNext());    // 'delete' element by setting root reference to following node
                    break;
                } else if (currentNodeIndex + 1 == index) {  // for any other cases
                    nodeValue = nextLink.getNext().getValue();  // get value from the element to be deleted
                    nextLink.setNext(nextLink.getNext().getNext()); // set reference from current link to +2 node
                    break;
                }
                nextLink = nextLink.getNext();

                /*
                * To handle unexpected errors
                * */
                if (nextLink == null) {
                    throw new ListAccessError("Something unexpected happened.. \n" +
                            "Please see '../linkedList/list/SingleLinkList.java file \n" +
                            "function <public T get(int index) throws ListAccessError\n");
                }
                currentNodeIndex++;  // incrementing 'node count' variable
            }
        }
        return nodeValue;   // return deleted element value
    }

    /**
     * Finds element at required index in list looping through it consequently from root node
     * and returns the generic value of that node
     *
     * @param index the index of the entry to be accessed.
     * @return generic value
     * @throws ListAccessError if index is negative, bigger then size of list or if list is empty
     */
    @Override
    public T get(int index) throws ListAccessError {
        /* confirm that index is non-negative and not larger then current list size
         * @see
         *       private void checkIndex(int index) throws ListAccessError
         * */
        checkIndex(index);
        /*
         * List is empty. There is nothing to return
         * */
        if (isEmpty()) {
            throw new ListAccessError("List is empty!");
        }
        /*If list is not empty and index is within bounds*/
        else {
            SingleLinkNode<T> nextLink = getRoot(); // start from root
            int currentNode = 0; // follow list node indexes
            while (nextLink != null) {
                if (currentNode == index) {
                    return nextLink.getValue(); // if node at requested index is found - return the value
                }
                nextLink = nextLink.getNext();

                /*
                 * To handle unexpected errors
                 * */
                if (nextLink == null) {
                    throw new ListAccessError("Something unexpected happened.. \n" +
                            "Please see '../linkedList/list/SingleLinkList.java file \n" +
                            "function <public T get(int index) throws ListAccessError\n");
                }
                currentNode++;
            }
            return null; // if element is found (it should be never reached)
        }
    }

    /**
     * Checks if given variable is non-negative and not bigger
     * to size of list.
     *
     * Throws ListAccessError in any of the cases
     *
     * @param index to be checked
     * @throws ListAccessError when variable is negative or bigger then size of list
     */
    private void checkIndex(int index) throws ListAccessError {
        /*
         * In case given index is negative
         * */
        if (index < 0)
            throw new ListAccessError("Index must be positive number");
        /*
         * In case given index is bigger then size of array
         * */
        else if (getSize() < (index - 1)) {
            throw new ListAccessError("Index is out of bounds. List is smaller then you think!");
        }
    }
}
