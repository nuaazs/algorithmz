package class13;

import java.util.LinkedList;
// 判断是否为完全二叉树
// 测试链接 : https://leetcode.com/problems/check-completeness-of-a-binary-tree/

public class IsCBT {
    public static class TreeNode {
        public int value;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int value) {
            this.value = value;
        }
    }

    public static boolean isCompleteTree1(TreeNode head) {
        if (head == null) {
            return true;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        boolean leaf = false;
        TreeNode l = null;
        TreeNode r = null;
        queue.add(head);
        while (!queue.isEmpty()) {
            head = queue.poll();
            l = head.left;
            r = head.right;
            // 如果遇到了不双全的节点之后，又发现当前节点不是叶节点
            if ((leaf && (l != null || r != null)) || (l == null && r != null)) {
                return false;
            }
            if (l != null) {
                queue.add(l);
            }
            if (r != null) {
                queue.add(r);
            }
            if (l == null || r == null) {
                leaf = true;
            }
        }
        return true;
    }

    // 二叉树递归
    public static boolean isCompleteTree2(TreeNode head) {
        return process(head).isCBT;
    }

    public static class Info {
        public boolean isCBT;
        public boolean isFull;
        public int height;

        public Info(boolean full, boolean cbt, int h) {
            this.isFull = full;
            this.isCBT = cbt;
            this.height = h;
        }
    }

    public static Info process(TreeNode x) {
        if (x == null) {
            return new Info(true, true, 0);
        }
        Info left = process(x.left);
        Info right = process(x.right);
        int height = Math.max(left.height, right.height) + 1;
        boolean isFull = left.isFull && right.isFull && left.height == right.height;
        boolean isCBT = false;
        if (isFull) {
            isCBT = true;
        }
        if (left.isCBT && right.isFull && left.height == right.height + 1) {
            isCBT = true;
        }
        if (left.isFull && right.isFull && left.height == right.height + 1) {
            isCBT = true;
        }
        if (left.isFull && right.isCBT && left.height == right.height) {
            isCBT = true;
        }
        return new Info(isFull, isCBT, height);
    }

    public static TreeNode generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    public static TreeNode generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        TreeNode head = new TreeNode((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 10000;
        for (int i = 0; i < testTimes; i++) {
            TreeNode head = generateRandomBST(maxLevel, maxValue);
            if (isCompleteTree1(head) != isCompleteTree2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
