package uz.pdp.springadvanced.springjwttask1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springadvanced.springjwttask1.entity.Outcome;
import uz.pdp.springadvanced.springjwttask1.entity.User;

public interface OutcomeRepository extends JpaRepository<Outcome, Long> {

}
