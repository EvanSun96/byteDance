//check if there is a cycle
public class Solution {
    public boolean hasCycle(ListNode head) {
        if(head == null) return false;

        ListNode p1 = head;
        ListNode p2 = head;
        while(p2 != null && p2.next != null){
            p1 = p1.next;
            p2 = p2.next.next;
            if(p1 == p2){
                return true;
            }
        }
        return false;
    }
}

//返回循环开始的node
//我知道怎么做 但是为什么这样做？
public class Solution {
    public ListNode detectCycle(ListNode head) {
        if(head == null || head.next == null) return null;

        ListNode slow = head;
        ListNode fast = head;

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;

            if(slow == fast){
                ListNode slow2 = head;
                while(slow != slow2){
                    slow = slow.next;
                    slow2 = slow2.next;
                }
                return slow;
            }
        }
        return null;
    }
}
