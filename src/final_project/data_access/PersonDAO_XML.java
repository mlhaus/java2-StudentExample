package final_project.data_access;

import final_project.MyException;
import java1refresher.Person;

import java.time.LocalDate;
import java.util.List;

public class PersonDAO_XML implements MyDAO<Person>{
    private static final String FILE_NAME = "person.xml";
    private List<Person> list;

    @Override
    public void readInData() throws MyException {

    }

    @Override
    public void verifyData() throws MyException {

    }

    @Override
    public void add(Person obj) throws MyException {

    }

    @Override
    public Person get(int id) throws MyException {
        return null;
    }

    @Override
    public List<Person> get(String str) throws MyException {
        return null;
    }

    @Override
    public List<Person> get(LocalDate date) throws MyException {
        return null;
    }

    @Override
    public List<Person> getAll() throws MyException {
        return null;
    }

    @Override
    public void set(int id, Person obj) throws MyException {

    }

    @Override
    public boolean remove(Person obj) throws MyException {
        return false;
    }
}
