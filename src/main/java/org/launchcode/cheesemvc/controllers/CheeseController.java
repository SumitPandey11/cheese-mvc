package org.launchcode.cheesemvc.controllers;

import org.launchcode.cheesemvc.models.Cheese;
import org.launchcode.cheesemvc.models.CheeseData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("cheese")
public class CheeseController {

    @RequestMapping(value="")
    public String index(Model model){

        model.addAttribute("cheeses",CheeseData.getAll());
        model.addAttribute("title", "My Cheeses");

        return "cheese/index";
    }

    @RequestMapping(value="add" , method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model){
        model.addAttribute("title","Add Cheese");
        return "cheese/add";
    }

    @RequestMapping(value = "add",method = RequestMethod.POST)
    public String processAddCheeseForm(@ModelAttribute Cheese newCheese){

        CheeseData.add(newCheese);
        //redirect to /cheese
        return "redirect:";
    }

    @RequestMapping(value = "delete",method = RequestMethod.GET)
    public String processDeleteCheeseForm(Model model){

        model.addAttribute("cheeses",CheeseData.getAll());
        model.addAttribute("title", "Delete Cheeses");

        return "cheese/delete";

    }


    @RequestMapping(value = "delete",method = RequestMethod.POST)
    public String processDeleteCheeseForm(@RequestParam int[] cheeseIds){
        for( int cheeseId : cheeseIds){
            CheeseData.remove(cheeseId);
            }

        //redirect to /cheese
        return "redirect:";
    }

    @RequestMapping(value = "edit/{cheeseId}", method = RequestMethod.GET)
    public  String displayEntryForm(Model model, @PathVariable int cheeseId){
        model.addAttribute("cheese",CheeseData.getById(cheeseId));

        return "cheese/edit";
    }

    @RequestMapping(value = "edit/{cheeseId}", method = RequestMethod.POST)
    public String processEditForm(@RequestParam int cheeseId, String name, String description){
        Cheese editCheese = CheeseData.getById(cheeseId);
        editCheese.setName(name);
        editCheese.setDescription(description);

        return "redirect:/cheese";
    }
}

