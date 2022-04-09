package Day36二叉树中序遍历;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {

    /**
     * 给定一个二叉树的根节点 root ，返回 它的 中序 遍历 。
     *
     * 输入：root = [1,null,2,3]
     * 输出：[1,3,2]
     *
     */

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {

        TreeNode treeNode = new TreeNode(1);
        treeNode.left = null;
        treeNode.right = new TreeNode(2);
        treeNode.right.left = new TreeNode(3);
        treeNode.right.right = new TreeNode(4);
        final List<Integer> integers = inorderTraversal(treeNode);
        System.out.print("[");
        for (int i = 0; i < integers.size(); i++) {
            if (i +1 > integers.size() -1) {
                System.out.print(integers.get(i));
            }else {
                System.out.print(integers.get(i) + ",");
            }
        }
        System.out.print("]");
    }

    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        inorder(root, res);
        return res;
    }

    public static void inorder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        inorder(root.left, res);
        res.add(root.val);
        inorder(root.right, res);
    }




}
