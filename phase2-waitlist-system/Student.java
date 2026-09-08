
public class Student implements Comparable<Student> {
    private String firstName;
    private String lastName;
    private String id;

    public Student(String firstName, String lastName, String id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getId() { return id; }

    public void setFirstName(String fn) { firstName = fn; }
    public void setLastName(String ln) { lastName = ln; }
    public void setId(String i) { id = i; }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Student)) return false;
        Student other = (Student) obj;
        return id.equals(other.id);
    }

    @Override
    public int compareTo(Student other) {
        int last = this.lastName.compareToIgnoreCase(other.lastName);
        if (last != 0) return last;
        return this.firstName.compareToIgnoreCase(other.firstName);
    }

    @Override
    public String toString() {
        return lastName + ", " + firstName + " (ID: " + id + ")";
    }
}
