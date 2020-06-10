package controller;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.IUserService;
import validator.UserValidator;

@Controller
@RequestMapping()
public class UserController {

    @Autowired
    public IUserService userService;

    @GetMapping("/user")
    public ModelAndView showList(@PageableDefault(value = 2) Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("list");
        Page<User> list = userService.findAll(pageable);
        modelAndView.addObject("list",list);
        return modelAndView;
    }

    @GetMapping("/user/create")
    public ModelAndView showCreateForm(){
        return new ModelAndView ("createForm","user",new User());
    }

    @PostMapping("/user/create")
    public String createNew(@Validated @ModelAttribute User user, BindingResult bindingResult){
        new UserValidator().validate(user,bindingResult);
        if (bindingResult.hasFieldErrors()){
            return "createForm";
        }
        int id = userService.findAllList().size()+1;
        user.setId((long) id);
        userService.save(user);
        return "redirect:/user";
    }
}
