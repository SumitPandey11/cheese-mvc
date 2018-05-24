package org.launchcode.cheesemvc.controllers;

import org.launchcode.cheesemvc.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("user")
public class UserController {

    @RequestMapping(value="")
    public String index(Model model){

        return "user/index";
    }

    @RequestMapping(value="add", method = RequestMethod.GET)
    public String add(Model model){
        model.addAttribute("title", "Add user");
        return "user/add";
    }

    @RequestMapping(value="add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute User user, String verify){

        String message = "";
        if(user.getPassword().equals(verify)){
            message = " Welcome " + user.getUsername();
            model.addAttribute("message", message);
            return "user/index";
        }
        else{

            message = "Password / Confirm password do not match";
            model.addAttribute("user", user);
            model.addAttribute("message", message);
            return "user/add";
        }


    }
}
