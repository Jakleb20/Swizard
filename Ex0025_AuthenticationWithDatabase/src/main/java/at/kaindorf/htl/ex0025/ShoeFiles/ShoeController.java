package at.kaindorf.htl.ex0025.ShoeFiles;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

@Controller
public class ShoeController {

    private final ShoeService shoeService;

    @Autowired
    public ShoeController(ShoeService shoeService) {
        this.shoeService = shoeService;
    }

    @GetMapping("/admin/addShoe")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String showAddShoeForm(Model model) {
        model.addAttribute("shoe", new ShoeDto());
        return "addShoe";
    }

    @PostMapping("/admin/addShoe")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addShoe(@Valid @ModelAttribute("shoe") ShoeDto shoeDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "addShoe";
        }
        shoeService.saveShoe(shoeDto);
        return "redirect:/shoes";
    }

    @GetMapping("/shoes")
    public String listShoes(Model model) {
        model.addAttribute("shoes", shoeService.getAllShoes());
        return "shoes";
    }
}


