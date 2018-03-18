package com.Custom;// DECISION TREE  APPLICATION
// Frans Coenen
// Thursday 15 August 2002
// Department of Computer Science, University of Liverpool

import com.OnMyOwn.ClassificationTree;

import java.io.*;

class DecisionTreeApp {

    private static BufferedReader keyboardInput = new
            BufferedReader(new InputStreamReader(System.in));
    private static ClassificationTree newTree;


    public static void main(String[] args) throws IOException {

        // Create instance of class DecisionTree

        newTree = new ClassificationTree();

        // Generate tree

        generateTree();

        // Output tree

        System.out.println("\nOUTPUT DECISION TREE");
        System.out.println("====================");
        newTree.outputBinTree();

        // Query tree

        queryTree();
    }

    /* GENERATE TREE */

    static void generateTree() {
        System.out.println("\nGENERATE DECISION TREE");
        System.out.println("======================");
        newTree.createRoot(1,"Read Textbook?");
        newTree.addYesNode(1,2,"Hand ins made in time?");
        newTree.addNoNode(1,3,"Hand ins made in time?");
        newTree.addYesNode(2,4,"Attended lectures?");
        newTree.addNoNode(2,5,"You could easily fail the exam");
        newTree.addYesNode(4,6,"Made exercises?");
        newTree.addNoNode(4,7,"Made exercises?");
        newTree.addYesNode(6,8,"You should be able to pass the exam");
        newTree.addNoNode(6,9,"You should be able to pass the exam");
        newTree.addYesNode(7,10,"You should be able to pass the exam");
        newTree.addNoNode(7,11,"You could easily fail the exam");
        newTree.addYesNode(3,12,"Made exercises?");
        newTree.addNoNode(3,13,"You could easily fail the exam");
        newTree.addYesNode(12,14,"You should be able to pass the exam");
        newTree.addNoNode(12,15,"You should be able to pass the exam");
    }

    /* QUERY TREE */

    private static void queryTree() throws IOException {
        System.out.println("\nQUERY DECISION TREE");
        System.out.println("===================");
        newTree.queryTree();

        // Option to exit

        optionToExit();
    }

    /* OPTION TO EXIT PROGRAM */

    private static void optionToExit() throws IOException {
        System.out.println("Exit? (enter \"Yes\" or \"No\")");
        String answer = keyboardInput.readLine();
        if (answer.equalsIgnoreCase("Yes")) return;
        else {
            if (answer.equals("No")) queryTree();
            else {
                System.out.println("ERROR: Must answer \"Yes\" or \"No\"");
                optionToExit();
            }
        }
    }
}