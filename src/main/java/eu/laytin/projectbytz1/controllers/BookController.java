package eu.laytin.projectbytz1.controllers;

import eu.laytin.projectbytz1.dao.BookDAO;
import eu.laytin.projectbytz1.dao.PersonDAO;
import eu.laytin.projectbytz1.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/book")
public class BookController {
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;
    @Autowired
    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }
    @GetMapping()
    public String index(Model model){
        model.addAttribute("books",bookDAO.showAll());
        return "book/index";
    }

    @GetMapping("/{id}")
    public String id(@PathVariable("id") int id, @ModelAttribute("person") Person person, Model model){
        model.addAttribute("book",bookDAO.showIndex(id));
        Optional<Person> owner;
        if((owner=bookDAO.getBookOwner(id)).isPresent()){
            model.addAttribute("owner", owner.get());
        }else{
            model.addAttribute("persons",personDAO.showAll());
        }
        return "book/id";
    }

    @PatchMapping("/{id}/resetOwner")
    public String resetOwner(@PathVariable("id") int id){
        bookDAO.resetOwner(id);
        return "redirect:/book/{id}";
    }

    @PatchMapping("/{id}/setOwner")
    public String setOwner(@PathVariable("id") int id, @ModelAttribute("person") Person person){
        bookDAO.setOwner(id,person);
        return "redirect:/book/{id}";
    }

//    @PostMapping()
//    public String createPost(@ModelAttribute("person") @Valid Person person,
//                             BindingResult bindingResult){
//        if(bindingResult.hasErrors()){
//            return "person/new";
//        }
//        personDAO.createPerson(person);
//        return "redirect:/person";
//    }
//    @GetMapping("/{id}/edit")
//    public String edit(@PathVariable("id") int id,Model model){
//        model.addAttribute("person", personDAO.showIndex(id));
//        return "person/edit";
//    }
//
//    @PatchMapping("/{id}")
//    public String update(@PathVariable("id") int id,@ModelAttribute("person") @Valid Person person,
//                         BindingResult br){
//        if(br.hasErrors()){
//            return "person/edit";
//        }
//        personDAO.updatePerson(id,person);
//        return "redirect:/person";
//    }
//
//    @DeleteMapping("/{id}")
//    public String delete(@PathVariable("id") int id){
//        personDAO.deletePerson(id);
//        return "redirect:/person";
//    }
}
