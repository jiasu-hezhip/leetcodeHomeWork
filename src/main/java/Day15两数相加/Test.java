package Day15两数相加;

/**
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 *
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 *
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807.
 *
 * 示例 2：
 * 输入：l1 = [0], l2 = [0]
 * 输出：[0]
 *
 * 示例 3：
 * 输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * 输出：[8,9,9,9,0,0,0,1]
 *
 */

public class Test {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(9,new ListNode(9,new ListNode(9)));
        ListNode l2 = new ListNode(9,new ListNode(9));

        ListNode l3 = addTwoNumbers(l1,l2);
        System.out.println(l3.val);
        System.out.println(l3.next.val);
        System.out.println(l3.next.next.val);
        System.out.println(l3.next.next.next.val);
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode listNode = null;
        ListNode listNode2 = null;
        int d = 0;
        while (l1!=null || l2!=null){
            int a = l1!=null?l1.val:0;
            int b = l2!=null?l2.val:0;
            int c = a+b+d;
            d = c/10;
            if (listNode == null) {
                listNode =  listNode2 = new ListNode(c%10);
            } else {
                listNode2.next = new ListNode(c%10);
                listNode2 = listNode2.next;
            }
            if (l1 != null){
                l1 = l1.next;
            }
            if (l2 != null){
                l2 = l2.next;
            }
        }
        if (d > 0){
            listNode2.next = new ListNode(d);
        }
        return listNode;
    }


     public static class ListNode {
         int val;
         ListNode next;
         ListNode() {}
         ListNode(int val) { this.val = val; }
         ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     }


}
