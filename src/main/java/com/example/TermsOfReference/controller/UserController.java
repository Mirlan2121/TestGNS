package com.example.TermsOfReference.controller;


import com.example.TermsOfReference.entity.User;
import com.example.TermsOfReference.model.UserAuthModel;
import com.example.TermsOfReference.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/userall")
    public String getAllUsers(Model model){
        Iterable<User> allUser = userService.getAllUsers();
        model.addAttribute("users", allUser);
        model.addAttribute("log", "Не известный пользователь");
        return "users";
    }

    @GetMapping("/newuser")
    public String menuUsers(Model model){
        return "newuser";
    }
    @GetMapping("/loguser")
    public String logUsers(Model model){
        return "loguser";
    }

    @PostMapping("/loguser")// санек
    public String logUser (@RequestParam String login, @RequestParam String password, Model model){
        if((password.isEmpty() || password == null) || (login.isEmpty() || login == null)) {
            model.addAttribute("texterror", "Нельзя оставлять поля пустыми");
            return "loguser";
        }
        UserAuthModel user = UserAuthModel.builder().login(login).password(password).build();
        model.addAttribute("log", user.getLogin());
        String write =
        userService.getAuthorizerToken(UserAuthModel.builder().login(login).password(password).build());
        System.out.println(write);
        return "redirect:/userall";
    }

    @PostMapping("/newuser")
    public String newUser(@RequestParam String login, @RequestParam String password,
                          @RequestParam String gmail, @RequestParam String fullName,  Model model) {
        User user = User.builder()
                .login(login)
                .password(password)
                .gmail(gmail)
                .fullName(fullName)
                .userInfo("USER").isActive(3L).build();
        if ((password.isEmpty() || password == null) || (login.isEmpty() || login == null) ||
                (gmail.isEmpty() || gmail == null ) || (fullName.isEmpty() || (fullName == null))) {
            model.addAttribute("texterror", "Нельзя оставлять поля пустыми");
            return "newuser";

        }
        userService.saveUsers(user);
        return "userall";
    }
}
