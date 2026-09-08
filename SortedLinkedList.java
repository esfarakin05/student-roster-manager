
// HW #2 - SortedLinkedList using dummy node
public class SortedLinkedList {
    private class Node {
        Student data;
        Node next;

        Node(Student data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node dummy;

    public SortedLinkedList() {
        dummy = new Node(null); // dummy node at start
    }

    public boolean isEmpty() {
        return dummy.next == null;
    }

    public void insert(Student s) {
        Node prev = dummy;
        Node curr = dummy.next;
        while (curr != null && curr.data.compareTo(s) < 0) {
            prev = curr;
            curr = curr.next;
        }
        Node newNode = new Node(s);
        newNode.next = curr;
        prev.next = newNode;
    }

    public boolean delete(Student s) {
        Node prev = dummy;
        Node curr = dummy.next;
        while (curr != null) {
            if (curr.data.equals(s)) {
                prev.next = curr.next;
                return true;
            }
            prev = curr;
            curr = curr.next;
        }
        return false;
    }

    public Student search(Student s) {
        Node curr = dummy.next;
        while (curr != null) {
            if (curr.data.equals(s)) return curr.data;
            curr = curr.next;
        }
        return null;
    }

    public String toString() {
        StringBuilder list = new StringBuilder();
        Node curr = dummy.next;
        while (curr != null) {
            list.append(curr.data).append("\n");
            curr = curr.next;
        }
        return list.toString();
    }
}
