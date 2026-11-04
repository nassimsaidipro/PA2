public class Stack {
    private char[] elements;
    private int top; //number of elements in the stack

    public Stack() {
        elements = new char[2];
    }

    public void push(char c) {
        if (isFull()) {
            expand();
        }
        elements[top++] = c;
    }

    public char pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return elements[--top];
    }

    public char top() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return elements[top - 1];
    }

    public int size() {
        return top;
    }

    public boolean isEmpty() {
        return top == 0;
    }

    public boolean isFull() {
        return top == elements.length;
    }

    private void expand() {
        expansionInfo();
        char[] bigger = new char[elements.length * 2];
        for (int i = 0; i < top; i++) {
            bigger[i] = elements[i];
        }
        elements = bigger;
    }

    private void expansionInfo() {
        System.out.println("Stack is full. Its current size is " + elements.length);
        System.out.println("Will be expanding the size to " + elements.length * 2);
    }
}
