package java1refresher;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    private Book book;
    private Book book1;
    private Book book2;
    private Person person;
    private Person person1;
    private LocalDate today;
    private LocalDate tomorrow;
    private LocalDate yesterday;

    @BeforeEach
    void setUp() {
        today = LocalDate.now();
        tomorrow = LocalDate.now().plusDays(1);
        yesterday = LocalDate.now().minusDays(1);
        person = new Person("Nathan", "Zumsande");
        person1 = new Person("Micheal", "Zumsande");
        book = new Book();
        //book1 = new Book("Title", person, true, 10, today, 1.011);
        //book2 = new Book("Title", person1, false, 30, yesterday, 5.5456);
    }

    @Test
    void getTitle() {
        assertEquals(Book.DEFAULT_TITLE, book.getTitle());
    }

    @Test
    void getAuthor() {
        assertEquals(person, book1.getAuthor());
    }

    @Test
    void isRead() {
        assertEquals(true, book1.isRead());
        assertEquals(false, book2.isRead());
    }

    @Test
    void getNumPages() {
        assertEquals(1, book.getNumPages());
        assertEquals(10, book1.getNumPages());
    }

    @Test
    void getDatePublished() {
        assertEquals(today, book.getDatePublished());
    }

    @Test
    void getUnitPrice() {
        assertEquals(0.01, book.getUnitPrice());
    }

    @Test
    void setTitle() {
        //good
        book.setTitle("Java");
        assertEquals("Java", book.getTitle());

        //bad
        assertThrows(IllegalArgumentException.class, () -> book.setTitle(""));
    }

    @Test
    void setAuthor() {
        //good
        book.setAuthor(person);
        assertEquals(person, book1.getAuthor());

        //bad
        assertThrows(IllegalArgumentException.class, () -> book.setAuthor(null));
    }

    @Test
    void setRead() {
        book.setRead(false);
        assertEquals(false, book.isRead());
        book.setRead(true);
        assertEquals(true, book.isRead());
    }

    @Test
    void setNumPages() {
        //good
        book.setNumPages(1);
        assertEquals(1, book.getNumPages());
        book.setNumPages(100);
        assertEquals(100, book.getNumPages());

        //bad
        assertThrows(IllegalArgumentException.class, () -> book.setNumPages(0));
        assertThrows(IllegalArgumentException.class, () -> book.setNumPages(-1));
    }

    @Test
    void setDatePublished() {
        //good
        book.setDatePublished(today);
        assertEquals(today, book.getDatePublished());
        book.setDatePublished(yesterday);
        assertEquals(yesterday, book.getDatePublished());

        //bad
        assertThrows(IllegalArgumentException.class, () -> book.setDatePublished(tomorrow));
    }

    @Test
    void setUnitPrice() {
        //good
        book.setUnitPrice(1);
        assertEquals(1.00, book.getUnitPrice());
        book.setUnitPrice(5.546);
        assertEquals(5.55, book.getUnitPrice());
        book.setUnitPrice(3.333333333333);
        assertEquals(3.33, book.getUnitPrice());

        //bad
        assertThrows(IllegalArgumentException.class, () -> book.setUnitPrice(0));
        assertThrows(IllegalArgumentException.class, () -> book.setUnitPrice(-1.1));

    }

    @Test
    void testToString() {
        assertEquals(String.format("Book{title='%s', author='%s', read=%s, numPages=%s, datePublished=%s, unitPrice=%s}"
                , book.getTitle(), book.getAuthor(), book.isRead(), book.getNumPages(), book.getDatePublished()
                , book.getUnitPrice()), book.toString());
    }

    @Test
    void compareTo() {
        assertTrue(book.compareTo(book1) < 0);
        assertTrue(book.compareTo(book2) < 0);
        assertTrue(book1.compareTo(book) > 0);
        assertTrue(book1.compareTo(book2) > 0);
        assertTrue(book2.compareTo(book) > 0);
        assertTrue(book2.compareTo(book1) < 0);
    }
}