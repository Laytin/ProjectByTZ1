package eu.laytin.projectbytz1.dao;

import eu.laytin.projectbytz1.models.Book;
import eu.laytin.projectbytz1.models.Person;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {
    private final JdbcTemplate jbdc;

    public BookDAO(JdbcTemplate jbdc) {
        this.jbdc = jbdc;
    }

    //Показать всех
    public List<Book> showAll(){
        return jbdc.query("SELECT * FROM Book", new BeanPropertyRowMapper<Book>(Book.class));
    }

    //Показать с id
    public Book showIndex(int book_id){
        return jbdc.query("SELECT * FROM Book WHERE id=?", new Object[]{book_id},
                new BeanPropertyRowMapper<Book>(Book.class)).stream().findAny().orElse(null);
    }

    //Создать нового
    public void createBook(Book book){
        jbdc.update("INSERT INTO Book(name,author,year) VALUES(?,?,?)", book.getName(),book.getAuthor(), book.getYear());
    }

    //Обновить существующего (Попробовать убрать из аргументов id и юзать person.getID())
    public void updateBook(int id,Book book){
        jbdc.update("UPDATE Book SET name=?,author=?,year=? WHERE id=?",book.getName(),book.getAuthor(),book.getYear(),id);
    }
    //Удалить с id
    public void deleteBook(int id){
        jbdc.update("DELETE From Book WHERE id=?",id);
    }

    public void resetOwner(int bookId){
        jbdc.update("UPDATE Book SET person_id=null WHERE id=?",bookId);
    }
    public void setOwner(int id, Person person){
        jbdc.update("UPDATE Book SET person_id=? WHERE id=?",person.getId(),id);
    }
    public Optional<Person> getBookOwner(int id){
        return jbdc.query("SELECT Person.* FROM Book JOIN person on person.id = book.person_id WHERE book.id=?",
                new Object[]{id},new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }
}
