
// HW #3-4 - Full user program using SortedLinkedList
import java.util.Scanner;

public class Employee {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        SortedLinkedList empList = new SortedLinkedList();

        while (true) {
            System.out.println("Options: add / search / display / quit");
            System.out.print("Your choice: ");
            String option = input.nextLine().trim().toLowerCase();

            if (option.equals("add")) {
                System.out.print("First Name: ");
                String first = input.nextLine();
                System.out.print("Last Name: ");
                String last = input.nextLine();
                System.out.print("ID: ");
                String id = input.nextLine();
                Student s = new Student(first, last, id);
                empList.insert(s);
                System.out.println("Employee added.\n");

            } else if (option.equals("search")) {
                System.out.print("Enter ID to search: ");
                String id = input.nextLine();
                Student temp = new Student("", "", id);
                Student found = empList.search(temp);
                if (found != null) {
                    System.out.println("Found: " + found + "\n");
                } else {
                    System.out.println("Not found.\n");
                }

            } else if (option.equals("display")) {
                System.out.println("Employee List:");
                System.out.println(empList.toString());

            } else if (option.equals("quit")) {
                System.out.println("Done.");
                break;

            } else {
                System.out.println("Invalid input.\n");
            }
        }

        input.close();
    }
}
