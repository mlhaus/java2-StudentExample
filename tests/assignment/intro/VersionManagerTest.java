package assignment.intro;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class VersionManagerTest {
    @Test
    public void initializationTests() {
        try {
            assertEquals("0.0.1", new VersionManager().release());
            assertEquals("0.0.1", new VersionManager("").release());
            assertEquals("1.2.3", new VersionManager("1.2.3").release());
            assertEquals("1.2.3", new VersionManager("1.2.3.4").release());
            assertEquals("1.2.3", new VersionManager("1.2.3.d").release());
            assertEquals("1.0.0", new VersionManager("1").release());
            assertEquals("1.1.0", new VersionManager("1.1").release());
        } catch(Exception e) {
            fail();
        }
    }

    @Test
    public void majorReleasesTests() {
        try {
            assertEquals("1.0.0", new VersionManager().major().release());
            assertEquals("2.0.0", new VersionManager("1.2.3").major().release());
            assertEquals("3.0.0", new VersionManager("1.2.3").major().major().release());
        } catch(Exception e) {
            fail();
        }
    }

    @Test
    public void minorReleasesTests() {
        try {
            assertEquals("0.1.0", new VersionManager().minor().release());
            assertEquals("1.3.0", new VersionManager("1.2.3").minor().release());
            assertEquals("1.1.0", new VersionManager("1").minor().release());
            assertEquals("4.2.0", new VersionManager("4").minor().minor().release());
        } catch(Exception e) {
            fail();
        }
    }

    @Test
    public void patchReleasesTests() {
        try {
            assertEquals("0.0.2", new VersionManager().patch().release());
            assertEquals("1.2.4", new VersionManager("1.2.3").patch().release());
            assertEquals("4.0.2", new VersionManager("4").patch().patch().release());
        } catch(Exception e) {
            fail();
        }
    }

    @Test
    public void rollbacksTests() {
        try {
            assertEquals("0.0.1", new VersionManager().major().rollback().release());
            assertEquals("0.0.1", new VersionManager().minor().rollback().release());
            assertEquals("0.0.1", new VersionManager().patch().rollback().release());
            assertEquals("1.0.0", new VersionManager().major().patch().rollback().release());
            assertEquals("1.0.0", new VersionManager().major().patch().rollback().major().rollback().release());
        } catch(Exception e) {
            fail();
        }
    }

    @Test
    public void seperatedCallsTests() {
        try {
            VersionManager m = new VersionManager("1.2.3");
            m.major();
            m.minor();
            assertEquals("2.1.0", m.release());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void exceptionsTests() {
        try {
            VersionManager m = new VersionManager("a.b.c");
            fail("Expected an Exception to be thrown");
        } catch (Exception e) {
            assertEquals("Error occured while parsing version!", e.getMessage());
        }
        try {
            VersionManager m = new VersionManager().rollback();
            fail("Expected an Exception to be thrown");
        } catch (Exception e) {
            assertEquals("Cannot rollback!", e.getMessage());
        }
    }
}