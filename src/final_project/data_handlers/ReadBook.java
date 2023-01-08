package final_project.data_handlers;

import final_project.MyException;
import final_project.UserInput;
import final_project.data_access.MyDAO;
import java1refresher.Book;
import java1refresher.Person;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ReadBook implements MyDataHandler{
    @Override
    public void handleTask(MyDAO data_source, Scanner in, ResourceBundle messages) throws MyException {
        String userIn = UserInput.getString("Search for a book by their id, author first name or last name, or date it was published (YYYY-MM-DD)", in);
        try{
            int id = Integer.parseInt(userIn);
            Book book = (Book)data_source.get(id);
            if(book == null) {
                System.out.println("No book found with id '" + id + "'.");
            } else {
                System.out.println("Retrieved:");
                System.out.println(book);
            }
        } catch(NumberFormatException e1){
            //search by date
            try{
                DateTimeFormatter formatterInput = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate datePublished = LocalDate.parse(userIn, formatterInput);
                List<Book> list = (List<Book>)data_source.get(datePublished);
                if(list.size() == 0) {
                    System.out.println("No person found with birth date '" + datePublished + "'.");
                } else {
                    System.out.println("\nRetrieved:");
                    for(Book book: list) {
                        System.out.println(book);
                    }
                }
            } catch(DateTimeParseException e2){
                //search by name
                List<Book> list = (List<Book>)data_source.get(userIn);
                if(list.size() == 0) {
                    System.out.println("No person found with first or last name containing '" + userIn +"'.");
                } else {
                    System.out.println("Retrieved:");
                    for(Book book: list) {
                        System.out.println(book);
                    }
                }
            }
        }
    }
}
