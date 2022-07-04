package uz.pdp.springadvanced.springjwttask1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springadvanced.springjwttask1.entity.Card;
import uz.pdp.springadvanced.springjwttask1.entity.User;

public interface CardRepository extends JpaRepository<Card, Long> {
    boolean existsByNumber(String number);
    boolean existsByNumberAndIdNot(String number, Long id);
}
