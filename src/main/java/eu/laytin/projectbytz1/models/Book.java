package eu.laytin.projectbytz1.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Book {
    private int id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 60, message = "Name should be between 2 and 60 characters")
    private String name;

    @NotEmpty(message = "Author should not be empty.Format = A.A.Abiggneil")
    @Size(min = 2, max = 60, message = "Authorname should be between 2 and 60 characters")
    @Pattern(regexp = "[A-Z]\\.[A-Z]\\.[A-Z]\\w+")
    private String author;

    @Min(value =1000, message = "Age should be greater than 1000")
    @NotEmpty(message = "Year should not be empty")
    private int year;

    public Book(){

    }
    public Book(int id, String name, String author, int year) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
