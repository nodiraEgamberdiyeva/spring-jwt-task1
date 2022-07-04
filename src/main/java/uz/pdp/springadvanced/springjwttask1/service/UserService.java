package uz.pdp.springadvanced.springjwttask1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.springadvanced.springjwttask1.entity.User;
import uz.pdp.springadvanced.springjwttask1.payload.ResponseApi;
import uz.pdp.springadvanced.springjwttask1.payload.UserDto;
import uz.pdp.springadvanced.springjwttask1.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Page<User> getUsers(int page){
        Pageable pageable = PageRequest.of(page, 10);
        return userRepository.findAll(pageable);
    }

    public User getUser(Long id){
        Optional<User> byId = userRepository.findById(id);
        return byId.orElse(null);
    }

    public ResponseApi addUser(UserDto userDto){
        if (userRepository.existsByUsername(userDto.getUsername())){
            return new ResponseApi("exist", false);
        }
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        userRepository.save(user);
        return new ResponseApi("added", true);
    }
}
