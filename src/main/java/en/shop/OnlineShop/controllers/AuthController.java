package en.shop.OnlineShop.controllers;

import en.shop.OnlineShop.models.User;
import en.shop.OnlineShop.services.RegistrationService;
import en.shop.OnlineShop.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final RegistrationService registrationService;
    private final UsersService usersService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(RegistrationService registrationService, UsersService usersService, PasswordEncoder passwordEncoder) {
        this.registrationService = registrationService;
        this.usersService = usersService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String loginPage() {
        if (usersService.findAll().size() == 0){
            User user = new User("admin", "admin", "admin@gmail.com", "admin");
            user.setRole("ROLE_ADMIN");
            user.setPassword(passwordEncoder.encode("admin"));
            usersService.save(user);
        }

        return "/auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("user") User user){
        return "/auth/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("user") @Valid User user,
                                      BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "/auth/registration";
        registrationService.uploadUser(user);

        return "redirect:/auth/login";
    }


}
