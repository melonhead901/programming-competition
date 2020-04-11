import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StudentTest {

    @Test
    public void testGpaOrderingSimple() {
        Student s1 = new Student(1, "mary", 4.0);
        Student s2 = new Student(2, "james", 3.0);
        assertThat(Student.COMPARATOR.compare(s1, s2)).isNegative();
    }

    @Test
    public void testGpaOrderingComplex() {
        Student s1 = Student.createFromStringArr("ENTER Shafaet 3.7 35".split(" "));
        Student s2 = Student.createFromStringArr("ENTER Maria 3.6 46".split(" "));
        assertThat(Student.COMPARATOR.compare(s1, s2)).isNegative();
    }

}
