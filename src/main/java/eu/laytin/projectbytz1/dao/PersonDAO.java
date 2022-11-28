package eu.laytin.projectbytz1.dao;

import eu.laytin.projectbytz1.models.Book;
import eu.laytin.projectbytz1.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {
    private final JdbcTemplate jbdc;

    @Autowired
    public PersonDAO(JdbcTemplate jbdc) {
        this.jbdc = jbdc;
    }

    //Показать всех
    public List<Person> showAll(){
        return jbdc.query("SELECT * FROM person", new BeanPropertyRowMapper<>(Person.class));
    }

    //Показать с id
    public Person showIndex(int person_id){
        return jbdc.query("SELECT * FROM Person WHERE id=?", new Object[]{person_id},
                new BeanPropertyRowMapper<Person>(Person.class)).stream().findAny().orElse(null);

    }

    //Создать нового
    public void createPerson(Person person){
        jbdc.update("INSERT INTO Person(name,year) VALUES(?,?)", person.getName(), person.getYear());
    }

    //Обновить существующего (Попробовать убрать из аргументов id и юзать person.getID())
    public void updatePerson(int id,Person person){
        jbdc.update("UPDATE Person SET name=?,year=? WHERE id=?",person.getName(),person.getYear(),id);
    }
    //Удалить с id
    public void deletePerson(int id){
        jbdc.update("DELETE From Person WHERE id=?",id);
    }

    public List<Book> getBooksByPersonId(int id) {
        return jbdc.query("SELECT * FROM Book WHERE person_id = ?", new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class));
    }
}
