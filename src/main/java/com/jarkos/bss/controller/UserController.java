package com.jarkos.bss.controller;

import com.jarkos.bss.service.UserService;
import com.jarkos.bss.persistance.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/admin/users")
    public String getUserList(Model model) {
        log.debug("getUserList");
        model.addAttribute(userService.findAllUsers());

        return "users";
    }

    @RequestMapping(value = "/admin/users/create", method = RequestMethod.GET)
    public String createNewUserForm(Model model) {
        model.addAttribute("user", new User());
        return "users-create";
    }

    @RequestMapping(value = "/admin/users/create", method = RequestMethod.POST)
    public String createNewUser(@Valid User user, BindingResult bindingResult) {
        log.debug("createNewUser, username={}, email={}, errorCount={}", user.getUsername(), user.getEmail(), bindingResult.getErrorCount());

        if (userService.findUserByUsername(user.getUsername()) != null) {
            bindingResult.rejectValue("username", "error.username.exist");
        }

        if (userService.findUserByEmail(user.getEmail()) != null) {
            bindingResult.rejectValue("email", "error.email.exist");
        }

        if (bindingResult.hasErrors()) {
            return "redirect:/admin/users?created=false";
        }
        user.setAccountBalance(0);
        user.setEnabled(true);
        user.setAccountBalance(20);
        userService.saveUser(user);

        return "redirect:/admin/users?created=true";
    }

    @RequestMapping(value = "/admin/users/{id}/delete", method = RequestMethod.GET)
    public String deleteUserForm(@PathVariable int id) {
        if (id != 0) {
            User userToDelete = userService.findUserById(id);
            if (userToDelete != null) {
                userService.deleteUser(userToDelete);
            } else {
                log.debug("deleteUser, id={}", id);
                System.out.printf("ERROR. Nie ma takiego u¿ytkownika");
            }
            return "redirect:/admin/users?deleted=true";
        }
        return "redirect:/admin/users?deleted=false";
    }

    @RequestMapping(value = "/admin/users/{id}/edit", method = RequestMethod.GET)
    public String editUserForm(@PathVariable int id, Model model) {

        User user = userService.findUserById(id);
        model.addAttribute("user", user);
        return "users-edit";
    }

    @RequestMapping(value = "/admin/users/{id}/edit", method = RequestMethod.POST)
    public String editUser(@Valid User user, @PathVariable int id) {
        userService.update(user);
        return "redirect:/admin/users?edited=true";
    }
}
