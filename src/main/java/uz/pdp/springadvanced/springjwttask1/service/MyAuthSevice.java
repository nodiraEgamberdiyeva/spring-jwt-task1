package uz.pdp.springadvanced.springjwttask1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.springadvanced.springjwttask1.entity.User;
import uz.pdp.springadvanced.springjwttask1.repository.UserRepository;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class MyAuthSevice implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    //to auth user
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> byUsername = userRepository.findByUsername(username);
        if (byUsername.isPresent()){
            User user = byUsername.get();
            return new org.springframework.security.core.userdetails
                    .User(user.getUsername(), passwordEncoder.encode(user.getPassword()), new ArrayList<>());

        }
        if (username.equals("admin")) {
            return new org.springframework.security.core.userdetails
                    .User("admin", passwordEncoder.encode("12345"), new ArrayList<>());
        }
        throw new UsernameNotFoundException("user not found");
    }
}
