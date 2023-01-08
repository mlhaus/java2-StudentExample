package final_exam;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    Student student;
    Student student1;
    Student student2;
    Student student3;

    @BeforeEach
    void setUp(){
        student = new Student();
        student1 = new Student("k9999999", 4);
        student2 = new Student("k0000000", 2);
        student3 = new Student("k9999999", 0);
    }

    @Test
    void getID() {
        assertEquals("k0000000", student.getID());
    }

    @Test
    void setID() {
        //good tests
        student.setID("k0000001");
        assertEquals("k0000001", student.getID());

        //bad tests
        assertThrows(IllegalArgumentException.class, () -> student.setID(""));
        assertThrows(IllegalArgumentException.class, () -> student.setID("k000001"));
        assertThrows(IllegalArgumentException.class, () -> student.setID("j0000000"));
    }

    @Test
    void getGpa() {
        assertEquals(0, student.getGpa());
    }

    @Test
    void setGpa() {
        //good tests
        student.setGpa(4);
        assertEquals(4, student.getGpa());
        student.setGpa(0);
        assertEquals(0, student.getGpa());

        //bad tests
        assertThrows(IllegalArgumentException.class, () -> student.setGpa(-0.01));
        assertThrows(IllegalArgumentException.class, () -> student.setGpa(4.01));
    }

    @Test
    void compareTo() {
        assertTrue(student.compareTo(student1) < 0);
        assertTrue(student.compareTo(student2) < 0);
        assertTrue(student.compareTo(student3) == 0);
        assertTrue(student1.compareTo(student) > 0);
        assertTrue(student1.compareTo(student2) > 0);
        assertTrue(student2.compareTo(student) > 0);
        assertTrue(student2.compareTo(student1) < 0);

    }

    @Test
    void testToString() {
        assertEquals(String.format("Student{id='%s', gpa=%s}"
                , student.getID(), student.getGpa()), student.toString());
    }
}