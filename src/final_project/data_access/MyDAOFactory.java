package final_project.data_access;

import final_project.data_access.MyDAO;
import java1refresher.Book;
import java1refresher.Person;

import java.util.ArrayList;
import java.util.List;

public class MyDAOFactory {

    public static List<MyDAO> getMyDAO(String dao_source){
        List<MyDAO> daoList = new ArrayList<MyDAO>();
        MyDAO<Person> daoPerson;
        MyDAO<Book> daoBook;
        switch(dao_source.toUpperCase()){
            case "CSV":
                daoPerson = new PersonDAO_CSV();
                daoBook = new BookDAO_CSV();
                daoList.add(daoPerson);
                daoList.add(daoBook);
                break;
            case "XML":

                break;
            case "MYSQL":
                daoPerson = new PersonDAO_MYSQL();
                daoBook = new BookDAO_MYSQL();
                daoList.add(daoPerson);
                daoList.add(daoBook);
                break;
            default:
        }
        return daoList;
    }
}
