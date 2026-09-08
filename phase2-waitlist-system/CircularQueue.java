
public class CircularQueue<T> {
    private Object[] data;
    private int front, rear, size;

    public CircularQueue(int capacity) {
        data = new Object[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }

    public void enqueue(T item) {
        if (size == data.length) return;
        rear = (rear + 1) % data.length;
        data[rear] = item;
        size++;
    }

    public T dequeue() {
        if (size == 0) return null;
        T item = (T) data[front];
        front = (front + 1) % data.length;
        size--;
        return item;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T peek() {
        if (size == 0) return null;
        return (T) data[front];
    }

    public int size() {
        return size;
    }
}
