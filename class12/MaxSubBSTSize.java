package class12;

import java.util.ArrayList;

// 在线测试链接 : https://leetcode.com/problems/largest-bst-subtree
public class MaxSubBSTSize {
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int value) {
            val = value;
        }
    }

    public static int largestBSTSubtree(TreeNode head) {
        if (head == null) {
            return 0;
        }
        return process(head).maxBSTSubtreeSize;
    }

    public static class Info {
        public int maxBSTSubtreeSize;
        public int allSize;
        public int min;
        public int max;

        public Info(int m, int a, int ma, int mi) {
            this.maxBSTSubtreeSize = m;
            this.allSize = a;
            this.min = mi;
            this.max = ma;
        }
    }

    public static Info process(TreeNode x) {
        if (x == null) {
            return null;
        }

        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        int max = x.val;
        int min = x.val;
        int allSize = 1;
        if (leftInfo != null) {
            allSize += leftInfo.allSize;
            min = Math.min(min, leftInfo.min);
            max = Math.max(max, leftInfo.max);
        }
        if (rightInfo != null) {
            allSize += rightInfo.allSize;
            min = Math.min(min, rightInfo.min);
            max = Math.max(max, rightInfo.max);
        }
        int p1 = -1;
        if (leftInfo != null) {
            p1 = leftInfo.maxBSTSubtreeSize;
        }

        int p2 = -1;
        if (rightInfo != null) {
            p2 = rightInfo.maxBSTSubtreeSize;
        }

        int p3 = -1;
        boolean leftBST = leftInfo == null ? true : (leftInfo.maxBSTSubtreeSize == leftInfo.allSize);
        boolean rightBST = rightInfo == null ? true : (rightInfo.maxBSTSubtreeSize == rightInfo.allSize);
        if (leftBST && rightBST) {

            boolean leftMaxLessX = leftInfo == null ? true : (leftInfo.max < x.val);
            boolean rightMinMoreX = rightInfo == null ? true : (x.val < rightInfo.min);
            if (leftMaxLessX && rightMinMoreX) {
                int leftSize = leftInfo == null ? 0 : leftInfo.allSize;
                int rightSize = rightInfo == null ? 0 : rightInfo.allSize;
                p3 = leftSize + rightSize + 1;
            }
        }
        return new Info(Math.max(p1, Math.max(p2, p3)), allSize, max, min);
    }

    public static int right(TreeNode head) {
        if (head == null) {
            return 0;
        }
        int h = getBSTSize(head);
        if (h != 0) {
            return h;
        }
        return Math.max(right(head.left), right(head.right));
    }

    public static int getBSTSize(TreeNode head) {
        if (head == null) {
            return 0;
        }
        ArrayList<TreeNode> arr = new ArrayList<>();
        in(head, arr);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).val <= arr.get(i - 1).val) {
                return 0;
            }
        }
        return arr.size();
    }

    public static void in(TreeNode head, ArrayList<TreeNode> arr) {
        if (head == null) {
            return;
        }
        in(head.left, arr);
        arr.add(head);
        in(head.right, arr);
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
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 10000;
        System.out.println("start");
        for (int i = 0; i < testTimes; i++) {
            TreeNode head = generateRandomBST(maxLevel, maxValue);
            int right = right(head);
            int my = largestBSTSubtree(head);
            if (right != my) {
                System.out.println("Wrong");
                System.out.println("right: " + right);
                System.out.println("my: " + my);
                System.out.println("head: " + head);
                break;
            }
        }
        System.out.println("test end");
    }
}