package final_project.data_access;

import final_project.MyException;
import java1refresher.Book;
import java1refresher.Person;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BookDAO_MYSQL implements MyDAO<Book>{

    private List<Book> list;

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
                CallableStatement callableStatement = connection.prepareCall("{CALL sp_get_all_books()}");
                ResultSet resultSet = callableStatement.executeQuery();
                list = new ArrayList<Book>();
                while(resultSet.next()){
                    int id = resultSet.getInt("id");
                    String title = resultSet.getString("title");
                    int personId = resultSet.getInt("personId");

                    //get author
                    CallableStatement callableStatement1 = connection.prepareCall("{CALL sp_get_person_by_id(?)}");
                    callableStatement1.setInt(1, personId);
                    ResultSet resultSet1 = callableStatement1.executeQuery();
                    Person author = null;
                    if(resultSet1.next()){
                        String firstName = resultSet1.getString("firstName");
                        String lastName = resultSet1.getString("lastName");
                        int height = resultSet1.getInt("heightInInches");
                        double weight = resultSet1.getDouble("weightInPounds");
                        LocalDateTime dateOfBirth = resultSet1.getTimestamp("dateOfBirth").toLocalDateTime();
                        author = new Person(id, firstName, lastName, height, weight, dateOfBirth);
                    }
                    resultSet1.close();
                    callableStatement1.close();
                    //end of getting author

                    boolean read = resultSet.getBoolean("readBook");
                    int numPages = resultSet.getInt("numPages");
                    LocalDate datePublished = resultSet.getDate("datePublished").toLocalDate();
                    double price = resultSet.getDouble("price");
                    Book book = new Book(title, author, read, numPages, datePublished, price, id);
                    list.add(book);
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
    public void add(Book obj) throws MyException {
        if(list == null){
            readInData();
        }
        list.add(obj);
        try (Connection connection = getConnection()) {
            CallableStatement callableStatement = connection.prepareCall("{CALL sp_add_book(?,?,?,?,?,?)}");
            callableStatement.setString(1, obj.getTitle());
            callableStatement.setInt(2, obj.getAuthorID());
            callableStatement.setBoolean(3, obj.isRead());
            callableStatement.setInt(4, obj.getNumPages());
            callableStatement.setDate(5, Date.valueOf(obj.getDatePublished()));
            callableStatement.setDouble(6, obj.getUnitPrice());
            callableStatement.execute();
            callableStatement.close();
        } catch(SQLException e){
            throw new MyException(e);
        }
        readInData();
    }

    @Override
    public Book get(int id) throws MyException {
        return null;
    }

    @Override
    public List<Book> get(String str) throws MyException {
        return null;
    }

    @Override
    public List<Book> get(LocalDate date) throws MyException {
        return null;
    }

    @Override
    public List<Book> getAll() throws MyException {
        if(list == null){
            readInData();
        }
        return list;
    }

    @Override
    public void set(int id, Book obj) throws MyException {

    }

    @Override
    public boolean remove(Book obj) throws MyException {
        return false;
    }
}
