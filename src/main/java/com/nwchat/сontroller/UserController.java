package com.nwchat.сontroller;

import com.nwchat.entity.UserEntity;
import com.nwchat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public String userList(Model model,
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

        return "users/list";
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
    public String showUser(Model model, @RequestParam(value = "id", required = false) UserEntity user) {
        if (user == null) {
            user = new UserEntity();
        }
        model.addAttribute("user", user);

        return "users/form";
    }

    @PostMapping("/form")
    public String addNewUser(@ModelAttribute @Valid UserEntity user, BindingResult errors, SessionStatus status) {
        if (errors.hasErrors()) {
            return "users/form";
        }
        userService.saveUser(user);
        status.setComplete();
        return "redirect:";
    }

    @RequestMapping("/delete")
    public String deleteUser(@RequestParam("id") int id) {
        userService.deleteById(id);
        return "redirect:";
    }
}
