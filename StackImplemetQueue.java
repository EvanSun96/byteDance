//stack implement queue
//it is very easy, however, we will take O(n) for both pop and push
//now we have a new way of both takes O(1) in pop and push
class MyQueue {
    Stack<Integer> stack;
    Stack<Integer> reverseStack;
    int front;

    /** Initialize your data structure here. */
    public MyQueue() {
        stack = new Stack<>();
        reverseStack = new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        if (stack.isEmpty()) { //make a copy the first
            front = x;
        }
        stack.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if (reverseStack.isEmpty()) {
            while (!stack.isEmpty()) {
                reverseStack.push(stack.pop());
            }
        }
        return reverseStack.pop();

    }

    /** Get the front element. */
    public int peek() {
        if (!reverseStack.isEmpty()) {
            return reverseStack.peek();
        }
        return front;
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stack.isEmpty() && reverseStack.isEmpty(); //we don't keep all data in any of stacks sepecifically
    }
}
//use queue implement stack
class MyStack {
    LinkedList<Integer> queue;

    /** Initialize your data structure here. */
    public MyStack() {
        queue = new LinkedList<>();

    }

    //if we implement push in a easy way, then we have to implement pop and peek in a hard way,
    //but if we implement push in a harder way, then the other 2 will be implemented in a simple way

    /** Push element x onto stack. */
    public void push(int x) {
        queue.add(x);
        int size = queue.size();
        while (size > 1) {
            queue.add(queue.remove());
            size--;
        }
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return queue.remove();

    }

    /** Get the top element. */
    public int top() { // we want to pop the last put in queue, but we can only pop the first one put there
        return queue.peek();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue.isEmpty();

    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
