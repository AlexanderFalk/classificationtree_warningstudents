package com.warningstudents;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class DTree<T>  implements Iterable<DTree<T>> {

    T data;
    DTree<T> parent;
    LinkedList<DTree<T>> children;

    private List<DTree<T>> elementsIndex;

    public DTree(T data) {
        this.data = data;
        this.children = new LinkedList<>();
        this.elementsIndex = new LinkedList<>();
        this.elementsIndex.add(this);
    }

    public DTree<T> addChild(T child) {
        DTree<T> childNode = new DTree<T>(child);
        childNode.parent = this;
        this.children.add(childNode);
        this.registerChildForSearch(childNode);
        return childNode;
    }

    public boolean isRoot() {
        return parent == null;
    }

    public boolean isLeaf() {
        return children.size() == 0;
    }

    public int getLevel() {
        if (this.isRoot())
            return 0;
        else
            return parent.getLevel() + 1;
    }

    private void registerChildForSearch(DTree<T> node) {
        elementsIndex.add(node);
        if (parent != null)
            parent.registerChildForSearch(node);
    }

    public DTree<T> findTreeNode(Comparable<T> cmp) {
        for(DTree<T> element : this.elementsIndex) {
            T elData = element.data;
            System.out.println(elData);
            if (cmp.compareTo(elData) == 0) {
                return element;
            }
        }

        return null;
    }


    @Override
    public Iterator<DTree<T>> iterator() {
        return new TreeIterator<>(this);
    }
}
