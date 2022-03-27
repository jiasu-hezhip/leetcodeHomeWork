package Day23合并有序链表;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution {

    /**
     * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     *
     * 输入：l1 = [1,2,4], l2 = [1,3,4]
     * 输出：[1,1,2,3,4,4]
     *
     * 首先这个并不难，但是如果题目不明确指出需要使用链表，也是比较难想到的
     * 从头开始对比每个链表，如果a > b 则b.next -> a
     * 好吧，写list定义有点费劲
     *
     * @param args
     */

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("/Users/togi/Downloads/day01-SpringCloud01/代码/leetcodeHomeWork/src/main/java/Day23合并有序链表/test.txt"));
        String a = sc.nextLine();
        String b = sc.nextLine();
        String[] s = a.split(" ");
        String[] s2= b.split(" ");
        ListNode node1 = new ListNode();
        ListNode yNode = new ListNode();
        yNode.next = node1;
        for (int i = 0; i < s.length; i++) {
            if (i + 1 >= s.length) {
                ListNode temp = getNeed(node1,i);
                temp.val = Integer.parseInt(s[i]);
                temp.next = null;
            } else {
                ListNode temp = getNeed(node1,i);
                temp.val = Integer.parseInt(s[i]);
                temp.next = new ListNode();
            }

        }

        ListNode node2 = new ListNode();
        ListNode yNode2 = new ListNode();
        yNode2.next = node2;
        for (int i = 0; i < s2.length; i++) {
            if (i+1 >= s2.length) {
                ListNode temp = getNeed(node2,i);
                temp.val = Integer.parseInt(s2[i]);
                temp.next = null;
            } else {
                ListNode temp = getNeed(node2,i);
                temp.val = Integer.parseInt(s2[i]);
                temp.next = new ListNode();
            }

        }

        ListNode result = mergeTwoLists(yNode.next,yNode2.next);

        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }

    }

    public static ListNode getNeed(ListNode node,int i) {
        if (i == 0) {
            return node;
        }else {
            ListNode temp = node;
            for (int j = 0; j < i; j++) {
                temp = temp.next;
            }
            return temp;
        }
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }


    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }


}
