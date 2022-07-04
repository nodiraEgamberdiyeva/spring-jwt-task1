package uz.pdp.springadvanced.springjwttask1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
@Data
@AllArgsConstructor
public class IncomeDto {
    @NotNull
    private Long fromCardId;
    @NotNull
    private Long toCardId;
    @NotNull
    private BigDecimal amount;

}
