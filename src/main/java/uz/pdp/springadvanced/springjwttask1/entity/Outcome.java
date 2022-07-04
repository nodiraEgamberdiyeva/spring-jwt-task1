package uz.pdp.springadvanced.springjwttask1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Outcome {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Card fromCard;

    @OneToOne
    private Card toCard;

    private BigDecimal amount;
    private Date date;

    private BigDecimal commissionAmount;

}
