public class MyStack<E extends Comparable<? super E>> {

    private final MyDblLinkedList<E> list = new MyDblLinkedList<E>();

    // ADD ALL STACK FUNCTIONS

    /*
     * PRE: Accepts the item of type E to be added to the stack.
     * POST: Adds the item to the top of the stack and increases the size.
     */
    public void push(E item) {
        list.addLast(item);
    }

    /*
     * PRE: None
     * POST: Removes and returns the top element of the stack. Throws
     * IllegalStateException if empty.
     */
    public E pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty.");
        }
        return list.removeLast();
    }

    /*
     * PRE: None
     * POST: Returns the top element of the stack without removing it. Throws
     * IllegalStateException if empty.
     */
    public E peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty.");
        }
        return list.getLast();
    }

    /*
     * PRE: None
     * POST: Returns the number of elements in the stack.
     */
    public int size() {
        return list.size();
    }

    /*
     * PRE: None
     * POST: Returns true if the stack contains no elements, false otherwise.
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /*
     * PRE: None
     * POST: Removes all elements from the stack.
     */
    public void clear() {
        list.clear();
    }

    /*
     * PRE: None
     * POST: Returns a String representation of the stack contents in list order
     */
    public String stackString() {
        return list.toString();
    }

}
