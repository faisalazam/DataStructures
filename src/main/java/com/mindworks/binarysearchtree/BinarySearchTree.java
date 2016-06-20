package com.mindworks.binarysearchtree;

import java.util.LinkedList;
import java.util.List;

import static java.lang.Math.max;

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
        POST_ORDER,
        LEVEL_ORDER
    }

    private BinarySearchTreeNode root;

    public BinarySearchTreeNode insert(final int data) {
        if (root == null) {
            root = new BinarySearchTreeNode(data, null, null);
            return root;
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
        return currentRootNode;
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

    public int height() {
        final int height = height(root) - 1;
        return height < 0 ? 0 : height;
    }

    public BinarySearchTreeNode parent(final BinarySearchTreeNode nodeLookingForParent) {
        if (nodeLookingForParent == null) {
            return null;
        }
        return parent(nodeLookingForParent.getData());
    }

    public BinarySearchTreeNode parent(final int data) {
        if (root == null) {
            return null;
        }

        BinarySearchTreeNode parentNode = null;
        BinarySearchTreeNode currentRootNode = root;
        while (currentRootNode.getData() != data) {
            parentNode = currentRootNode;
            if (currentRootNode.getData() > data) {
                currentRootNode = currentRootNode.getLeftChild();
            } else {
                currentRootNode = currentRootNode.getRightChild();
            }
            if (currentRootNode == null) {
                return null;
            }
        }
        return parentNode;
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
            case LEVEL_ORDER:
                levelOrderTraversal(root);
                break;
            default:
                //do nothing
        }
    }

    private int height(final BinarySearchTreeNode currentRootNode) {
        if (currentRootNode == null) {
            return 0;
        }

        return 1 + max(
                height(currentRootNode.getLeftChild()),
                height(currentRootNode.getRightChild())
        );
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

    /**
     * Breadth-first search (BFS) or level order traversal is an algorithm for traversing or searching tree or graph data structures.
     * It starts at the tree root (or some arbitrary node of a graph, sometimes referred to as a 'search key'[1])
     * and explores the neighbor nodes first, before moving to the next level neighbors
     */
    private void levelOrderTraversal(final BinarySearchTreeNode currentRootNode) {
        final List<BinarySearchTreeNode> nodes = new LinkedList<>();
        if (currentRootNode != null) {
            nodes.add(currentRootNode);
        }

        while (!nodes.isEmpty()) {
            final BinarySearchTreeNode node = nodes.remove(0);
            System.out.print(node.getData() + " ");

            if (node.getLeftChild() != null) {
                nodes.add(node.getLeftChild());
            }
            if (node.getRightChild() != null) {
                nodes.add(node.getRightChild());
            }
        }
    }
}
