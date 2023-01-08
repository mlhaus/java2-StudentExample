package java1refresher;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
        private Person person;

        @BeforeEach
        void setUp () {
            person = new Person();
        }

        @Test
        void getFirstName () {
            assertEquals(Person.DEFAULT_FIRST, person.getFirstName());
        }

        @Test
        void setFirstName () {
            person.setFirstName("Marvin");
            assertEquals("Marvin", person.getFirstName());

            // Bad tests
            Exception e = assertThrows(IllegalArgumentException.class, () -> person.setFirstName("1"));
            assertEquals(Person.NO_NUMBER_ERROR, e.getMessage());
            e = assertThrows(IllegalArgumentException.class, () -> person.setFirstName(""));
            assertEquals(Person.EMPTY_ERROR , e.getMessage());
        }

        @Test
        void getLastName () {
            assertEquals(Person.DEFAULT_LAST, person.getLastName());
        }

        @Test
        void setLastName () {
            fail();
        }

        @Test
        void getHeightInInches () {
            assertEquals(Person.DEFAULT_HEIGHT, person.getHeightInInches());
        }

        @Test
        void setHeightInInches () {
            //good data
            person.setHeightInInches(0);
            assertEquals(0, person.getHeightInInches());
            person.setHeightInInches(100);
            assertEquals(100, person.getHeightInInches());

            // bad data
            assertThrows(IllegalArgumentException.class, () -> person.setHeightInInches(-1));
            assertThrows(IllegalArgumentException.class, () -> person.setHeightInInches(101));
        }

        @Test
        void getWeightInPounds () {
            assertEquals(Person.DEFAULT_WEIGHT, person.getWeightInPounds());
        }
        @Test
        void setWeightInPounds () {
            fail();
        }

        @Test
        void getDateOfBirth () {
            assertEquals(Person.DEFAULT_DOB, person.getDateOfBirth());
        }

        @Test
        void setDateOfBirth () {
            //good
            LocalDateTime yesterday = LocalDateTime.now().minusDays(1);
            person.setDateOfBirth(yesterday);
            assertTrue(person.getDateOfBirth().equals(yesterday));

            //bad
            LocalDateTime tomorrow = LocalDateTime.now().plusDays(1);
            assertThrows(IllegalArgumentException.class, () -> person.setDateOfBirth(tomorrow));
        }

        @Test
        void isAlive () {
            fail();
        }

        @Test
        void setAlive () {
            fail();
        }

        @Test
        void testToString () {
            assertEquals(String.format("Person{firstName='%s', lastName='%s'}",
                    Person.DEFAULT_FIRST, Person.DEFAULT_LAST), person.toString());
        }

        /*
        @Test
        void testCompareTo(){
            Person person1 = new Person("Nathan", "Zumsande");
            Person person2 = new Person("Marc", "Hauschildt");
            Person person3 = new Person("Mike", "Zumsande");
            assertTrue(person1.compareTo(person2) > 0);
            assertTrue(person3.compareTo(person1) < 0);
        }
         */
}