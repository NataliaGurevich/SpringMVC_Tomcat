package ru.natalia.springmvc.dao;

import org.springframework.stereotype.Component;
import ru.natalia.springmvc.model.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {

    private static int PEOPLE_COUNT = 0;
    private List<Person> people;

    {
        people = new ArrayList<>();

        people.add(new Person(++PEOPLE_COUNT, "Tom", 24, "tom@mail.com"));
        people.add(new Person(++PEOPLE_COUNT, "Bob", 14, "bob@mail.com"));
        people.add(new Person(++PEOPLE_COUNT, "Mike", 36, "mike@mail.com"));
        people.add(new Person(++PEOPLE_COUNT, "Katy", 22, "kate@mail.com"));
    }

    public List<Person> index() {
        return people;
    }

    public Person show(int id) {
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public void save(Person person) {
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }

    public void update(int id, Person personToEdit) {
        Person personEditable = show(id);
        personEditable.setName(personToEdit.getName());
        personEditable.setAge(personToEdit.getAge());
        personEditable.setEmail(personToEdit.getEmail());
    }

    public void delete(int id) {
        people.removeIf(p -> p.getId() == id);
    }
}
