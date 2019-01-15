package AVLTrees;


import java.nio.channels.Selector;

/**
 * An implementation of Binary Trees that rebalances trees using the AVL method.
 * 
 * @author Hugh Osborne
 * @version December 2018
 */
public class AVLtree<T extends Comparable<T>> extends BinaryTree<T> {
    // the balanceFactor selector is used to indicate the balance factor of ths tree.
    // If the balance factor is -1 (the tree is one deeper on the left), balanceFactor will be LEFT.
    // If the balance factor is +1 (the tree is one deeper on the right), balanceFactor will be RIGHT.
    // If the balance facotr is zero (left and right subtrees are equally deep), balanceFactor will be NULL.
    private Selector balanceFactor;

    /**
     * Construct an empty tree.
     */
    public AVLtree() {
        super();
        setBalanced(); // both subtrees are empty, so the tree is balanced.
    }

    /**
     * Fet the balance factor of this tree.
     *
     * @return this tree's balance factor.
     */
    public Selector getBalanceFactor() {
        return balanceFactor;
    }

    /**
     * Set the balance factor to indicate that the tree is balanced.
     */
    public void setBalanced() {
        balanceFactor = null;
    }

    /**
     * Set the balance factor to indicate that the tree is not balanced.
     *
     * @param balanceFactor indicates whether the tree is deeper on the LEFT or the RIGHT.
     */
    public void setUnbalanced(Selector balanceFactor) {
        this.balanceFactor = balanceFactor;
    }

    /**
     * Insert a new value in the binary tree at a position determined by the current contents
     * of the tree, and by the ordering on the type T.
     * Rebalance the tree if necessary.
     *
     * @param value the value to be inserted into the tree.
     */

    public boolean insert(T value) throws SelectionError {
        if (isEmpty()) {
            root = new TreeNode(value, new AVLtree<T>(), new AVLtree<T>());
            return true; // the tree is deeper than it was
        } else { // select which subtree to insert in
            Selector insertIn;
            if (value.compareTo(getValue()) < 0) {
                insertIn = Selector.LEFT;
            } else {
                insertIn = Selector.RIGHT;
            }
            // insert the value in the selected subtree, and check if that subtree has become deeper.
            boolean insertedDeeper = ((AVLtree<T>) getSubtree(insertIn)).insert(value);
            if (insertedDeeper) { // may need to rebalance
                if (balanceFactor == null) { // tree was balanced
                    setUnbalanced(insertIn); // it is now deeper on the side
                    return true; // the tree is now deeper
                } else if (balanceFactor == insertIn.inverse()) { // tree was deeper on the opposite side
                    setBalanced(); // it is now balanced
                    // the tree has not become deeper
                } else if (balanceFactor == insertIn) { // tree was already deeper on this side
                    rebalance(insertIn); // it is now too deep and must be rebalanced
                    // rebalancing the tree ensures it is not deeper.
                }
            } else {
                // the tree did not become deeper.
            }
            return false;
        }
    }

    /**
     * Rebalance an out of balance tree.
     * The tree is unbalanced due to a deeper tree on the side indicated by the selector.
     * This deeper tree must cannot be balanced, and must have a balance factor of +1 or -1 (see lecture notes, pp 11,12).
     * If the deeper tree is, in turn, deeper on the same side then the simpler rotation (lecture note, p 11) will be used.
     * If the deeper tree is deeper on the opposite side the more complex rotation (lecture notes, pp 12,13) will be used.
     *
     * @param deeper the side on which the tree is unbalanced.
     */
    private void rebalance(Selector deeper) throws SelectionError {
        AVLtree<T> deeperTree = (AVLtree<T>) getSubtree(deeper); // get the deeper subtree
        if (deeperTree.getBalanceFactor() == deeper) { // is it, in turn, deeper on the same side?
            simplerRotation(deeper); // if so use simpler rotattion.
        } else {
            moreComplexRotation(deeper); // otherwise used more complex rotation.
        }
    }

    /**
     * Perform the simpler rebalancing rotation.
     * The variable names reflect the names used in the example of this type of rotation presented in the lecture notes
     * on p 11, which showed a tree out of balance on the right.  The left case is symmetric.
     * In this example, capital letters were used for whole subtrees, e.g. "A".  In the code this is called "subtreeA".
     * Lower case letters were used for specific tree roots, e.g. "x".  In the code this is called "subtreeRootX".
     *
     * @param deeper indicates which side of the tree is deeper.
     */
    private void simplerRotation(Selector deeper) throws SelectionError {
        // Access the necessary components of the tree.
        AVLtree<T> subtreeA = (AVLtree<T>) getSubtree(deeper.inverse());
        AVLtree<T> subtreeRootX = (AVLtree<T>) getSubtree(deeper);
        AVLtree<T> subTreeU = (AVLtree<T>) subtreeRootX.getSubtree(deeper.inverse());
        AVLtree<T> subTreeY = (AVLtree<T>) subtreeRootX.getSubtree(deeper);
        AVLtree<T> subtreeRootR = this; // "r" is the root of the whole unbalanced tree, hence the root of this tree

        /* We want to move subtreeRootX up to be the new root of the tree, and move subtreeRootR down to be its
         * subtree on the inverse side (e.g. in the example in the lecture notes, the tree was too deep on the right,
         * and "r" became the left subtree of "x").
         * It's not possible to reassign the root of the tree so instead we will swap values between the "x" root
         * and the "r" root.
         */
        T rValue = subtreeRootR.getValue();
        subtreeRootR.setValue(subtreeRootX.getValue());
        subtreeRootX.setValue(rValue);

        // "subtreeRootX" now contains "r"'s value, and "subtreeRootR" cotnains "x"'s value.
        // Reassign so that the variable names match the cotnent.
        AVLtree<T> tmpSubtreeRoot = subtreeRootR;
        subtreeRootR = subtreeRootX;
        subtreeRootX = tmpSubtreeRoot;

        // Connect up the subtrees - see lecture notes, p 11.
        subtreeRootX.setSubtree(deeper.inverse(), subtreeRootR);
        subtreeRootX.setSubtree(deeper, subTreeY);
        subtreeRootX.setBalanced(); // "x", the root of the whole tree (i.e. this) is balanced
        subtreeRootR.setSubtree(deeper.inverse(), subtreeA);
        subtreeRootR.setSubtree(deeper, subTreeU);
        subtreeRootR.setBalanced(); // and "r" is also balanced
    }

    private void moreComplexRotation(Selector insertedIn) throws SelectionError {
        // more complex rotation should be implemented here
    }
}
