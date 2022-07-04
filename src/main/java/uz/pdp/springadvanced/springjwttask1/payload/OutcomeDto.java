package uz.pdp.springadvanced.springjwttask1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import uz.pdp.springadvanced.springjwttask1.entity.Card;

import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
public class OutcomeDto {
    @NotNull
    private Long fromCardId;
    @NotNull
    private Long toCardId;
    @NotNull
    private BigDecimal amount;

    @NotNull
    private BigDecimal commissionAmount;
}
