package com.warningstudents;

import java.util.Iterator;

public class TreeIterator<T> implements Iterator<DTree<T>>{

        enum ProcessStages {
            ProcessParent, ProcessChildCurNode, ProcessChildSubNode
        }

        private DTree<T> DTree;

        public TreeIterator(DTree<T> DTree) {
            this.DTree = DTree;
            this.doNext = ProcessStages.ProcessParent;
            this.childrenCurNodeIter = DTree.children.iterator();
        }

        private ProcessStages doNext;
        private DTree<T> next;
        private Iterator<DTree<T>> childrenCurNodeIter;
        private Iterator<DTree<T>> childrenSubNodeIter;

        @Override
        public boolean hasNext() {

            if (this.doNext == ProcessStages.ProcessParent) {
                this.next = this.DTree;
                this.doNext = ProcessStages.ProcessChildCurNode;
                return true;
            }

            if (this.doNext == ProcessStages.ProcessChildCurNode) {
                if (childrenCurNodeIter.hasNext()) {
                    DTree<T> childDirect = childrenCurNodeIter.next();
                    childrenSubNodeIter = childDirect.iterator();
                    this.doNext = ProcessStages.ProcessChildSubNode;
                    return hasNext();
                }

                else {
                    this.doNext = null;
                    return false;
                }
            }

            if (this.doNext == ProcessStages.ProcessChildSubNode) {
                if (childrenSubNodeIter.hasNext()) {
                    this.next = childrenSubNodeIter.next();
                    return true;
                }
                else {
                    this.next = null;
                    this.doNext = ProcessStages.ProcessChildCurNode;
                    return hasNext();
                }
            }

            return false;
        }

        @Override
        public DTree<T> next() {
            return this.next;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
}
