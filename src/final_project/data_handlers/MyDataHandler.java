package final_project.data_handlers;

import final_project.data_access.MyDAO;
import final_project.MyException;
import java.util.ResourceBundle;
import java.util.Scanner;

public interface MyDataHandler {
    void handleTask(MyDAO data_source, Scanner in, ResourceBundle messages) throws MyException;

}
