package com.nwchat.сontroller;

import com.nwchat.entity.UserEntity;
import com.nwchat.repository.UserRepository;
import com.nwchat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView model = new ModelAndView();

        model.setViewName("user/login");
        return model;
    }

//    @RequestMapping(value = "/signup", method = RequestMethod.GET)
//    public ModelAndView signup() {
//        ModelAndView model = new ModelAndView();
//        UserEntity user = new UserEntity();
//        model.addObject("user", user);
//        model.setViewName("user/signup");
//
//        return model;
//    }

//    @RequestMapping(value = "/signup", method = RequestMethod.POST)
//    public ModelAndView createUser(@Valid UserEntity user, BindingResult bindingResult) {
//        ModelAndView model = new ModelAndView();
//        UserEntity userExists = userService.findUserByLogin(user.getLogin());
//
//        if (userExists != null) {
//            bindingResult.rejectValue("login", "error.user", "This login already exists!");
//        }
//        if (bindingResult.hasErrors()) {
//            model.setViewName("user/signup");
//        } else {
//            user.setRoleId(1);
//            userService.saveUser(user);
//            model.addObject("msg", "User has been registered successfully!");
//            model.setViewName("redirect:/user/login");
//        }
//        return model;
//    }
//
//    @RequestMapping(value = "/checkLogin", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Boolean> checkLogin(@RequestBody String login) {
//        UserEntity userExists = userService.findUserByLogin(login);
//
//        return new ResponseEntity<>(userExists != null, HttpStatus.OK);
//    }

    @GetMapping("/list")
    public Model userList(Model model,
                          @RequestParam(value = "page", defaultValue = "1") int page,
                          @RequestParam(value = "size", defaultValue = "10") int size,
                          @RequestParam(name = "value", required = false) String value) {
        Page<UserEntity> all;
        PageRequest pageable = PageRequest.of(page - 1, size);

        if (value != null && !value.isEmpty()) {
            all = userService.findAllByLastnameContainingIgnoreCase(pageable, value);
        } else {
            all = userService.findAll(pageable);
        }

        model.addAttribute("key", value);
        model.addAttribute("userPage", all);
        model.addAttribute("pageNumbers", getPageNumbers(all));

        return model;
    }

    private List<Integer> getPageNumbers(Page<UserEntity> page) {
        List<Integer> pageNumbers = new ArrayList<>();

        if (page != null) {
            int totalPages = page.getTotalPages();
            if (totalPages > 0) {
                pageNumbers = IntStream.rangeClosed(1, totalPages)
                        .boxed()
                        .collect(Collectors.toList());

            }
        }

        return pageNumbers;
    }

    @GetMapping("/form")
    public ModelMap showUser(@RequestParam(value = "id",required = false) UserEntity user){
        if (user == null){
            user = new UserEntity();
        }
        return  new ModelMap("user", user);
    }

    @PostMapping("/form")
    public String addNewUser(@ModelAttribute @Valid UserEntity user, BindingResult errors, SessionStatus status){
        if (errors.hasErrors()){
            return "user/form";
        }
        userService.saveUser(user);
        status.setComplete();
        return "redirect:list";
    }

    @RequestMapping("/delete")
    public String deleteUser(@RequestParam("id") int id) {
        userService.deleteById(id);
        return "redirect:list";
    }
}
