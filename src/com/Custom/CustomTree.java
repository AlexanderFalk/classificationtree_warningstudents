package com.Custom;

public class CustomTree<T> {

    private Node root;
    private int size;

    public CustomTree(T item) {
        Node firstNode = new Node(item);
        if (this.root != null) {
            this.root = firstNode;
        }
    }

    public CustomTree<T> add(T item, String answer) {
        CustomTree<T> childNode = new CustomTree<>(item);
        Node child = new Node(item);
        insert(item, this.root, child, answer);
        return childNode;
    }

    public CustomTree<T> addEnd(T item) {
        CustomTree<T> childNode = new CustomTree<>(item);
        Node child = new Node(item);

        return childNode;
    }

    private void insert(T item, Node parent, Node child, String answer) {

        if (answer.equalsIgnoreCase("yes")) {
            parent.setRight(child);
            child.setParent(parent);
            child.setItem(item);
            this.size++;
        }
        else if (answer.equalsIgnoreCase("no")) {
            parent.setLeft(child);
            child.setParent(parent);
            child.setItem(item);
            this.size++;
        }
    }



    private class Node {
        private Node left;
        private Node right;
        private Node parent;
        private T item;

        Node(T item) {
            this.left = null;
            this.right = null;
            this.parent = null;
            this.item = item;
        }

        Node getLeft() {
            return left;
        }

        void setLeft(Node left) {
            this.left = left;
        }

        Node getRight() {
            return right;
        }

        void setRight(Node right) {
            this.right = right;
        }

        Node getParent() {
            return parent;
        }

        void setParent(Node parent) {
            this.parent = parent;
        }

        T getItem() {
            return item;
        }

        public void setItem(T item) {
            this.item = item;
        }
    }

}
