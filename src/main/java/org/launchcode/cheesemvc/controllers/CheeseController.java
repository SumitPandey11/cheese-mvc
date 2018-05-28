package org.launchcode.cheesemvc.controllers;

import org.launchcode.cheesemvc.models.Cheese;
import org.launchcode.cheesemvc.models.CheeseType;
import org.launchcode.cheesemvc.models.data.CheeseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("cheese")
public class CheeseController {

    @Autowired
    private CheeseDao cheeseDao;

    @RequestMapping(value="")
    public String index(Model model){

        model.addAttribute("cheeses",cheeseDao.findAll());
        model.addAttribute("title", "My Cheeses");

        return "cheese/index";
    }

    @RequestMapping(value="add" , method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model){
        model.addAttribute("title","Add Cheese");
        model.addAttribute("cheeseTypes",CheeseType.values());
        model.addAttribute(new Cheese());
        return "cheese/add";
    }

    @RequestMapping(value = "add",method = RequestMethod.POST)
    public String processAddCheeseForm(@ModelAttribute @Valid  Cheese newCheese, Errors error, Model model){

        if(error.hasErrors()){
            model.addAttribute("title","Add Cheese");
            model.addAttribute("cheeseTypes",CheeseType.values());

            return "cheese/add";
        }
        cheeseDao.save(newCheese);
        //redirect to /cheese
        return "redirect:";
    }

    @RequestMapping(value = "delete",method = RequestMethod.GET)
    public String processDeleteCheeseForm(Model model){

        model.addAttribute("cheeses",cheeseDao.findAll());
        model.addAttribute("title", "Delete Cheeses");

        return "cheese/delete";

    }


    @RequestMapping(value = "delete",method = RequestMethod.POST)
    public String processDeleteCheeseForm(@RequestParam int[] cheeseIds){
        for( int cheeseId : cheeseIds){
            cheeseDao.deleteById(cheeseId);
            }

        //redirect to /cheese
        return "redirect:";
    }

    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public  String displayEntryForm(Model model, @PathVariable int id){

        Optional<Cheese> editCheese = cheeseDao.findById(id);
        Cheese cheese = null;
        //editCheese = newCheese;
        if(editCheese.isPresent()){
            cheese = editCheese.get();
        }

        model.addAttribute("cheese",cheese);
        model.addAttribute("cheeseTypes",CheeseType.values());

        return "cheese/edit";
    }

    @RequestMapping(value = "edit/{id}", method = RequestMethod.POST )
    public String processEditForm(@RequestParam int id, String name, String description, @ModelAttribute @Valid  Cheese newCheese, Errors error, Model model){
        if(error.hasErrors()){
            model.addAttribute("title","Edit Cheese");
            model.addAttribute("cheeseTypes",CheeseType.values());

            return "/cheese/edit";
        }


        Optional<Cheese> editCheese = cheeseDao.findById(id);
        //editCheese = newCheese;
        if(editCheese.isPresent()){
            Cheese cheese = editCheese.get();
            cheese.setName(newCheese.getName());
            cheese.setDescription(newCheese.getDescription());
            cheese.setType(newCheese.getType());
            cheese.setRating(newCheese.getRating());

            cheeseDao.save(cheese);
        }

        return "redirect:/cheese";
    }
}

