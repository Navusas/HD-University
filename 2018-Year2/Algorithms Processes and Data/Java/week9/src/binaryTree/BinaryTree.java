package binaryTree;

import jdk.nashorn.api.tree.Tree;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree<T extends Comparable<? super T>> implements BTree<T> {

    private TreeNode<T> root; // the root node

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
        if (isEmpty()) {
            root = new TreeNode<T>(value);
        } else if (value.compareTo(getValue()) <= 0) {
            getLeft().insert(value);
        } else {
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
        if (isEmpty())
            return null;
        return root.getValue();
        // Note: it might make sense to define getValue() to throw a (custom) exception if an attempt
        // is made to access a value from an empty tree.
        // However, since a tree is empty iff it's root node is null, it is also acceptable to rely
        // on Java's NullPointerException.
        // This comment also applies to the other get and set methods defined in this interface.

        // placeholder return value below - replace with implementation of getValue()
    }

    /**
     * Change the value stored at the root of the tree.
     *
     * @param value the new value to be stored at the root of the tree.
     */
    @Override
    public void setValue(T value) {
        this.root.value = value;
        // implement setValue(T value) here
    }

    /**
     * Get the left subtree of this tree.
     *
     * @return This tree's left subtree.
     */
    @Override
    public BTree<T> getLeft() {
        if (isEmpty()) return null;
        return root.getLeft();
        // placeholder return value below - replace with implementation of getLeft()
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
        if (isEmpty()) {
            return null;
        }
        return root.getRight();
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
        return contains(root.getLeft(), value);
    }

    public boolean contains(BTree<T> node, T value) {
        if (node.getValue() == value) return true;
        else if (node.getLeft() != null) {
            contains(node.getLeft(), value);
            return true;
        } else if (node.getRight() != null) {
            contains(node.getRight(), value);
            return true;
        }
        return false;
    }

    /**
     * Traverse the tree, producing a list of all the values contained in the tree.
     *
     * @return a list of all the values in the tree.
     */
    @Override
    public List<T> traverse() {
        List<T> sortedList = new ArrayList<>();
        InOrderTraversal(root.getLeft(),sortedList);
        sortedList.add(root.getValue());
        InOrderTraversal(root.getRight(),sortedList);
        return sortedList;
    }

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

    public int size() {
        if(isEmpty()) return 0;
        else return traverse().size();
    }
}

