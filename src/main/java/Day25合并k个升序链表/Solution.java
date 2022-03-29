package Day25合并k个升序链表;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Solution {

    /**
     * 给你一个链表数组，每个链表都已经按升序排列。
     * <p>
     * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
     * <p>
     * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
     * 输出：[1,1,2,3,4,4,5,6]
     * 解释：链表数组如下：
     * [
     * 1->4->5,
     * 1->3->4,
     * 2->6
     * ]
     * 将它们合并到一个有序链表中得到。
     * 1->1->2->3->4->4->5->6
     *
     * 首先这道题可以暴力求解，每两个链表直接使用归并排序，直到最后两个（想不出来就暴力求解）
     * 优化一下就是每次都合并两个链表，直到所有链表合并完
     *
     * 但是这道题因为是有序链表，所以可以有优先队列的方法，方法是每次都从k个链表的头节点中找到最小的一个
     * 这个方法不是人人都能想到的，首先需要了解   ***PriorityQueue***
     * 还需要构造一个能排序的类
     *
     *
     * // 1。构造一个能排序的类，由值和链表元素组成，排序根据值升序排序
     * class Status implements Comparable<Status> {
     *         int val;
     *         ListNode ptr;
     *
     *         Status(int val, ListNode ptr) {
     *             this.val = val;
     *             this.ptr = ptr;
     *         }
     *
     *         public int compareTo(Status status2) {
     *             return this.val - status2.val;
     *         }
     *     }
     *
     *     public ListNode mergeKLists(ListNode[] lists) {
     *     // 首先把所有的头节点放入优先级队列
     *         for (ListNode node: lists) {
     *             if (node != null) {
     *                 queue.offer(new Status(node.val, node));
     *             }
     *         }
     *
     *         ListNode head = new ListNode(0); //创建一个哑节点，最后返回时候用
     *         ListNode tail = head; // 从哑节点开始，构建链表
     *         while (!queue.isEmpty()) { //在优先级队列里面还有东西的时候
     *             Status f = queue.poll(); //弹出优先级队列的第一个元素
     *             tail.next = f.ptr; //添加到链表的下一位
     *             tail = tail.next; //自己作为指针下移一位
     *             if (f.ptr.next != null) { //如果弹出的元素有下一个链表节点加入优先级队列
     *                 queue.offer(new Status(f.ptr.next.val, f.ptr.next));
     *             }
     *         } // 直到队列中没有元素
     *         return head.next; //返回哑节点的下一位
     *     }
     *
     */
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("/Users/togi/Downloads/day01-SpringCloud01/代码/leetcodeHomeWork/src/main/java/Day25合并k个升序链表/test.txt"));
        ArrayList<String> list = new ArrayList<>();
        while (in.hasNextLine()) {
            list.add(in.nextLine());
        }
        ArrayList<ListNode> nodeList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            String[] split = list.get(i).split(" ");
            ListNode yNode = new ListNode();
            ListNode node = new ListNode();
            yNode.next = node;
            for (int j = 0; j < split.length; j++) {
                if ( j+1 >= split.length){
                    ListNode temp = getNeed(node,j);
                    temp.val = Integer.parseInt(split[j]);
                    temp.next = null;
                }else {
                    ListNode temp = getNeed(node,j);
                    temp.val = Integer.parseInt(split[j]);
                    temp.next = new ListNode();
                }
            }
            nodeList.add(yNode.next);
        }
        ListNode result = mergeKLists(nodeList);
        while (result.next != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }

    public static ListNode mergeKLists(ArrayList<ListNode> lists) {
        int size = lists.size();
        if (size == 0) {
            return null;
        }
        if (size == 1) {
            return lists.get(0);
        }
        int i = 0; // while循环标记
        ArrayList<ListNode> temp = new ArrayList<>();
        while (i < size -1) {
            if (i == 0 ){
                ListNode node = merge2List(lists.get(i),lists.get(i+1));
                temp.add(node);
            } else {
                ListNode node = merge2List(temp.get(i - 1),lists.get(i+1));
                temp.add(node);
            }
            i++;
        }
        return temp.get(temp.size()-1);
    }

    // 两个列表归并排序
    public static ListNode merge2List(ListNode l1, ListNode l2) {

        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {
            l1.next = merge2List(l1.next, l2);
            return l1;
        } else {
            l2.next = merge2List(l1, l2.next);
            return l2;
        }
    }

    public static ListNode getNeed(ListNode node, int i) {
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

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

}
