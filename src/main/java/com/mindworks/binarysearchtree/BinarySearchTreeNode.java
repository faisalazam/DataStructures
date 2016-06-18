package com.mindworks.binarysearchtree;

public class BinarySearchTreeNode {
    private int data;
    private BinarySearchTreeNode leftChild;
    private BinarySearchTreeNode rightChild;

    public BinarySearchTreeNode(final int data, final BinarySearchTreeNode leftChild, final BinarySearchTreeNode rightChild) {
        this.data = data;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public int getData() {
        return data;
    }

    public void setData(final int data) {
        this.data = data;
    }

    public BinarySearchTreeNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(final BinarySearchTreeNode leftChild) {
        this.leftChild = leftChild;
    }

    public BinarySearchTreeNode getRightChild() {
        return rightChild;
    }

    public void setRightChild(final BinarySearchTreeNode rightChild) {
        this.rightChild = rightChild;
    }
}
