package at.kaindorf.htl.ex0025;

import at.kaindorf.htl.ex0025.ShoeFiles.ShoeDto;
import at.kaindorf.htl.ex0025.ShoeFiles.ShoeService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MyUserController {
  private final Logger logger = LoggerFactory.getLogger(getClass());
  private final IMyUserService userService;
  private final MyUserDetailsService userDetailsService;
  private final ShoeService shoeService;

  @Autowired
  public MyUserController(IMyUserService userService, MyUserDetailsService userDetailsService, ShoeService shoeService) {
    this.userService = userService;
    this.userDetailsService = userDetailsService;
    this.shoeService = shoeService;
  }

  @GetMapping("login")
  public String loginForm() {
    logger.info("--> loginForm() !!");
    return "login";
  }

  @GetMapping({"/", "index"})
  public String homePage() {
    logger.info("--> homePage() !!");
    return "index";
  }

  @GetMapping("register")
  public String showRegistrationForm(Model model) {
    logger.info("--> showRegistrationForm() !!");
    UserDto user = new UserDto();
    model.addAttribute("user", user);
    return "register";
  }

  @PostMapping("register/save")
  public String registration(@Valid @ModelAttribute("user") UserDto user, BindingResult result, Model model) {
    logger.info("Attempting to register user: {}", user);
    if (userDetailsService.userExists(user.getName())) {
      logger.warn("Attempted to register existing user: {}", user.getName());
      result.rejectValue("name", null, "There is already an account registered with that name");
    }
    if (result.hasErrors()) {
      logger.error("Registration errors for user: {}", user.getName());
      model.addAttribute("user", user);
      return "register";
    }
    userService.saveUser(user);
    logger.info("User registered successfully: {}", user.getName());
    return "redirect:/register?success";
  }


  @GetMapping("/users")
  public String listRegisteredUsers(Model model) {
    model.addAttribute("shoes", shoeService.getAllShoes());
    return "users";
  }

  @GetMapping("/addShoe")
  public String showAddShoeForm(Model model) {
    model.addAttribute("shoe", new ShoeDto());
    return "addShoe";
  }

  @PostMapping("/addShoe")
  public String addShoe(@Valid @ModelAttribute("shoe") ShoeDto shoeDto, BindingResult result) {
    if (result.hasErrors()) {
      return "addShoe";
    }
    shoeService.saveShoe(shoeDto);
    return "redirect:/users";
  }
}
