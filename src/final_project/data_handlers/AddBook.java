package final_project.data_handlers;

import final_project.MyException;
import final_project.UIUtility;
import final_project.UserInput;
import final_project.data_access.BookDAO_MYSQL;
import final_project.data_access.MyDAO;
import final_project.data_access.PersonDAO_MYSQL;
import java1refresher.Book;
import java1refresher.Person;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class AddBook {

    public void handleTask(MyDAO data_source, MyDAO persondata, Scanner in, ResourceBundle messages) throws MyException {
        Book book = new Book();

        while(true) {
            try {
                String userIn = UserInput.getString("Enter the title", in);
                book.setTitle(userIn);
                break;
            } catch(IllegalArgumentException e) {
                UIUtility.showErrorMessage(e.getMessage(), in, messages);
            }
        }

        if(data_source.getClass() != BookDAO_MYSQL.class) {
            while (true) {
                try {
                    String userIn = UserInput.getString("Enter the author first name", in);
                    String userIn2 = UserInput.getString("Enter the author last name", in);
                    Person author = new Person(userIn, userIn2);
                    book.setAuthor(author);
                    break;
                } catch (IllegalArgumentException e) {
                    UIUtility.showErrorMessage(e.getMessage(), in, messages);
                }
            }
        }
        else{
            List<Person> list = persondata.getAll();
            int author;
            if(list != null && list.size() > 0) {
                String[] menuOptions = new String[list.size()];
                for (int i = 0; i < menuOptions.length; i++) {
                    menuOptions[i] = list.get(i).getFirstName() + " " + list.get(i).getLastName();
                }
                while (true) {
                    String menuTitle = "Select a Person as the Author";
                    String prompt = "Your Choice";
                    int choice = UIUtility.showMenuOptions(menuTitle, prompt, menuOptions, in, messages);
                    if (choice <= 0 || choice > menuOptions.length + 1) {
                        UIUtility.pressEnterToContinue(in, messages);
                        continue;
                    }
                    if (choice == menuOptions.length + 1) {
                        author = 0;
                        book.setAuthor(author);
                        break;
                    }
                    UIUtility.showSectionTitle(" " + menuOptions[Integer.valueOf(choice) - 1]);
                    author = choice;
                    Person person = list.get(choice - 1);
                    book.setAuthor(person);
                    book.setAuthor(author);
                    break;
                }
            } else {
                System.out.println("There are no people available to set as the author.");
            }
        }

        while(true) {
            try {
                String[] inputs = {"yes","no"};
                String userIn = UserInput.validateString("Have you read this book? ", inputs, in, messages).toLowerCase();
                switch(userIn){
                    case "yes":
                        book.setRead(true);
                        break;
                    case "no":
                        book.setRead(false);
                        break;
                }
                book.setRead(false);
                break;
            } catch(IllegalArgumentException e) {
                UIUtility.showErrorMessage(e.getMessage(), in, messages);
            }
        }

        while(true) {
            try {
                int userIn = UserInput.getInt("Enter the number of pages", in, messages);
                book.setNumPages(userIn);
                break;
            } catch(IllegalArgumentException e) {
                UIUtility.showErrorMessage(e.getMessage(), in, messages);
            }
        }

        while(true){
            try{
                LocalDateTime userIn = UserInput.getDate("Enter the date published", in, messages);
                book.setDatePublished(userIn.toLocalDate());
                break;
            } catch(IllegalArgumentException e){
                UIUtility.showErrorMessage(e.getMessage(), in, messages);
            }
        }

        while(true){
            try{
                Double userIn = UserInput.getDouble("Enter the price", in, messages);
                book.setUnitPrice(userIn);
                break;
            } catch(IllegalArgumentException e){
                UIUtility.showErrorMessage(e.getMessage(), in, messages);
            }
        }

        data_source.add(book);
        System.out.println("\n" + "Book added");
    }
}
