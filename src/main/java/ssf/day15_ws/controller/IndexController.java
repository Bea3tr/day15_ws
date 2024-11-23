package ssf.day15_ws.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ssf.day15_ws.models.Contact;

@Controller
@RequestMapping
public class IndexController {

    @GetMapping(path={"/", "index.html"})
    public String getIndex(Model model) {
        model.addAttribute("contact", new Contact());
        return "index";
    }
    
}
