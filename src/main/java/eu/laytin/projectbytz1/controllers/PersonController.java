package eu.laytin.projectbytz1.controllers;

import eu.laytin.projectbytz1.dao.PersonDAO;
import eu.laytin.projectbytz1.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @GetMapping("/new")
    public String createGet(@ModelAttribute("person") Person person){
        return "person/new";
    }

    @PostMapping()
    public String createPost(@ModelAttribute("person") @Valid Person person,
                             BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "person/new";
        }
        personDAO.createPerson(person);
        return "redirect:/person";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        personDAO.deletePerson(id);
        return "redirect:/person";
    }
}
