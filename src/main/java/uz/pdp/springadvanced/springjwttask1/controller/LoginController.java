package uz.pdp.springadvanced.springjwttask1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.springadvanced.springjwttask1.payload.UserDto;
import uz.pdp.springadvanced.springjwttask1.security.JwtBuild;

@RestController
@RequestMapping("/login")
public class LoginController {

    //bean from myAuthService
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtBuild jwtBuild;

    @PostMapping
    public HttpEntity<?> loginUser(@RequestBody UserDto userDto){
        try{

            //auth user and return generated token
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword())
            );

            return ResponseEntity.ok().body(jwtBuild.generateToken(userDto.getUsername()));
        }
        catch (BadCredentialsException e){
            return ResponseEntity.status(409).body("");
        }
    }
}
