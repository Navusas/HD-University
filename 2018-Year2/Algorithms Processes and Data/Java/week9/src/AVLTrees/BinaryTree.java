package AVLTrees;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree<T extends Comparable<? super T>> implements BTree<T> {

    public TreeNode<T> root; // the root node

    /**
     * Construct an empty tree.
     */
    public BinaryTree() {
        root = null;
    }

    /**
     * Construct a singleton tree.
     * A singleton tree contains a value in the root, but the left and right subtrees are
     * empty.
     *
     * @param value the value to be stored in the tree.
     */
    public BinaryTree(T value) {
        root = new TreeNode<T>(value);
    }

    /**
     * Construct a tree with a root value, and left and right subtrees.
     *
     * @param value the value to be stored in the root of the tree.
     * @param left  the tree's left subtree.
     * @param right the tree's right subtree.
     */
    public BinaryTree(T value, BinaryTree<T> left, BinaryTree<T> right) {
        root = new TreeNode<T>(value, left, right);
    }

    /**
     * Check if the tree is empty.
     *
     * @return true iff the tree is empty.
     */
    @Override
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Insert a new value in the binary tree at a position determined by the current contents
     * of the tree, and by the ordering on the type T.
     *
     * @param value the value to be inserted into the tree.
     */
    @Override
    public void insert(T value) {
        if (isEmpty()) {// check if root is empty
            root = new TreeNode<T>(value); // create a new root
        } else if (value.compareTo(getValue()) < 0) { // if its smaller, add to left side (check recursively)
            getLeft().insert(value);
        } else { // check to right side recursively
            getRight().insert(value);
        }
    }

    /**
     * Get the value stored at the root of the tree.
     *
     * @return the value stored at the root of the tree.
     */
    @Override
    public T getValue() {
        return isEmpty() ? null : root.getValue();
    }

    /**
     * Change the value stored at the root of the tree.
     *
     * @param value the new value to be stored at the root of the tree.
     */
    @Override
    public void setValue(T value) {
        this.root.value = value;
    }

    /**
     * Get the left subtree of this tree.
     *
     * @return This tree's left subtree.
     */
    @Override
    public BTree<T> getLeft() {
        return isEmpty() ? null : root.getLeft();
    }

    /**
     * Change the left subtree of this tree.
     *
     * @param tree the new left subtree.
     */
    @Override
    public void setLeft(BTree<T> tree) {
        this.root.left = tree;
    }

    /**
     * Get the right subtree of this tree.
     *
     * @return this tree's right subtree.
     */
    @Override
    public BTree<T> getRight() {
        return isEmpty() ? null : root.getRight();
    }

    /*
     * Change the right subtree of this tree.
     * @param tree the new right subtree.
     */
    @Override
    public void setRight(BTree<T> tree) {
        this.root.right = tree;
    }

    /**
     * Check if the tree contains a given value.
     *
     * @param value the value to be checked.
     * @return true iff the value is in the tree.
     */
    @Override
    public boolean contains(T value) {
        boolean contains = false; // initialize local variable
        if(value == root.getValue()) { // check if value requeired is not a root value
            return true;
        }
        else {
            if(containsHelper(root.getLeft(), value,contains)){ // check all left leaves of tree
                return true;
            }
            else return containsHelper(root.getRight(), value,contains); // check all right leaves of tree
        }
    }

    /**
     * Recursively check each node to find the given value
     *
     * @param node the current root node
     * @param value the value to be checked
     * @return true if the value is in the tree, false otherwise
     */
    public boolean containsHelper(BTree<T> node, T value, boolean contains) {
        if(contains) return true; // return true if value was found
        if (node.getValue() == value) contains =  true;
        else if (node.getLeft() != null) {
            containsHelper(node.getLeft(), value,contains); // call recursively changing the root node
            contains = true;
        } else if (node.getRight() != null) {
            containsHelper(node.getRight(), value,contains); // call recursively changing the root node
            contains = true;
        }
        return contains;
    }

    /**
     * Traverse the tree, producing a list of all the values contained in the tree.
     *
     * @return a list of all the values in the tree.
     */
    @Override
    public List<T> traverse() {
        List<T> sortedList = new ArrayList<>(); // initialize new list
        InOrderTraversal(root.getLeft(),sortedList); // sort and add to list all left leaves of tree
        sortedList.add(root.getValue()); // add the root
        InOrderTraversal(root.getRight(),sortedList); // sort and add to list all right leaves of tree
        return sortedList; // return the list
    }

    /**
     * Executes In Order Binary Tree search, which outputs from lowest to highest all leaves.
     * It is called recursively and does left side first, then adds the roots and then sorts the right side
     *
     * @param node the current node
     * @param list the list where non null, sorted nodes values are going to be added
     */
    private void InOrderTraversal(BTree<T> node, List<T> list)
    {
        if (node != null)
        {
            InOrderTraversal(node.getLeft(),list);
            if(node.getValue() != null)
                list.add(node.getValue());
            InOrderTraversal(node.getRight(),list);
        }
    }

    /**
     * Executes traversal method, gets the list and checks it size
     * @return the size of the binary tree
     */
    public int size() {
        if(isEmpty()) return 0;
        else return traverse().size();
    }
}

