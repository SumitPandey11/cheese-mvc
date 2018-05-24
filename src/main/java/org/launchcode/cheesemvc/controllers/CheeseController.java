package org.launchcode.cheesemvc.controllers;

import org.launchcode.cheesemvc.models.Cheese;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;

@Controller
@RequestMapping("cheese")
public class CheeseController {

    static ArrayList<Cheese> cheeses = new ArrayList<>();


    @RequestMapping(value="")
    public String index(Model model){

        model.addAttribute("cheeses",cheeses);
        model.addAttribute("title", "My Cheeses");

        return "cheese/index";
    }

    @RequestMapping(value="add" , method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model){
        model.addAttribute("title","Add Cheese");
        return "cheese/add";
    }

    @RequestMapping(value = "add",method = RequestMethod.POST)
    public String processAddCheeseForm(@RequestParam String cheeseName, @RequestParam String cheeseDescription){

        Cheese cheese = new Cheese(cheeseName,cheeseDescription);
        cheeses.add(cheese);
        //redirect to /cheese
        return "redirect:";
    }

    @RequestMapping(value = "delete",method = RequestMethod.GET)
    public String processDeleteCheeseForm(Model model){

        model.addAttribute("cheeses",cheeses);
        model.addAttribute("title", "Delete Cheeses");

        return "cheese/delete";

    }


    @RequestMapping(value = "delete",method = RequestMethod.POST)
    public String processDeleteCheeseForm(@RequestParam ArrayList<String> cheese){
        for( String cheeseName : cheese){
            for(Cheese c : cheeses) {

                if(c.getName().equals(cheeseName)) {
                    cheeses.remove(c);
                    break;
                }
            }
        }
        //redirect to /cheese
        return "redirect:";
    }
}

