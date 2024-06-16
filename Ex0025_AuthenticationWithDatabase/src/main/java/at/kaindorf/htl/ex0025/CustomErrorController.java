package at.kaindorf.htl.ex0025;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @GetMapping("/error")
    public String handleError() {
        return "error";
    }

    @GetMapping("/403")
    public String handle403() {
        return "403";
    }
}
