package ru.natalia.springmvc.dao;

import org.springframework.stereotype.Component;
import ru.natalia.springmvc.model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {

    private static int PEOPLE_COUNT = 0;

    private static String URL = "jdbc:postgresql://localhost:5432/people_mvc";
    private static String USERNAME = "natalia";
    private static String PASSWORD = "test123";

    private static Connection connection;

    {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Person> index() throws SQLException {
        List<Person> people = new ArrayList<>();

        Statement statement = connection.createStatement();
        String SQL = "SELECT * FROM person";
        ResultSet resultSet = statement.executeQuery(SQL);

        while (resultSet.next()){
            Person person = new Person();

            person.setId(resultSet.getInt("id"));
            person.setName(resultSet.getString("name"));
            person.setAge(resultSet.getInt("age"));
            person.setName(resultSet.getString("email"));

            people.add(person);
        }

        return people;
    }

    public Person show(int id) {
//        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
        return null;
    }

    public void save(Person person) throws SQLException {
//        person.setId(++PEOPLE_COUNT);
//        people.add(person);

        Statement statement = connection.createStatement();
        String SQL = "INSERT INTO person(name, age, email) VALUES('" + person.getName() + "', " + person.getAge()
                        + ", '" + person.getEmail() + "')";
        statement.executeUpdate(SQL);
    }

    public void update(int id, Person personToEdit) {
//        Person personEditable = show(id);
//        personEditable.setName(personToEdit.getName());
//        personEditable.setAge(personToEdit.getAge());
//        personEditable.setEmail(personToEdit.getEmail());
    }

    public void delete(int id) {
//        people.removeIf(p -> p.getId() == id);
    }
}
