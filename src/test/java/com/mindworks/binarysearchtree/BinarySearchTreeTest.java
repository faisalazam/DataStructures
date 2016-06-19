package com.mindworks.binarysearchtree;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static com.mindworks.binarysearchtree.BinarySearchTree.TraversalType.IN_ORDER;
import static com.mindworks.binarysearchtree.BinarySearchTree.TraversalType.LEVEL_ORDER;
import static com.mindworks.binarysearchtree.BinarySearchTree.TraversalType.POST_ORDER;
import static com.mindworks.binarysearchtree.BinarySearchTree.TraversalType.PRE_ORDER;
import static java.lang.System.setOut;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class BinarySearchTreeTest {

    @Test
    public void shouldVerifyInsertionAlongWithSearch() {
        final BinarySearchTree binarySearchTree = new BinarySearchTree();

        assertThat(binarySearchTree.search(90), nullValue()); //any random value which does not exist in tree

        binarySearchTree.insert(27);
        assertThat(binarySearchTree.search(27).getLeftChild(), nullValue());
        assertThat(binarySearchTree.search(27).getRightChild(), nullValue());
        assertThat(binarySearchTree.search(77), nullValue()); //any random value which does not exist in tree

        binarySearchTree.insert(14);
        assertThat(binarySearchTree.search(27).getLeftChild().getData(), is(14));
        assertThat(binarySearchTree.search(27).getRightChild(), nullValue());
        assertThat(binarySearchTree.search(14).getLeftChild(), nullValue());
        assertThat(binarySearchTree.search(14).getRightChild(), nullValue());
        assertThat(binarySearchTree.search(34), nullValue()); //any random value which does not exist in tree

        binarySearchTree.insert(35);
        assertThat(binarySearchTree.search(27).getLeftChild().getData(), is(14));
        assertThat(binarySearchTree.search(27).getRightChild().getData(), is(35));
        assertThat(binarySearchTree.search(14).getLeftChild(), nullValue());
        assertThat(binarySearchTree.search(14).getRightChild(), nullValue());
        assertThat(binarySearchTree.search(35).getLeftChild(), nullValue());
        assertThat(binarySearchTree.search(35).getRightChild(), nullValue());
        assertThat(binarySearchTree.search(37), nullValue()); //any random value which does not exist in tree

        binarySearchTree.insert(10);
        assertThat(binarySearchTree.search(27).getLeftChild().getData(), is(14));
        assertThat(binarySearchTree.search(27).getRightChild().getData(), is(35));
        assertThat(binarySearchTree.search(14).getLeftChild().getData(), is(10));
        assertThat(binarySearchTree.search(14).getRightChild(), nullValue());
        assertThat(binarySearchTree.search(10).getLeftChild(), nullValue());
        assertThat(binarySearchTree.search(10).getRightChild(), nullValue());
        assertThat(binarySearchTree.search(35).getLeftChild(), nullValue());
        assertThat(binarySearchTree.search(35).getRightChild(), nullValue());
        assertThat(binarySearchTree.search(44), nullValue()); //any random value which does not exist in tree

        binarySearchTree.insert(19);
        assertThat(binarySearchTree.search(27).getLeftChild().getData(), is(14));
        assertThat(binarySearchTree.search(27).getRightChild().getData(), is(35));
        assertThat(binarySearchTree.search(14).getLeftChild().getData(), is(10));
        assertThat(binarySearchTree.search(14).getRightChild().getData(), is(19));
        assertThat(binarySearchTree.search(10).getLeftChild(), nullValue());
        assertThat(binarySearchTree.search(10).getRightChild(), nullValue());
        assertThat(binarySearchTree.search(19).getLeftChild(), nullValue());
        assertThat(binarySearchTree.search(19).getRightChild(), nullValue());
        assertThat(binarySearchTree.search(35).getLeftChild(), nullValue());
        assertThat(binarySearchTree.search(35).getRightChild(), nullValue());
        assertThat(binarySearchTree.search(49), nullValue()); //any random value which does not exist in tree

        binarySearchTree.insert(31);
        assertThat(binarySearchTree.search(27).getLeftChild().getData(), is(14));
        assertThat(binarySearchTree.search(27).getRightChild().getData(), is(35));
        assertThat(binarySearchTree.search(14).getLeftChild().getData(), is(10));
        assertThat(binarySearchTree.search(14).getRightChild().getData(), is(19));
        assertThat(binarySearchTree.search(10).getLeftChild(), nullValue());
        assertThat(binarySearchTree.search(10).getRightChild(), nullValue());
        assertThat(binarySearchTree.search(19).getLeftChild(), nullValue());
        assertThat(binarySearchTree.search(19).getRightChild(), nullValue());
        assertThat(binarySearchTree.search(35).getLeftChild().getData(), is(31));
        assertThat(binarySearchTree.search(35).getRightChild(), nullValue());
        assertThat(binarySearchTree.search(31).getLeftChild(), nullValue());
        assertThat(binarySearchTree.search(31).getRightChild(), nullValue());
        assertThat(binarySearchTree.search(11), nullValue()); //any random value which does not exist in tree

        binarySearchTree.insert(42);
        assertThat(binarySearchTree.search(27).getLeftChild().getData(), is(14));
        assertThat(binarySearchTree.search(27).getRightChild().getData(), is(35));
        assertThat(binarySearchTree.search(14).getLeftChild().getData(), is(10));
        assertThat(binarySearchTree.search(14).getRightChild().getData(), is(19));
        assertThat(binarySearchTree.search(35).getLeftChild().getData(), is(31));
        assertThat(binarySearchTree.search(35).getRightChild().getData(), is(42));
        assertThat(binarySearchTree.search(10).getLeftChild(), nullValue());
        assertThat(binarySearchTree.search(10).getRightChild(), nullValue());
        assertThat(binarySearchTree.search(19).getLeftChild(), nullValue());
        assertThat(binarySearchTree.search(19).getRightChild(), nullValue());
        assertThat(binarySearchTree.search(31).getLeftChild(), nullValue());
        assertThat(binarySearchTree.search(31).getRightChild(), nullValue());
        assertThat(binarySearchTree.search(42).getLeftChild(), nullValue());
        assertThat(binarySearchTree.search(42).getRightChild(), nullValue());
        assertThat(binarySearchTree.search(21), nullValue()); //any random value which does not exist in tree
    }

    @Test
    public void shouldVerifyTraversals() throws Exception {
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        final PrintStream printStream = new PrintStream(outputStream);
        setOut(printStream);

        final BinarySearchTree binarySearchTree = new BinarySearchTree();

        verifyTraversals(binarySearchTree, outputStream, "", "", "", "");

        binarySearchTree.insert(27);
        verifyTraversals(binarySearchTree, outputStream, "27 ", "27 ", "27 ", "27 ");

        binarySearchTree.insert(14);
        verifyTraversals(binarySearchTree, outputStream, "14 27 ", "27 14 ", "14 27 ", "27 14 ");

        binarySearchTree.insert(35);
        verifyTraversals(binarySearchTree, outputStream, "14 27 35 ", "27 14 35 ", "14 35 27 ", "27 14 35 ");

        binarySearchTree.insert(10);
        verifyTraversals(binarySearchTree, outputStream, "10 14 27 35 ", "27 14 10 35 ", "10 14 35 27 ", "27 14 35 10 ");

        binarySearchTree.insert(19);
        verifyTraversals(binarySearchTree, outputStream, "10 14 19 27 35 ", "27 14 10 19 35 ", "10 19 14 35 27 ", "27 14 35 10 19 ");

        binarySearchTree.insert(31);
        verifyTraversals(binarySearchTree, outputStream, "10 14 19 27 31 35 ", "27 14 10 19 35 31 ", "10 19 14 31 35 27 ", "27 14 35 10 19 31 ");

        binarySearchTree.insert(42);
        verifyTraversals(binarySearchTree, outputStream, "10 14 19 27 31 35 42 ", "27 14 10 19 35 31 42 ", "10 19 14 31 42 35 27 ", "27 14 35 10 19 31 42 ");
    }

    @Test
    public void shouldVerifyHeight() throws Exception {
        final BinarySearchTree binarySearchTree = new BinarySearchTree();

        assertThat(binarySearchTree.height(), is(0));

        binarySearchTree.insert(27);
        assertThat(binarySearchTree.height(), is(0));

        binarySearchTree.insert(14);
        assertThat(binarySearchTree.height(), is(1));

        binarySearchTree.insert(35);
        assertThat(binarySearchTree.height(), is(1));

        binarySearchTree.insert(10);
        assertThat(binarySearchTree.height(), is(2));

        binarySearchTree.insert(19);
        assertThat(binarySearchTree.height(), is(2));

        binarySearchTree.insert(31);
        assertThat(binarySearchTree.height(), is(2));

        binarySearchTree.insert(42);
        assertThat(binarySearchTree.height(), is(2));

        binarySearchTree.insert(45);
        assertThat(binarySearchTree.height(), is(3));
    }

    private void verifyTraversals(final BinarySearchTree binarySearchTree,
                                  ByteArrayOutputStream outputStream, final String expectedInorder,
                                  final String expectedPreorder,
                                  final String expectedPostorder,
                                  final String expectedLevelorder) throws IOException {
        outputStream.reset();
        binarySearchTree.traverse(IN_ORDER);
        assertThat(outputStream.toString(), is(expectedInorder));

        outputStream.reset();
        binarySearchTree.traverse(PRE_ORDER);
        assertThat(outputStream.toString(), is(expectedPreorder));

        outputStream.reset();
        binarySearchTree.traverse(POST_ORDER);
        assertThat(outputStream.toString(), is(expectedPostorder));

        outputStream.reset();
        binarySearchTree.traverse(LEVEL_ORDER);
        assertThat(outputStream.toString(), is(expectedLevelorder));
    }
}