package org.launchcode.cheesemvc.controllers;

import org.launchcode.cheesemvc.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

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
        model.addAttribute(new User());
        return "user/add";
    }

    @RequestMapping(value="add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid User user,  Errors error){

        if(error.hasErrors()){
            model.addAttribute("user",user);
            return "user/add";
        }

        String message = "";
        message = " Welcome " + user.getUsername();
        model.addAttribute("message", message);
        return "user/index";

        /*if(user.getPassword() != null && verify != null ) {
            if(user.getPassword().equals(verify)){
                message = " Welcome " + user.getUsername();
                model.addAttribute("message", message);
                return "user/index";
            }
            else{

                message = "Password / Confirm password do not match";
                user.setPassword("");
                model.addAttribute("user", user);
                model.addAttribute("message", message);
                return "user/add";
            }
        }
        else{
            message = "Password / Confirm password can not be null";
            user.setPassword("");
            model.addAttribute("user", user);
            model.addAttribute("message", message);
            return "user/add";
        }*/
    }
}
