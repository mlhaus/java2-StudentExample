package final_project.data_access;

import final_project.MyException;
import java1refresher.Person;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
public class PersonDAO_MYSQL implements MyDAO<Person>{
    private List<Person> list;

    private Connection getConnection() throws SQLException {
        String driver = "mysql";
        String host = "localhost";
        String port = "3307";
        String db_name = "java2";
        String user = "root";
        String password = "password";

        String connectionString = "jdbc:" + driver + "://" + host + ":" + port + "/" + db_name;
        return DriverManager.getConnection(connectionString, user, password);
    }

    @Override
    public void readInData() throws MyException {
        try (Connection connection = getConnection()) {
            if (connection.isValid(2)) {
                //Statement statement = connection.createStatement();
                //ResultSet resultSet = statement.executeQuery("SELECT * FROM person;");
                CallableStatement callableStatement = connection.prepareCall("{CALL sp_get_all_people()}");
                ResultSet resultSet = callableStatement.executeQuery();
                list = new ArrayList<Person>();
                while(resultSet.next()){
                    int id = resultSet.getInt("id");
                    String firstName = resultSet.getString("firstName");
                    String lastName = resultSet.getString("lastName");
                    int height = resultSet.getInt("heightInInches");
                    double weight = resultSet.getDouble("weightInPounds");
                    LocalDateTime dateOfBirth = resultSet.getTimestamp("dateOfBirth").toLocalDateTime();
                    Person person = new Person(id, firstName, lastName, height, weight, dateOfBirth);
                    list.add(person);
                }
                resultSet.close();
                callableStatement.close();
                //statement.close();
            }
        }
        catch(Exception e) {
            System.out.println("Exception message: " + e.getMessage());
            if (e instanceof SQLException) {
                SQLException sqlException = (SQLException)e;
                System.out.println("Error Code: " + sqlException.getErrorCode());
                System.out.println("SQL State: " + sqlException.getSQLState());
            }
        }
    }

    @Override
    public void verifyData() throws MyException {

    }

    @Override
    public void add(Person obj) throws MyException {
        if(list == null){
            readInData();
        }
        list.add(obj);
        try (Connection connection = getConnection()) {
            CallableStatement callableStatement = connection.prepareCall("{CALL sp_add_person(?,?,?,?,?)}");
            callableStatement.setString(1, obj.getFirstName());
            callableStatement.setString(2, obj.getLastName());
            callableStatement.setInt(3, obj.getHeightInInches());
            callableStatement.setDouble(4, obj.getWeightInPounds());
            callableStatement.setTimestamp(5, Timestamp.valueOf(obj.getDateOfBirth()));
            callableStatement.execute();
        } catch(SQLException e){
            throw new MyException(e);
        }
    }

    @Override
    public Person get(int id) throws MyException {
        Person person = null;
        try{
            Connection conn = getConnection();
            CallableStatement callableStatement = conn.prepareCall("{CALL sp_get_person_by_id(?)}");
            callableStatement.setInt(1, id);

            ResultSet resultSet = callableStatement.executeQuery();
            if(resultSet.next()){
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                int height = resultSet.getInt("heightInInches");
                double weight = resultSet.getDouble("weightInPounds");
                LocalDateTime dateOfBirth = resultSet.getTimestamp("dateOfBirth").toLocalDateTime();
                person = new Person(id, firstName, lastName, height, weight, dateOfBirth);
            }
            callableStatement.close();
            conn.close();

        } catch(SQLException e){
            throw new MyException(e);
        }
        return person;
    }

    @Override
    public List<Person> get(String str) throws MyException {
        List<Person> list = new ArrayList<>();
        try{
            Connection conn = getConnection();
            CallableStatement callableStatement = conn.prepareCall("{CALL sp_get_person_by_name(?)}");
            callableStatement.setString(1, str);

            ResultSet resultSet = callableStatement.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                int height = resultSet.getInt("heightInInches");
                double weight = resultSet.getDouble("weightInPounds");
                LocalDateTime dateOfBirth = resultSet.getTimestamp("dateOfBirth").toLocalDateTime();
                Person person = new Person(id, firstName, lastName, height, weight, dateOfBirth);
                list.add(person);
            }
            callableStatement.close();
            conn.close();

        } catch(SQLException e){
            throw new MyException(e);
        }
        return list;
    }

    @Override
    public List<Person> get(LocalDate date) throws MyException {
        List<Person> list = new ArrayList<>();
        try{
            Connection conn = getConnection();
            CallableStatement callableStatement = conn.prepareCall("{CALL sp_get_person_by_date(?)}");
            callableStatement.setTimestamp(1, Timestamp.valueOf(date.atStartOfDay()));

            ResultSet resultSet = callableStatement.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                int height = resultSet.getInt("heightInInches");
                double weight = resultSet.getDouble("weightInPounds");
                LocalDateTime dateOfBirth = resultSet.getTimestamp("dateOfBirth").toLocalDateTime();
                Person person = new Person(id, firstName, lastName, height, weight, dateOfBirth);
                list.add(person);
            }
            callableStatement.close();
            conn.close();

        } catch(SQLException e){
            throw new MyException(e);
        }
        return list;
    }

    @Override
    public List<Person> getAll() throws MyException {
        if(list == null){
            readInData();
        }
        return list;
    }

    @Override
    public void set(int id, Person obj) throws MyException {
        try{
            Connection conn = getConnection();
            CallableStatement callableStatement = conn.prepareCall("{CALL sp_update_person(?,?,?,?,?,?)}");
            callableStatement.setInt(1, obj.getId());
            callableStatement.setString(2, obj.getFirstName());
            callableStatement.setString(3, obj.getLastName());
            callableStatement.setInt(4, obj.getHeightInInches());
            callableStatement.setDouble(5, obj.getWeightInPounds());
            callableStatement.setTimestamp(6, Timestamp.valueOf(obj.getDateOfBirth()));

            callableStatement.execute();
            callableStatement.close();
            conn.close();

        } catch(SQLException e){
            throw new MyException(e);
        }
        // Update the person list
        readInData();
    }

    @Override
    public boolean remove(Person obj) throws MyException {
        boolean removed = false;
        try{
            Connection conn = getConnection();
            CallableStatement callableStatement = conn.prepareCall("{CALL sp_delete_person(?)}");
            callableStatement.setInt(1, obj.getId());
            callableStatement.execute();
            callableStatement.close();
            conn.close();
            // Update the person list
            readInData();
            removed = true;
        } catch(SQLException e){
            throw new MyException(e);
        }
        return removed;
    }
}
