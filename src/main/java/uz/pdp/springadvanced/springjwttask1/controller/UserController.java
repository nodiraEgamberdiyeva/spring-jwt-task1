package uz.pdp.springadvanced.springjwttask1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springadvanced.springjwttask1.entity.User;
import uz.pdp.springadvanced.springjwttask1.payload.UserDto;
import uz.pdp.springadvanced.springjwttask1.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public HttpEntity<?> getUsers(@RequestParam int page){
        return ResponseEntity.ok(userService.getUsers(page));
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getUser(@PathVariable Long id){
        User user = userService.getUser(id);
        return ResponseEntity.status(user!=null?200:409).body(user);
    }

    @PostMapping
    public HttpEntity<?> addUser(@RequestBody @Valid UserDto userDto){
        if (userService.addUser(userDto).isStatus()){
            return ResponseEntity.ok(userService.addUser(userDto));
        }
        return ResponseEntity.status(409).body(userService.addUser(userDto));
    }
}
