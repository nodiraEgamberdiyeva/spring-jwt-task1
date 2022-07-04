package uz.pdp.springadvanced.springjwttask1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
public class CardDto {
    @NotNull
    private Long userId;
    @NotNull
    private String number;
    @NotNull
    private BigDecimal balance;
    @NotNull
    private Date expireDate;
    @NotNull
    private boolean active;
}
