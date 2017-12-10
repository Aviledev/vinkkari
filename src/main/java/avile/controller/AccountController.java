package avile.controller;

import avile.domain.Account;
import avile.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class AccountController {

    @Autowired
    AccountService accountService;


    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }
    @GetMapping("/register")
    public String getRegisterPage(@ModelAttribute Account account) {
        return "register";
    }
    @PostMapping("/register")
    public String processRegistration(@Valid @ModelAttribute Account account, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        accountService.save(account);
        redirectAttributes.addFlashAttribute("messageType", "success");
        redirectAttributes.addFlashAttribute("message", "Account successfully created");
        return "redirect:/login";
    }
}
