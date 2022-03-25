package Day21单向链表删除倒数第n个节点;

public class Solution {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }


    public static void main(String[] args) {
        /**
         * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
         *
         * 第一个想法是首先遍历一次得到全部长度，然后再去删除节点
         *
         * 第二个想法是遍历的时候自己构造一个list，遍历完成list也构建完成，之后删除一下对应位置返回头节点就行 （对应官方的栈思想）
         *
         * 官方的第三个解法是快慢指针，即初始的时候制定两个指针都指向头节点，然后快指针前进指定的数n
         * 之后两个指针一起遍历，直到快指针到头，慢指针则刚好到达快指针-n的位置
         * 移除该元素即可
         *
         * 所有解时间复杂度都是o（n）可以说没啥好的方法
         *
         * 尤其需要注意在处理链表结构时可以新增一个节点指向列表的!!头节点!!，这样返回时就不用担心处理到哪了
         *
         */



    }


    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        int length = getLength(head);
        ListNode cur = dummy;
        for (int i = 1; i < length - n + 1; ++i) {
            cur = cur.next;
        }
        cur.next = cur.next.next;
        ListNode ans = dummy.next;
        return ans;
    }

    public static int getLength(ListNode head) {
        int length = 0;
        while (head != null) {
            ++length;
            head = head.next;
        }
        return length;
    }

    public ListNode removeNthFromEndByTwoPointer(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head); // 创建哑节点
        ListNode first = head; // 定义快指针
        ListNode second = dummy; // 定义慢指针
        for (int i = 0; i < n; ++i) {
            first = first.next;
        } // 先让快指针移动n位
        while (first != null) {
            first = first.next;
            second = second.next;
        }// 遍历所有链表长度
        second.next = second.next.next; // 移除对应元素
        ListNode ans = dummy.next; // 返回头节点
        return ans;
    }

}
