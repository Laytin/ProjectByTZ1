package eu.laytin.projectbytz1.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class Person {
    private int id;

    @NotEmpty(message = "Name should not be empty")
    @Pattern(regexp = "\"[A-Z]\\w+, [A-Z]\\w+,[A-Z]\\w+")
    private String name;

    @Min(value = 1900, message = "Age should be greater than 1900")
    //@NotEmpty(message = "Name should not be empty")
    private int year;


    public Person() {
    }

    public Person(String name, int year, int id) {
        this.id = id;
        this.name = name;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
