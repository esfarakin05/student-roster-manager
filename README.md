# Employee Roster — Sorted Linked List (Java)

A command-line employee record manager built for **CSCI 313 (Data Structures), Queens College**.

The program stores employee records in a **custom sorted linked list built from scratch** (no Java `LinkedList` ADT), keeping the list alphabetically ordered by last name as records are inserted.

## What it does

Running the program gives an interactive menu:

- **add** — enter a first name, last name, and ID; the record is inserted into its correct alphabetical position
- **search** — look up an employee by ID
- **display** — print the full roster in sorted order
- **quit** — exit the program

## How it's built

| File | Role |
|------|------|
| `Student.java` | Record class implementing `Comparable`. `compareTo` orders by last name, then first name; `equals` matches by ID. |
| `SortedLinkedList.java` | Hand-written singly linked list with a **dummy head node**. Supports `insert` (in sorted order), `delete`, `search`, `isEmpty`, and `toString`. |
| `Employee.java` | Main driver — handles the menu loop and user input, delegating all list operations to `SortedLinkedList`. |
| `docs/recap-hw3-4.txt` | Written analysis: pre-conditions, post-conditions, and runtimes for every method. |

## Complexity

- `insert`, `delete`, `search`, `toString` — **O(n)**
- `compareTo`, `equals`, `isEmpty` — **O(1)**

## Run it

```bash
javac Student.java SortedLinkedList.java Employee.java
java Employee
```

## What I learned

- Implementing `Comparable` and designing multi-key comparison logic
- Building a linked list manually and using a dummy node to simplify insertion/deletion edge cases
- Separating the data structure from the driver program so the main class only handles I/O
