package final_project.data_handlers;

import final_project.MyException;
import final_project.UIUtility;
import final_project.UserInput;
import final_project.data_access.MyDAO;
import java1refresher.Person;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class UpdatePerson implements MyDataHandler{
    @Override
    public void handleTask(MyDAO data_source, Scanner in, ResourceBundle messages) throws MyException {
        List<Person> list = data_source.getAll();
        if(list != null && list.size() > 0) {
            String[] menuOptions = new String[list.size()];
            for (int i = 0; i < menuOptions.length; i++) {
                menuOptions[i] = list.get(i).getFirstName() + " " + list.get(i).getLastName();
            }
            while (true) {
                String menuTitle = "Select a Person to Update";
                String prompt = "Your Choice";
                int choice = UIUtility.showMenuOptions(menuTitle, prompt, menuOptions, in, messages);
                if (choice <= 0 || choice > menuOptions.length + 1) {
                    UIUtility.pressEnterToContinue(in, messages);
                    continue;
                }
                if (choice == menuOptions.length + 1)
                    break;
                UIUtility.showSectionTitle("Updating " + menuOptions[Integer.valueOf(choice) - 1]);
                Person person = list.get(choice - 1);
                updatePerson(data_source, person, choice - 1, in, messages);
                break;
            }
        } else {
            System.out.println("There are no people available to update.");
        }
    }

    public void updatePerson(MyDAO data_source, Person person, int index, Scanner in, ResourceBundle messages) throws MyException{
        String keep = " (Press enter to keep the current value)";
        System.out.println("First name: " + person.getFirstName());
        while(true) {
            try {
                String userIn = UserInput.getString("New first name" + keep, in);
                if(userIn.equals("")){
                    break;
                }
                person.setFirstName(userIn);
                break;
            } catch(IllegalArgumentException e) {
                UIUtility.showErrorMessage(e.getMessage(), in, messages);
            }
        }
        System.out.println("Last name: " + person.getLastName());
        while(true) {
            try {
                String userIn = UserInput.getString("New last name" + keep, in);
                if(userIn.equals("")){
                    break;
                }
                person.setLastName(userIn);
                break;
            } catch(IllegalArgumentException e) {
                UIUtility.showErrorMessage(e.getMessage(), in, messages);
            }
        }
        System.out.println("Height: " + person.getHeightInInches());
        while(true) {
            try {
                String userIn = UserInput.getString("New height" + keep, in);
                if(userIn.equals("")){
                    break;
                }
                person.setHeightInInches(Integer.parseInt(userIn));
                break;
            }
            catch (NumberFormatException e){
                UIUtility.showErrorMessage("Invalid integer", in, messages);
            }
            catch(IllegalArgumentException e) {
                UIUtility.showErrorMessage(e.getMessage(), in, messages);
            }
        }
        System.out.println("Weight: " + person.getWeightInPounds());
        while(true) {
            try {
                String userIn = UserInput.getString("New weight" + keep, in);
                if(userIn.equals("")){
                    break;
                }
                person.setWeightInPounds(Double.parseDouble(userIn));
                break;
            }
            catch (NumberFormatException e){
                UIUtility.showErrorMessage("Invalid number", in, messages);
            }
            catch(IllegalArgumentException e) {
                UIUtility.showErrorMessage(e.getMessage(), in, messages);
            }
        }
        System.out.println("Date of birth: " + person.getDateOfBirth().toLocalDate());
        while(true){
            try{
                String userIn = UserInput.getString("New date of birth" + keep, in);
                if(userIn.equals("")){
                    break;
                }
                DateTimeFormatter formatterInput = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date = LocalDate.parse(userIn, formatterInput);
                person.setDateOfBirth(date.atStartOfDay());
                break;
            }
            catch(IllegalArgumentException e){
                UIUtility.showErrorMessage(e.getMessage(), in, messages);
            }
            catch(DateTimeParseException e){
                UIUtility.showErrorMessage("Invalid date", in, messages);
            }
        }

        data_source.set(index, person);
        System.out.println("\nPerson Updated.");
    }
}
