
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        BST activeRoster = new BST();
        BST inactiveList = new BST();
        Queue waitlist = new Queue();
        Student[] IDList = new Student[100];
        int rosterLimit = 10;
        int rosterSize = 0;

        // Read from file
        try (BufferedReader reader = new BufferedReader(new FileReader("active.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                Student s = new Student(parts[0], parts[1], parts[2]);
                activeRoster.insert(s);
                IDList[Integer.parseInt(s.getId()) % 100] = s;
                rosterSize++;
            }
        } catch (IOException e) {
            System.out.println("active.txt not found. Starting with empty roster.");
        }

        while (true) {
            System.out.println("\nOptions: add / drop / searchName / searchID / display / waitlist / inactive / quit");
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
                if (rosterSize < rosterLimit) {
                    activeRoster.insert(s);
                    IDList[Integer.parseInt(id) % 100] = s;
                    rosterSize++;
                } else {
                    waitlist.enqueue(s);
                    System.out.println("Roster full. Added to waitlist.");
                }

            } else if (option.equals("drop")) {
                System.out.print("Enter student ID to drop: ");
                String id = input.nextLine();
                int idx = Integer.parseInt(id) % 100;
                Student s = IDList[idx];
                if (s != null && s.getId().equals(id)) {
                    IDList[idx] = null;
                    inactiveList.insert(s);
                    rosterSize--;
                    Student wait = waitlist.dequeue();
                    if (wait != null) {
                        activeRoster.insert(wait);
                        IDList[Integer.parseInt(wait.getId()) % 100] = wait;
                        rosterSize++;
                        System.out.println("Added from waitlist: " + wait);
                    }
                } else {
                    System.out.println("Student not found.");
                }

            } else if (option.equals("searchname")) {
                System.out.print("Last name: ");
                String last = input.nextLine();
                System.out.print("First name: ");
                String first = input.nextLine();
                Student temp = new Student(first, last, "");
                Student found = activeRoster.search(temp);
                System.out.println(found != null ? found : "Not found.");

            } else if (option.equals("searchid")) {
                System.out.print("Enter ID: ");
                int id = Integer.parseInt(input.nextLine());
                Student s = IDList[id % 100];
                System.out.println((s != null && s.getId().equals(String.valueOf(id))) ? s : "Not found.");

            } else if (option.equals("display")) {
                System.out.println("Active Roster:");
                activeRoster.inOrderPrint();

            } else if (option.equals("waitlist")) {
                System.out.println("Waitlist:");
                waitlist.print();

            } else if (option.equals("inactive")) {
                System.out.println("Inactive students:");
                inactiveList.inOrderPrint();

            } else if (option.equals("quit")) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("active.txt"))) {
                    for (Student s : activeRoster.getAll()) {
                        writer.write(s.getFirstName() + "," + s.getLastName() + "," + s.getId());
                        writer.newLine();
                    }
                } catch (IOException e) {
                    System.out.println("Error writing to active.txt");
                }

                try (BufferedWriter writer = new BufferedWriter(new FileWriter("waitlist.txt"))) {
                    for (Student s : waitlist.getAll()) {
                        writer.write(s.getFirstName() + "," + s.getLastName() + "," + s.getId());
                        writer.newLine();
                    }
                } catch (IOException e) {
                    System.out.println("Error writing to waitlist.txt");
                }

                try (BufferedWriter writer = new BufferedWriter(new FileWriter("inactive.txt"))) {
                    for (Student s : inactiveList.getAll()) {
                        writer.write(s.getFirstName() + "," + s.getLastName() + "," + s.getId());
                        writer.newLine();
                    }
                } catch (IOException e) {
                    System.out.println("Error writing to inactive.txt");
                }

                System.out.println("Data saved. Goodbye!");
                break;
            }
        }
        input.close();
    }
}
