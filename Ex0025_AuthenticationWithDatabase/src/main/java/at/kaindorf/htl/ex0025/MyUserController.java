package at.kaindorf.htl.ex0025;

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
public class MyUserController
{
  private Logger logger = LoggerFactory.getLogger(getClass());

  private IMyUserService userService;

  @Autowired
  public MyUserController(IMyUserService userService)
  {
    this.userService = userService;
  }

  @GetMapping("login")
  public String loginForm()
  {
    logger.info("--> loginForm() !!");
    return "login";
  }

  @GetMapping( {"/", "index" } )
  public String homePage()
  {
    logger.info("--> homePage() !!");
    return "index";
  }

  @GetMapping("register")
  public String showRegistrationForm(Model model)
  {
    logger.info("--> showRegistrationForm() !!");
    UserDto user = new UserDto();
    model.addAttribute("user",user);
    return "register";
  }

  @PostMapping("register/save")
  public String registration(@Valid @ModelAttribute("user") UserDto user, BindingResult result, Model model)
  {
    logger.info("--> registration() !!");
    logger.info(" --> " + user);

    MyUser existingUser = userService.findByName( user.getName() );
    logger.info("--> " + existingUser);
    if(existingUser != null && existingUser.getName() != null && ! existingUser.getName().isEmpty())
    {
      result.rejectValue("name",null,
              "There is already an account registered with that name");
    }
    if(result.hasErrors())
    {
      logger.info("--> ERROR !!!");
      model.addAttribute("user", user);
      return "register";
    }
    userService.saveUser(user);
    return "redirect:/register?success";
  }

  @GetMapping("/users")
  public String listRegisteredUsers(Model model)
  {
    model.addAttribute("users",userService.findAllUsers());
    return "users";
  }

}
