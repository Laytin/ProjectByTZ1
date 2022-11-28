package eu.laytin.projectbytz1.controllers;

import eu.laytin.projectbytz1.dao.PersonDAO;
import eu.laytin.projectbytz1.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/person")
public class PersonController {
    private final PersonDAO personDAO;
    @Autowired
    public PersonController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }
    @GetMapping()
    public String index(Model model){
        model.addAttribute("persons",personDAO.showAll());
        return "person/index";
    }
    @GetMapping("/{id}")
    public String id(@PathVariable("id") int id, Model model){
        model.addAttribute("person",personDAO.showIndex(id));
        model.addAttribute("books",personDAO.getBooksByPersonId(id));
        return "person/id";
    }
}
