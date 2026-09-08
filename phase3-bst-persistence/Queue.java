
import java.util.LinkedList;

public class Queue {
    private LinkedList<Student> list;

    public Queue() {
        list = new LinkedList<>();
    }

    public void enqueue(Student s) {
        list.addLast(s);
    }

    public Student dequeue() {
        return list.isEmpty() ? null : list.removeFirst();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void print() {
        for (Student s : list) {
            System.out.println(s);
        }
    }

    public LinkedList<Student> getAll() {
        return list;
    }
}
