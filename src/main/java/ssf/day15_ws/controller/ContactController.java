package ssf.day15_ws.controller;

import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ssf.day15_ws.models.Contact;
import ssf.day15_ws.services.ContactService;

@Controller
@RequestMapping
public class ContactController {
    
    private final Logger logger = Logger.getLogger(ContactController.class.getName());

    @Autowired
    private ContactService contactSvc;

    @GetMapping("/contact/{id}")
    public ModelAndView getContactById(@PathVariable String id) {
        logger.info("[Controller] Redirecting to /contact/{id}");
        Optional<Contact> opt = contactSvc.getContactById(id);
        ModelAndView mav = new ModelAndView();

        if(opt.isEmpty()) {
            logger.info("[Controller] ID not found");
            mav.setViewName("not-found");
            mav.setStatus(HttpStatusCode.valueOf(404));
            mav.addObject("id", id);
            return mav;
        }

        Contact contact = opt.get();
        logger.info("[Controller] ID found");
        mav.setViewName("contact-info");
        mav.setStatus(HttpStatusCode.valueOf(200));
        mav.addObject("contact", contact);
        return mav;
    }

    @GetMapping("/contacts")
    public String getContacts(Model model) {
        logger.info("[Controller] Retriving all contact ids from redis");
        model.addAttribute("idList", contactSvc.getIds());
        return "contacts";
    }

    @PostMapping("/contact")
    public String postContact(
        @ModelAttribute Contact contact,
        Model model) {
            contactSvc.insertContact(contact);
            model.addAttribute("name", contact.getName());
            return "added";
    }
}
