package en.shop.OnlineShop.controllers;

import en.shop.OnlineShop.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {
    private final UsersService usersService;

    @Autowired
    public MainController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/home")
    public String home(Model model){
        model.addAttribute("isShopper", usersService.isShopper());
        model.addAttribute("isAdmin", usersService.isAdmin());
        return "home";
    }
}
