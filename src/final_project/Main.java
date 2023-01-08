package final_project;

import final_project.data_access.MyDAO;
import final_project.data_access.MyDAOFactory;
import final_project.data_handlers.*;
import java1refresher.Book;
import java1refresher.Person;

import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws MyException {
        String data_source = "mysql";
        List<MyDAO> daoList = MyDAOFactory.getMyDAO(data_source);
        MyDAO<Person> personDAO = daoList.get(0);
        MyDAO<Book> bookDAO = daoList.get(1);
        if(personDAO == null){
            System.out.println("Person data object not found");
            return;
        }
        try {
            personDAO.readInData();
        }
        catch(MyException e){
            System.out.println(e.getMessage());
            return;
        }
        Language language = new Language();
        ResourceBundle messages;
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        while (true) {
            messages = language.getMessages();
            String menuTitle = messages.getString("main-menu");
            String prompt = messages.getString("prompt");
            String[] menuOptions = {
                    messages.getString("add-person"),
                    messages.getString("get-person"),
                    messages.getString("update-person"),
                    messages.getString("delete-person"),
                    "Add Book",
                    "Get Book",
                    "Update Book",
                    "Delete Book",
                    messages.getString("change-language")
            };
            choice = UIUtility.showMenuOptions(menuTitle, prompt, menuOptions, scanner, messages);
            if (choice <= 0 || choice > menuOptions.length + 1) {
                UIUtility.pressEnterToContinue(scanner, messages);
                continue;
            }
            if (choice == menuOptions.length + 1) {
                break;
            }
            UIUtility.showSectionTitle(menuOptions[Integer.valueOf(choice) - 1]);
            try {
                switch (choice) {
                    case 1:
                        new AddPerson().handleTask(personDAO, scanner, messages);
                        break;
                    case 2:
                        new GetPerson().handleTask(personDAO, scanner, messages);
                        break;
                    case 3:
                        new UpdatePerson().handleTask(personDAO,scanner,messages);
                        break;
                    case 4:
                        new DeletePerson().handleTask(personDAO, scanner, messages);
                        break;
                    case 5:
                        new AddBook().handleTask(bookDAO, personDAO, scanner, messages);
                        break;
                    case 6:
                        new ReadBook().handleTask(bookDAO,scanner,messages);
                        break;
                    case 7:
                        new UpdateBook().handleTask(bookDAO,scanner,messages);
                        break;
                    case 8:
                        new DeleteBook().handleTask(bookDAO,scanner,messages);
                        break;
                    case 9:
                        language.setMessages(scanner);
                        messages = language.getMessages();
                        break;
                }
            }
            catch(MyException e){
                UIUtility.showErrorMessage(e.getMessage(), scanner, messages);
            }
            UIUtility.pressEnterToContinue(scanner, messages);
        }
        System.out.println("\n"+ messages.getString("end") + "\n");
        scanner.close();
    }
}
