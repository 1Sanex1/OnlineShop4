package en.shop.OnlineShop.controllers;

import en.shop.OnlineShop.models.Product;
import en.shop.OnlineShop.models.User;
import en.shop.OnlineShop.services.ProductsService;
import en.shop.OnlineShop.services.UsersService;
import en.shop.OnlineShop.util.Count;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;
    private final ProductsService productsService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsersController(UsersService usersService, ProductsService productsService, PasswordEncoder passwordEncoder){
        this.usersService = usersService;
        this.productsService = productsService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("incProduct/{id}")
    public String incProduct(@PathVariable("id") int id,
                             @ModelAttribute("count") Count count){
        Product product = productsService.find(id);
        productsService.changeCount(product.getId(), -count.getCount());

        return "redirect:/products/" + id;
    }

    @PostMapping("decProduct/{id}")
    public String decProduct(@PathVariable("id") int id,
                             @ModelAttribute("count") Count count){
        Product product = productsService.find(id);
        productsService.changeCount(product.getId(), count.getCount());

        return "redirect:/products/" + id;
    }

    @GetMapping()
    public String getAll(Model model){
        if (! usersService.isAdmin())
            return "redirect:/users/home";

        model.addAttribute("users", usersService.findAll());
        model.addAttribute("current", usersService.getCurrent());
        return "users/all";
    }

    @GetMapping("/{id}")
    public String get(@PathVariable("id") int id, Model model){
        if (! usersService.isAdmin())
            return "redirect:/users/home";

        model.addAttribute("user", usersService.find(id));
        model.addAttribute("usersService", usersService);
        return "users/get";
    }

    @GetMapping("/new")
    public String formCreateUser(@ModelAttribute("user") User user){
        if (! usersService.isAdmin())
            return "redirect:/users/home";

        return "users/new";
    }

    @PostMapping()
    public String postCreateUser(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult){
        if (! usersService.isAdmin())
            return "redirect:/users/home";

        if (bindingResult.hasErrors())
            return "users/new";

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_SHOPPER");
        usersService.save(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String getEdit(Model model, @PathVariable("id") int id){
        if (! usersService.isAdmin())
            return "redirect:/users/home";

        model.addAttribute("user", usersService.find(id));
        return "users/edit";
    }

    @PatchMapping("/{id}")
    public String postEdit(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult,
                         @PathVariable("id") int id){
        if (! usersService.isAdmin())
            return "redirect:/users/home";

        if (bindingResult.hasErrors())
            return "users/edit";

        usersService.update(id, user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        if (! usersService.isAdmin())
            return "redirect:/home";

        if (usersService.getCurrent().getId() != id)
            usersService.remove(id);

        return "redirect:/users";
    }

    @PostMapping("/{id}/upgrade")
    public String upgradeUser(@PathVariable("id") int id){
        if (! usersService.isAdmin())
            return "redirect:/users/home";

        User user = usersService.find(id);
        user.setRole("ADMIN");

        return "redirect:/users";
    }
}
