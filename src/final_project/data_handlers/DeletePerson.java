package final_project.data_handlers;

import final_project.MyException;
import final_project.UIUtility;
import final_project.data_access.MyDAO;
import java1refresher.Person;

import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class DeletePerson implements MyDataHandler{
    @Override
    public void handleTask(MyDAO data_source, Scanner in, ResourceBundle messages) throws MyException {
        List<Person> list = data_source.getAll();
        if(list != null && list.size() > 0){
            String[] menuOptions = new String[list.size()];
            for(int i = 0; i < menuOptions.length; i++){
                menuOptions[i] = list.get(i).getFirstName() + " " + list.get(i).getLastName();
            }
            while (true) {
                String menuTitle = "Select a Person to Delete";
                String prompt = "Your Choice";
                int choice = UIUtility.showMenuOptions(menuTitle, prompt, menuOptions, in, messages);
                if (choice <= 0 || choice > menuOptions.length + 1) {
                    UIUtility.pressEnterToContinue(in, messages);
                    continue;
                }
                if (choice == menuOptions.length + 1)
                    break;
                Person person = list.get(choice - 1);
                data_source.remove(person);
                break;
            }
        }
        else {
            System.out.println("There are no people available to delete.");
        }
    }
}
