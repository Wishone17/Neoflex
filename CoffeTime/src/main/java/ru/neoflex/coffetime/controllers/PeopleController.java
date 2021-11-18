package ru.neoflex.coffetime.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.neoflex.coffetime.DAO.PersonDAO;
import ru.neoflex.coffetime.models.Person;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    //Получаем всех
    @GetMapping()
    public String getPersonList(Model model) {
        model.addAttribute("people", personDAO.getPeople());
        return "persons-list";
    }

   //Получаем форму для заполнения для добавления
   @GetMapping("/add")
   public String addPerson(@ModelAttribute("person") Person person){
       return "add";
   }

   @PostMapping()
   public String createPerson (@ModelAttribute("person") @Valid Person person, BindingResult bindingResult){
       if(bindingResult.hasErrors())
           return "add";
       personDAO.save(person);
       return "redirect:/people";
   }

   @GetMapping("/edit/{id}")
   public String edit (Model model, @PathVariable("id") int id){
       model.addAttribute("person", personDAO.showPerson(id));
       return "edit";
   }

   @PostMapping("/{id}")
   public String update(@ModelAttribute("person") @Valid Person person,BindingResult bindingResult, @PathVariable("id") int id){
       if(bindingResult.hasErrors())
           return "edit";
       personDAO.update(id, person);
       return "redirect:/people";
   }

   //Удаляем человека
   @GetMapping("/delete/{id}")
   public String deletePerson(@PathVariable("id") int id){
       personDAO.delete(id);
       return "redirect:/people";
   }

}
