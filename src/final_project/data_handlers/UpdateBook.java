package final_project.data_handlers;

import final_project.MyException;
import final_project.UIUtility;
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

public class UpdateBook implements MyDataHandler{
    @Override
    public void handleTask(MyDAO data_source, Scanner in, ResourceBundle messages) throws MyException {
        List<Book> list = data_source.getAll();
        if(list != null && list.size() > 0) {
            String[] menuOptions = new String[list.size()];
            for (int i = 0; i < menuOptions.length; i++) {
                menuOptions[i] = list.get(i).getTitle();
            }
            while (true) {
                String menuTitle = "Select a Book to Update";
                String prompt = "Your Choice";
                int choice = UIUtility.showMenuOptions(menuTitle, prompt, menuOptions, in, messages);
                if (choice <= 0 || choice > menuOptions.length + 1) {
                    UIUtility.pressEnterToContinue(in, messages);
                    continue;
                }
                if (choice == menuOptions.length + 1)
                    break;
                UIUtility.showSectionTitle("Updating " + menuOptions[Integer.valueOf(choice) - 1]);
                Book book = list.get(choice - 1);
                updateBook(data_source, book, choice - 1, in, messages);
                break;
            }
        } else {
            System.out.println("There are no people available to update.");
        }
    }

    public void updateBook(MyDAO data_source, Book book, int index, Scanner in, ResourceBundle messages) throws MyException {
        String keep = " (Press enter to keep the current value)";
        System.out.println("Title: " + book.getTitle());
        while(true) {
            try {
                String userIn = UserInput.getString("New Title" + keep, in);
                if(userIn.equals("")){
                    break;
                }
                book.setTitle(userIn);
                break;
            } catch(IllegalArgumentException e) {
                UIUtility.showErrorMessage(e.getMessage(), in, messages);
            }
        }
        System.out.println("Author: " + book.getAuthor());
        while(true) {
            try {
                String userIn = UserInput.getString("New author first name" + keep, in);
                String userIn1 = UserInput.getString("New author last name" + keep, in);
                if(userIn.equals("")){
                    break;
                }
                book.setAuthor(new Person(userIn, userIn1));
                break;
            } catch(IllegalArgumentException e) {
                UIUtility.showErrorMessage(e.getMessage(), in, messages);
            }
        }
        System.out.println("Read: " + book.isRead());
        String[] values = {"true", "false"};
        while(true) {
            try {
                String userIn = UserInput.validateString("Have read" + keep, values, in, messages);
                if(userIn.equals("")){
                    break;
                }
                book.setRead(Boolean.parseBoolean(userIn));
                break;
            }
            catch (NumberFormatException e){
                UIUtility.showErrorMessage("Invalid Input", in, messages);
            }
            catch(IllegalArgumentException e) {
                UIUtility.showErrorMessage(e.getMessage(), in, messages);
            }
        }
        System.out.println("Number of Pages: " + book.getNumPages());
        while(true) {
            try {
                String userIn = UserInput.getString("New Number of Pages" + keep, in);
                if(userIn.equals("")){
                    break;
                }
                book.setNumPages(Integer.parseInt(userIn));
                break;
            }
            catch (NumberFormatException e){
                UIUtility.showErrorMessage("Invalid number", in, messages);
            }
            catch(IllegalArgumentException e) {
                UIUtility.showErrorMessage(e.getMessage(), in, messages);
            }
        }
        System.out.println("Date Published: " + book.getDatePublished());
        while(true){
            try{
                String userIn = UserInput.getString("New date published" + keep, in);
                if(userIn.equals("")){
                    break;
                }
                DateTimeFormatter formatterInput = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date = LocalDate.parse(userIn, formatterInput);
                book.setDatePublished(date);
                break;
            }
            catch(IllegalArgumentException e){
                UIUtility.showErrorMessage(e.getMessage(), in, messages);
            }
            catch(DateTimeParseException e){
                UIUtility.showErrorMessage("Invalid date", in, messages);
            }
        }
        System.out.println("Price: " + book.getUnitPrice());
        while(true) {
            try {
                String userIn = UserInput.getString("New price" + keep, in);
                if(userIn.equals("")){
                    break;
                }
                book.setUnitPrice(Double.parseDouble(userIn));
                break;
            }
            catch (NumberFormatException e){
                UIUtility.showErrorMessage("Invalid number", in, messages);
            }
            catch(IllegalArgumentException e) {
                UIUtility.showErrorMessage(e.getMessage(), in, messages);
            }
        }

        data_source.set(index, book);
        System.out.println("\nBook Updated.");
    }
}
