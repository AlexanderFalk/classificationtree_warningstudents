package com.OnMyOwn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ClassificationTree {

    // PRIVATE INNER CLASS
    private class Tree {
        /* FIELDS */
        private int nodeID;
        // Question or Answer
        private String QorA;
        private Tree yesBranch = null;
        private Tree noBranch = null;

        Tree(int nodeID, String QorA) {
            this.nodeID = nodeID;
            this.QorA = QorA;
        }

    }

    // Class fields
    private Tree rootNode = null;

    // Default Constructor
    public ClassificationTree(){}

    // Action methods
    public void createRoot(int nodeID, String QorA) {
        this.rootNode = new Tree(nodeID, QorA);
        System.out.println("Root node has been created with ID: " + nodeID);
    }

    public void addYesNode(int existingNodeID, int selfNodeID, String newQorA) {
        // If there is no root node, don't do anything
        if (this.rootNode == null) {
            System.out.println("NO ROOT NODE DETECTED");
            return;
        }

        if (searchAndAddYesNode(rootNode, existingNodeID, selfNodeID, newQorA)) {
            System.out.println("Added node " + selfNodeID + " onto the \"Yes\"-branch of node " + existingNodeID);
        } else {
            System.out.println("Node " + existingNodeID + " not found!");
        }
    }

    public void addNoNode(int existingNodeID, int selfNodeID, String newQorA) {
        // If there is no root node, don't do anything
        if (this.rootNode == null) {
            System.out.println("NO ROOT NODE DETECTED");
            return;
        }

        if (searchAndAddNoNode(rootNode, existingNodeID, selfNodeID, newQorA)) {
            System.out.println("Added node " + selfNodeID + " onto the \"Yes\"-branch of node " + existingNodeID);
        } else {
            System.out.println("Node " + existingNodeID + " not found!");
        }
    }

    private boolean searchAndAddYesNode(Tree currentNode, int existingNodeID, int selfNodeID, String QorA) {
        if (currentNode.nodeID == existingNodeID) {
            // Found a node with that ID
            if (currentNode.yesBranch == null) currentNode.yesBranch = new Tree(selfNodeID, QorA);
            else {
                System.out.println("WARNING: Overwriting existing node with ID: " + currentNode.yesBranch.nodeID +
                        ", which is linked to an 'Yes'-branch with Node ID: " + existingNodeID);
                currentNode.yesBranch = new Tree(selfNodeID, QorA);
            }
            return true;
        } else {
            // Try to go to the Yes-Branch if it exists
            if (currentNode.yesBranch != null) {
                if (searchAndAddYesNode(currentNode.yesBranch, existingNodeID, selfNodeID, QorA)) {
                    return true;
                }
            }
            // Try to go to the No-Branch if it exists
            return currentNode.noBranch != null && searchAndAddYesNode(currentNode.noBranch, existingNodeID, selfNodeID, QorA);
        }
    }

    private boolean searchAndAddNoNode(Tree currentNode, int existingNodeID, int selfNodeID, String QorA) {
        if (currentNode.nodeID == existingNodeID) {
            // Found a node with that ID
            if (currentNode.noBranch == null) currentNode.noBranch = new Tree(selfNodeID, QorA);
            else {
                System.out.println("WARNING: Overwriting existing node with ID: " + currentNode.yesBranch.nodeID +
                        ", which is linked to an 'No'-branch with Node ID: " + existingNodeID);
                currentNode.noBranch = new Tree(selfNodeID, QorA);
            }
            return true;
        } else {
            // Try to go to the Yes-Branch if it exists
            if (currentNode.yesBranch != null) {
                if (searchAndAddNoNode(currentNode.yesBranch, existingNodeID, selfNodeID, QorA)) {
                    return true;
                }
            }
            // Try to go to the No-Branch if it exists
            return currentNode.noBranch != null && searchAndAddNoNode(currentNode.noBranch, existingNodeID, selfNodeID, QorA);
        }
    }

    public void queryTree() {
        try {
            queryTree(this.rootNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void queryTree(Tree currentNode) throws IOException {
        // Testing for whether the leaf node (the answer) and missing branches
        if (currentNode.yesBranch == null)
            if (currentNode.noBranch == null) System.out.println(currentNode.QorA);
        else {
                System.out.println("ERROR: Missing \"Yes\"-branch at \"" + currentNode.QorA + "\" question");
                return;
            }

        if (currentNode.noBranch == null) {
            System.out.println("Error: Missing \"No\" branch at \"" +
                    currentNode.QorA + "\" question");
            return;
        }

        // Asking the question afterwards
        askQuestion(currentNode);
    }

    private void askQuestion(Tree currentNode) throws IOException {
        System.out.println(currentNode.QorA + " (Enter \"Yes\" or \"No\")");
        BufferedReader inputFromUser = new BufferedReader(new InputStreamReader(System.in));
        String answer = inputFromUser.readLine();
        if (answer.equalsIgnoreCase("yes")) queryTree(currentNode.yesBranch);
        else if (answer.equalsIgnoreCase("no")) queryTree(currentNode.noBranch);
        else {
            System.out.println("Must answer either Yes or No");
            askQuestion(currentNode);
        }
    }

    /* OUTPUT BIN TREE */

    public void outputBinTree() {

        outputBinTree("1",rootNode);
    }

    private void outputBinTree(String tag, Tree currentNode) {

        // Check for empty node

        if (currentNode == null) return;

        // Output

        System.out.println("[" + tag + "] nodeID = " + currentNode.nodeID +
                ", question/answer = " + currentNode.QorA);

        // Go down yes branch

        outputBinTree(tag + ".1",currentNode.yesBranch);

        // Go down no branch

        outputBinTree(tag + ".2",currentNode.noBranch);
    }
}


