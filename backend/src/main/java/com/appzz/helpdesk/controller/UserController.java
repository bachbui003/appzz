package com.appzz.helpdesk.controller;

import com.appzz.helpdesk.model.AppUser;
import com.appzz.helpdesk.repository.AppUserRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final AppUserRepository users;
    public UserController(AppUserRepository users) { this.users = users; }
    @GetMapping public List<AppUser> listUsers() { return users.findAll(); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public AppUser createUser(@Valid @RequestBody AppUser user) { return users.save(user); }
}
