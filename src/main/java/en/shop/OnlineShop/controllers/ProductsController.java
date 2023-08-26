package en.shop.OnlineShop.controllers;

import en.shop.OnlineShop.models.Product;
import en.shop.OnlineShop.services.ProductsService;
import en.shop.OnlineShop.services.UsersService;
import en.shop.OnlineShop.util.Count;
import en.shop.OnlineShop.util.ShopHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductsController {
    private final ProductsService productsService;
    private final UsersService usersService;

    @Autowired
    public ProductsController(ProductsService productsService, UsersService usersService){
        this.productsService = productsService;
        this.usersService = usersService;
    }

    @GetMapping("/basket")
    public String basket(Model model){
        if (! usersService.isShopper())
            return "redirect:/products";

        return "products/basket";
    }

    private void addUserModels(Model model){
        model.addAttribute("isVisitor", usersService.isVisitor());
        model.addAttribute("isShopper", usersService.isShopper());
        model.addAttribute("isAdmin", usersService.isAdmin());
    }

    @GetMapping()
    public String findAll(Model model){
        model.addAttribute("products", productsService.findAll());
        model.addAttribute("usersService", usersService);
        model.addAttribute("shopHelper", new ShopHelper());
        addUserModels(model);

        return "products/all";
    }

    @GetMapping("/{id}")
    public String get(@PathVariable("id") int id, Model model){
        model.addAttribute("product", productsService.find(id));
        model.addAttribute("usersService", usersService);
        model.addAttribute("productsService", productsService);
        model.addAttribute("shopHelper", new ShopHelper());
        model.addAttribute("count", new Count());
        addUserModels(model);

        return "products/get";
    }

    @GetMapping("/new")
    public String newProduct(@ModelAttribute("product") Product product){

        return "products/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("product") @Valid Product product,
                         BindingResult bindingResult){
        if (! usersService.isAdmin()){
            return "redirect:/products";
        }

        if (bindingResult.hasErrors()) {
            return "products/new";
        }
        productsService.save(product);
        return "redirect:/products";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        if (! usersService.isAdmin())
            return "redirect:/products";

        model.addAttribute("product", productsService.find(id));
        return "products/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("product") @Valid Product product,
                         BindingResult bindingResult,
                         @PathVariable("id") int id){
        if (!usersService.isAdmin())
            return "redirect:/products";

        if (bindingResult.hasErrors())
            return "/products/edit";

        productsService.update(id, product);
        return "redirect:/products";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        if (usersService.isAdmin()) {
            productsService.remove(id);
            System.out.println("removed");
        }

        return "redirect:/products";
    }
}

















