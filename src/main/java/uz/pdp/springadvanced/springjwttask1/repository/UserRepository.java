package uz.pdp.springadvanced.springjwttask1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springadvanced.springjwttask1.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);
    boolean existsByUsernameAndIdNot(String username, Long id);

    Optional<User> findByUsername(String username);
}
