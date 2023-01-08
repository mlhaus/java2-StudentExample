package final_project.data_handlers;

import final_project.data_access.MyDAO;
import final_project.MyException;
import final_project.UIUtility;
import final_project.UserInput;
import java1refresher.Person;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.Scanner;

public class AddPerson implements MyDataHandler {
    @Override
    public void handleTask(MyDAO data_source, Scanner in, ResourceBundle messages) throws MyException {
        Person person = new Person();

        while(true) {
            try {
                String userIn = UserInput.getString(messages.getString("enter-first-name"), in);
                person.setFirstName(userIn);
                break;
            } catch(IllegalArgumentException e) {
                UIUtility.showErrorMessage(e.getMessage(), in, messages);
            }
        }

        while(true) {
            try {
                String userIn = UserInput.getString(messages.getString("enter-last-name"), in);
                person.setLastName(userIn);
                break;
            } catch(IllegalArgumentException e) {
                UIUtility.showErrorMessage(e.getMessage(), in, messages);
            }
        }

        while(true) {
            try {
                int userIn = UserInput.getInt(messages.getString("enter-height"), in, messages);
                person.setHeightInInches(userIn);
                break;
            } catch(IllegalArgumentException e) {
                UIUtility.showErrorMessage(e.getMessage(), in, messages);
            }
        }

        while(true) {
            try {
                double userIn = UserInput.getDouble(messages.getString("enter-weight"), in, messages);
                person.setWeightInPounds(userIn);
                break;
            } catch(IllegalArgumentException e) {
                UIUtility.showErrorMessage(e.getMessage(), in, messages);
            }
        }

        while(true){
            try{
                LocalDateTime userIn = UserInput.getDate(messages.getString("enter-date-of-birth"), in, messages);
                person.setDateOfBirth(userIn);
                break;
            } catch(IllegalArgumentException e){
                UIUtility.showErrorMessage(e.getMessage(), in, messages);
            }
        }

        data_source.add(person);
        System.out.println("\n" + messages.getString("person-added"));
    }
}
