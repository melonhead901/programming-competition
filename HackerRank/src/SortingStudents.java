import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Scanner;

class Student {
    static final Comparator<Student> COMPARATOR = Comparator.comparing(Student::getCGPA)
            .reversed()
            .thenComparing(Student::getName)
            .thenComparing(Student::getID);

    final int id;
    final double gpa;
    final String name;

    static Student createFromStringArr(String[] stringArr) {
        //   0    1    2   3
        // ENTER John 3.75 50
        return new Student(Integer.parseInt(stringArr[3]), stringArr[1], Double.parseDouble(stringArr[2]));
    }

    public Student(int id, String name, double gpa) {
        this.id = id;
        this.gpa = gpa;
        this.name = name;
    }

    public int getID() {
        return id;
    }

    public double getCGPA() {
        return gpa;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id && Double.compare(student.gpa, gpa) == 0 && Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, gpa, name);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                '}';
    }
}

class Priorities {

    private final PriorityQueue<Student> studentsToServe;

    public Priorities() {
        studentsToServe = new PriorityQueue<>(Student.COMPARATOR);
    }

    public List<Student> getStudents(List<String> events) {
        for (String event : events) {
            String[] split = event.split(" ");
            switch (split[0]) {
                case "ENTER":
                    addStudent(split);
                    break;
                case "SERVED":
                    serveTopStudent();
                    break;
                default:
                    throw new IllegalStateException(String.format("Unexpected state %s", split[0]));
            }
        }
        List<Student> remaining = new ArrayList<>();
        while (!studentsToServe.isEmpty()) {
            remaining.add(studentsToServe.poll());
        }
        return remaining;
    }

    private void serveTopStudent() {
        studentsToServe.poll();
    }

    private void addStudent(String[] event) {
        studentsToServe.offer(Student.createFromStringArr(event));
    }
}

public class SortingStudents {
    private static final Scanner scan = new Scanner(System.in);
    private static final Priorities priorities = new Priorities();

    public static void main(String[] args) {
        int totalEvents = Integer.parseInt(scan.nextLine());
        List<String> events = new ArrayList<>();

        while (totalEvents-- != 0) {
            String event = scan.nextLine();
            events.add(event);
        }

        List<Student> students = priorities.getStudents(events);

        if (students.isEmpty()) {
            System.out.println("EMPTY");
        } else {
            for (Student st : students) {
                System.out.println(st.getName());
            }
        }
    }
}
