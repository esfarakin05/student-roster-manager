
import java.util.*;
public class BST {
    private class Node {
        Student data;
        Node left, right;

        Node(Student data) {
            this.data = data;
        }
    }

    private Node root;

    public void insert(Student s) {
        root = insertRecursive(root, s);
    }

    private Node insertRecursive(Node current, Student s) {
        if (current == null) return new Node(s);
        if (s.compareTo(current.data) < 0)
            current.left = insertRecursive(current.left, s);
        else
            current.right = insertRecursive(current.right, s);
        return current;
    }

    public Student search(Student s) {
        return searchRecursive(root, s);
    }

    private Student searchRecursive(Node current, Student s) {
        if (current == null) return null;
        if (s.equals(current.data)) return current.data;
        if (s.compareTo(current.data) < 0)
            return searchRecursive(current.left, s);
        else
            return searchRecursive(current.right, s);
    }

    public void inOrderPrint() {
        inOrderRecursive(root);
    }

    private void inOrderRecursive(Node node) {
        if (node != null) {
            inOrderRecursive(node.left);
            System.out.println(node.data);
            inOrderRecursive(node.right);
        }
    }

    public List<Student> getAll() {
        List<Student> list = new ArrayList<>();
        collectAll(root, list);
        return list;
    }

    private void collectAll(Node node, List<Student> list) {
        if (node != null) {
            collectAll(node.left, list);
            list.add(node.data);
            collectAll(node.right, list);
        }
    }
}
