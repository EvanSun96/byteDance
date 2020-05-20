//reverse a simple linkedlist
class Solution {
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = head;
        while(cur.next != null){ //注意 因为链表的长度是未知的 因此我们在这道题中只能用while 而只有当移动的步数已知 比如LC092 我们才可以用for
            ListNode temp = cur.next;
            cur.next = temp.next;
            temp.next = pre.next;
            pre.next = temp;//完成上面的四步 cur每次都停留在已经被反转的部分链表的末尾
        }

        return dummy.next;
    }
}

//反转m到n范围内的所有节点
class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode p1 = dummy;
        ListNode p2 = head;
        for(int i = 1; i < m; i++){
            p1 = p1.next;
            p2 = p2.next;
        }
        for(int i = 0; i < n - m; i++){
            ListNode temp = p2.next;
            p2.next = temp.next;
            temp.next = p1.next;
            p1.next = temp;
        }
        return dummy.next;

    }
}
//reverse linkedlist in K group
public ListNode reverseKGroup(ListNode head, int k) {
    ListNode begin;
    if (head==null || head.next ==null || k==1)
    	return head;
    ListNode dummyhead = new ListNode(-1);
    dummyhead.next = head;
    begin = dummyhead;
    int i=0;
    while (head != null){
    	i++;
    	if (i%k == 0){
    		begin = reverse(begin, head.next);
    		head = begin.next;
    	} else {
    		head = head.next;
    	}
    }
    return dummyhead.next;

}

public ListNode reverse(ListNode begin, ListNode end){
	ListNode curr = begin.next;
	ListNode next, first;
	ListNode prev = begin;
	first = curr;
	while (curr!=end){
		next = curr.next;
		curr.next = prev;
		prev = curr;
		curr = next;
	}
	begin.next = prev;
	first.next = curr;
	return first;
}
