package dev.backend.demo.controller;

import dev.backend.demo.entity.User;
import dev.backend.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@AllArgsConstructor
@RestController
@RequestMapping(path = "users", produces = APPLICATION_JSON_VALUE)
public class UserController {
   private UserService userService;
   @GetMapping
   public Iterable<User> getAllUsers(
       @RequestParam(required = false) String name
   ) {
      return this.userService.listUsers(name);
   }
}
