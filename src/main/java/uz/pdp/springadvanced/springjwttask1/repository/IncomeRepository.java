package uz.pdp.springadvanced.springjwttask1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springadvanced.springjwttask1.entity.Income;
import uz.pdp.springadvanced.springjwttask1.entity.User;

public interface IncomeRepository extends JpaRepository<Income, Long> {

}
