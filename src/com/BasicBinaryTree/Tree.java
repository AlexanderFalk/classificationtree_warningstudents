package com.BasicBinaryTree;

public class Tree<T extends Comparable<T>> {
    private Node root;
    private int size;

    public Tree() {
        this.root = null;
    }

    private int size() {
        return size;
    }

    public void add(T item) {
        Node node = new Node(item);

        // If this is an empty tree, set it as root
        if (root == null) {
            this.root = node;
            System.out.println("Set Root: " + node.getItem());
            this.size++;
        }
        // Otherwise we need to insert the item into the tree using the binary tree insert algorithm
        else {
            insert(this.root, node);
        }
    }

    public boolean contains(T item) {
        Node currentNode = getNode(item);

        return currentNode != null;
    }

    public boolean delete(T item) {
        boolean isDeleted = false;

        // We are making sure a root is set, which states a tree is accessible
        if (this.root == null) {
            return false;
        }

        // Find the node to delete
        Node currentNode = getNode(item);

        if (currentNode != null) {
            // if the node we are deleting doesn't have any children, just delete it
            if (currentNode.getLeft() == null && currentNode.getRight() == null) {
                unlink(currentNode, null);
                isDeleted = true;
            }
            // if the node we are deleting only has a right child, remove it for the tree hierarchy
            else if (currentNode.getLeft() == null && currentNode.getRight() != null) {
                unlink(currentNode, currentNode.getRight());
                isDeleted = true;
            }
            // if the node we are deleting only has a left child, remove it for the tree hierarchy
            else if (currentNode.getLeft() != null && currentNode.getRight() == null) {
                unlink(currentNode, currentNode.getLeft());
                isDeleted = true;
            }
            // the node has both children, do a node swap to delete
            else {
                Node child = currentNode.getLeft();

                // swap out the node with the right most leaf node on the left side
                while (child.getRight() != null && child.getLeft() != null) {
                    child = child.getRight();
                }

                // we have the right most leaf node, so we can replace the current node with this node
                child.getParent().setRight(null); // remove leaf node from current position

                child.setLeft(currentNode.getLeft());
                child.setRight(currentNode.getRight());

                unlink(currentNode, child);
                isDeleted = true;
            }
         }

        if (isDeleted) {
            this.size--;
        }

        return isDeleted;
    }

    private void unlink(Node currentNode, Node newNode) {
        // If the current node is a root node, we replace
        if (currentNode == this.root) {
            newNode.setLeft(currentNode.getLeft());
            newNode.setRight(currentNode.getRight());
            this.root= newNode;
        } else if (currentNode.getParent().getRight() == currentNode) {
            currentNode.getParent().setRight(newNode);
        } else {
            currentNode.getParent().setLeft(newNode);
        }
    }


    private Node getNode(T item) {
        Node currentNode = this.root;
        while (currentNode != null) {
            int value = item.compareTo(currentNode.getItem());

            if (value == 0) {
                return currentNode;
            } else if(value < 0) {
                currentNode = currentNode.getLeft();
            } else {
                currentNode = currentNode.getRight();
            }
        }
        return null;
    }

    private void insert(Node parent, Node child) {
        // If the child is less than the parent, it goes to the left
        if (child.getItem().compareTo(parent.getItem()) < 0) {
            // If the left node is null, we've found the spot of insertion
            if (parent.getLeft() == null) {
                parent.setLeft(child);
                child.setParent(parent);
                this.size++;

            // If that didn't work out, we need to call Insert again to test for left or right
            }else {
                insert(parent.getLeft(), child);
            }
          // If the child node is greater than the parent, it goes to the right
        } else if(child.getItem().compareTo(parent.getItem()) > 0) {
            // If the right node is null, we've found the spot of insertion
            if (parent.getItem() == null) {
                parent.setRight(child);
                child.setParent(parent);
                this.size++;
              // Otherwise we need to call insert again and test for left and right
            } else {
                insert(parent.getRight(), child);
            }
        }

        // If the parent and the child are equal, we will not do anything, since we assume
        // that every value is unique or already exists
    }


    public void addRoot(T item) {
        this.root = root;
        this.size++;
    }

    public void addChildRight(Node parent, Node child, T item) {
        Node node = new Node(item);

        // If this is an empty tree, set it as root
        if (root == null) {
            this.root = node;
            System.out.println("Set Root: " + node.getItem());
            this.size++;
        }
        // Otherwise we need to insert the item into the tree using the binary tree insert algorithm
        else {
            parent.setRight(child);
            child.setParent(parent);
            this.size++;
        }
    }

    public void addChildLeft(Node parent, Node child, T item) {
        Node node = new Node(item);

        // If this is an empty tree, set it as root
        if (root == null) {
            this.root = node;
            System.out.println("Set Root: " + node.getItem());
            this.size++;
        }
        // Otherwise we need to insert the item into the tree using the binary tree insert algorithm
        else {
            parent.setLeft(child);
            child.setParent(parent);
            this.size++;
        }
    }

    public void ins(Node parent, Node child) {

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
