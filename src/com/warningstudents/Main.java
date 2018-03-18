package com.warningstudents;

public class Main {

    public static void main(String[] args) {
        DTree<String> treeRoot = data();
        for (DTree<String> node : treeRoot) {
            String indent = createIndent(node.getLevel());
            System.out.println(indent + node.data);
        }


        search();
    }

    public static DTree<String> data() {
        DTree<String> root = new DTree<>("root");
        {
            DTree<String> node0 = root.addChild("node0");
            DTree<String> node1 = root.addChild("node1");
            DTree<String> node2 = root.addChild("node2");
            {
                DTree<String> node20 = node2.addChild(null);
                DTree<String> node21 = node2.addChild("node21");
                {
                    DTree<String> node210 = node21.addChild("node210");
                    DTree<String> node211 = node21.addChild("node211");
                }
            }
            DTree<String> node3 = root.addChild("node3");
            {
                DTree<String> node30 = node3.addChild("node30");
            }
        }

        return root;
    }

    private static String createIndent(int depth) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            sb.append('-');
        }
        return sb.toString();
    }


    private static void search() {
        Comparable<String> search = o -> {
            boolean nodeOk = o.contains("node3");
            return nodeOk ? 0 : 1;
        };

        DTree<String> treeRoot = data();
        DTree<String> found = treeRoot.findTreeNode(search);

        System.out.println("Found: " + found.data);
    }
}
