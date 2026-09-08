# Student Roster Manager (Java)

A command-line student enrollment system built in three phases, each one replacing or extending the data layer with structures written from scratch — sorted linked list, stack, circular queue, binary search tree, and a hash-indexed ID table — ending with full file persistence between sessions.

## The three phases

### Phase 1 — Sorted roster (`phase1-sorted-roster/`)
The core: student records stored in a **hand-built sorted linked list** (dummy head node, no Java `LinkedList` ADT). Records stay alphabetically ordered by last name as they're inserted. Menu supports add, search by ID, display, quit.

### Phase 2 — Waitlist and drop tracking (`phase2-waitlist-system/`)
The roster gets a capacity limit of 10. Two new structures handle the overflow:
- **Circular array queue** — the waitlist. When someone drops, the first waitlisted student is automatically promoted (FIFO).
- **Array-based stack** — dropped students, viewable most-recent-first (LIFO).

### Phase 3 — BST, hash lookup, and persistence (`phase3-bst-persistence/`)
The final version replaces the linked list with a **binary search tree** (recursive insert/search, in-order traversal for sorted display) and adds:
- **Hash-indexed ID table** — students are placed in a 100-slot array by `ID % 100`, so lookups by ID are O(1) while name searches go through the BST. Two access paths, two structures.
- **File persistence** — the roster loads from `active.txt` on startup and writes active, waitlist, and inactive lists to separate CSV-style text files on exit, so data survives between runs.

## Complexity summary

| Operation | Phase 1–2 | Phase 3 |
|-----------|-----------|---------|
| Insert (by name) | O(n) linked list | O(log n) avg BST |
| Search by name | O(n) | O(log n) avg |
| Search by ID | O(n) | **O(1)** via hash index |
| Waitlist enqueue/dequeue | O(1) | O(1) |

## Run it

Each phase is self-contained. For the final version:

```bash
cd phase3-bst-persistence
javac *.java
java Main
```

Add some students, quit, and re-run — the roster reloads from `active.txt`.

## What I learned

- Trading up data structures as requirements grow: list → BST for faster search, plus a hash index when one access pattern (ID lookup) needs to be constant-time
- Keeping two structures in sync (BST + hash table) when records are added and dropped
- Designing simple flat-file persistence: parsing CSV lines on load, writing state back on exit
- Separating the driver (menu + I/O) from the data structures that do the work
