package com.mindworks.binarysearchtree;

/*
 * A binary search tree (BST) is a tree in which all nodes follows the below mentioned properties:
 * The left sub-tree of a node has key less than or equal to its parent node's key.
 * The right sub-tree of a node has key greater than or equal to its parent node's key.
 *
 * Time complexity in big O notation
 *          Average	    Worst case
 * Space	Θ(n)	    O(n)
 * Search	Θ(log n)	O(n)
 * Insert	Θ(log n)	O(n)
 * Delete	Θ(log n)	O(n)
 */
public class BinarySearchTree {
    enum TraversalType {
        IN_ORDER,
        PRE_ORDER,
        POST_ORDER
    }

    private BinarySearchTreeNode root;

    public void insert(final int data) {
        if (root == null) {
            root = new BinarySearchTreeNode(data, null, null);
            return;
        }

        BinarySearchTreeNode currentRootNode = root;

        while (data != currentRootNode.getData()) {
            if (data < currentRootNode.getData()) {
                if (currentRootNode.getLeftChild() == null) {
                    currentRootNode.setLeftChild(new BinarySearchTreeNode(data, null, null));
                }
                currentRootNode = currentRootNode.getLeftChild();
            } else if (data > currentRootNode.getData()) {
                if (currentRootNode.getRightChild() == null) {
                    currentRootNode.setRightChild(new BinarySearchTreeNode(data, null, null));
                }
                currentRootNode = currentRootNode.getRightChild();
            }
        }
    }

    public BinarySearchTreeNode search(final int data) {
        BinarySearchTreeNode currentRootNode = root;
        while (currentRootNode != null) {
            if (data == currentRootNode.getData()) {
                return currentRootNode;
            } else if (data < currentRootNode.getData()) {
                currentRootNode = currentRootNode.getLeftChild();
            } else if (data > currentRootNode.getData()) {
                currentRootNode = currentRootNode.getRightChild();
            }
        }
        return null;

//  recursive solution
//        if (currentRootNode == null || data == currentRootNode.getData()) {
//            return currentRootNode;
//        } else if (data < currentRootNode.getData()) {
//            search(data, currentRootNode.getLeftChild());
//        } else {
//            search(data, currentRootNode.getRightChild());
//        }
    }

    public void traverse(final TraversalType traversalType) {
        switch (traversalType) {
            case IN_ORDER:
                inOrderTraversal(root);
                break;
            case PRE_ORDER:
                preOrderTraversal(root);
                break;
            case POST_ORDER:
                postOrderTraversal(root);
                break;
            default:
                //do nothing
        }
    }

    /*
     * Preorder traversal is used to ensure that any given node is visited before visiting its children.
     */
    private void preOrderTraversal(final BinarySearchTreeNode currentRootNode) {
        if (currentRootNode == null) {
            return;
        }
        System.out.print(currentRootNode.getData() + " ");
        preOrderTraversal(currentRootNode.getLeftChild());
        preOrderTraversal(currentRootNode.getRightChild());
    }

    /*
     * Postorder traversal is used to ensure that the children are visited before visiting any given node.
     */
    private void postOrderTraversal(final BinarySearchTreeNode currentRootNode) {
        if (currentRootNode == null) {
            return;
        }
        postOrderTraversal(currentRootNode.getLeftChild());
        postOrderTraversal(currentRootNode.getRightChild());
        System.out.print(currentRootNode.getData() + " ");
    }

    /*
     * An inorder traversal first visits the left child (including its entire subtree), then visits the node,
     * and finally visits the right child (including its entire subtree).
     * The binary search tree makes use of this traversal to print all nodes in ascending order of value.
     */
    private void inOrderTraversal(final BinarySearchTreeNode currentRootNode) {
        if (currentRootNode == null) {
            return;
        }
        inOrderTraversal(currentRootNode.getLeftChild());
        System.out.print(currentRootNode.getData() + " ");
        inOrderTraversal(currentRootNode.getRightChild());
    }
}
