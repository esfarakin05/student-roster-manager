
import java.util.Scanner;

public class StudentRoster {
    public static void main(String[] args) {
        SortedLinkedList roster = new SortedLinkedList();
        CircularQueue<Student> waitList = new CircularQueue<>(10);
        SimpleStack<Student> inactiveList = new SimpleStack<>();
        Scanner sc = new Scanner(System.in);
        int count = 0;

        while (true) {
            System.out.println("Options: add / drop / search / display / inactive / quit");
            System.out.print("Enter choice: ");
            String input = sc.nextLine().toLowerCase();

            if (input.equals("add")) {
                System.out.print("First: ");
                String first = sc.nextLine();
                System.out.print("Last: ");
                String last = sc.nextLine();
                System.out.print("ID: ");
                String id = sc.nextLine();
                Student s = new Student(first, last, id);

                if (count < 10) {
                    roster.insert(s);
                    count++;
                } else {
                    waitList.enqueue(s);
                    System.out.println("Added to waitlist.");
                }

            } else if (input.equals("drop")) {
                System.out.print("Enter ID to drop: ");
                String id = sc.nextLine();
                Student s = new Student("", "", id);
                boolean removed = roster.delete(s);
                if (removed) {
                    count--;
                    inactiveList.push(s);
                    if (!waitList.isEmpty()) {
                        Student fromWait = waitList.dequeue();
                        roster.insert(fromWait);
                        count++;
                        System.out.println("Moved from waitlist: " + fromWait);
                    }
                } else {
                    System.out.println("Student not found.");
                }

            } else if (input.equals("search")) {
                System.out.print("Enter ID to search: ");
                String id = sc.nextLine();
                Student temp = new Student("", "", id);
                Student found = roster.search(temp);
                if (found != null) {
                    System.out.println("Found: " + found);
                } else {
                    System.out.println("Not found.");
                }

            } else if (input.equals("display")) {
                System.out.println("Roster:");
                System.out.println(roster.toString());

            } else if (input.equals("inactive")) {
                System.out.println("Inactive Students (most recent first):");
                while (!inactiveList.isEmpty()) {
                    System.out.println(inactiveList.pop());
                }

            } else if (input.equals("quit")) {
                break;
            }
        }

        sc.close();
    }
}
