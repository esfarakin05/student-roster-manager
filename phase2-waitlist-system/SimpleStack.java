
import java.util.EmptyStackException;

public class SimpleStack<T> {
    private Object[] data;
    private int top;

    public SimpleStack() {
        data = new Object[10];
        top = -1;
    }

    public void push(T item) {
        if (top == data.length - 1) throw new StackOverflowError();
        data[++top] = item;
    }

    public T pop() {
        if (top == -1) throw new EmptyStackException();
        return (T) data[top--];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public T peek() {
        if (top == -1) throw new EmptyStackException();
        return (T) data[top];
    }
}
