package eu.laytin.projectbytz1.dao;

import eu.laytin.projectbytz1.models.Book;
import eu.laytin.projectbytz1.models.Person;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
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
        return jbdc.query("SELECT * FROM Book WHERE book_id=?", new Object[book_id],
                new BeanPropertyRowMapper<Book>(Book.class)).stream().findAny().orElse(null);
    }

    //Создать нового
    public void createBook(Book book){
        jbdc.update("INSERT INTO Book(book_name,book_author,book_year) VALUES(?,?,?)", book.getName(),book.getAuthor(), book.getYear());
    }

    //Обновить существующего (Попробовать убрать из аргументов id и юзать person.getID())
    public void updatePerson(int id,Book book){
        jbdc.update("UPDATE Book SET name=?,author=?,year=? WHERE book_id=?",book.getName(),book.getAuthor(),book.getYear(),id);
    }
    //Удалить с id
    public void deletePerson(int id){
        jbdc.update("DELETE From Book WHERE book_id=?",id);
    }
}
