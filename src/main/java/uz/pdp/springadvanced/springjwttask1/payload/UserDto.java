package uz.pdp.springadvanced.springjwttask1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class UserDto {

    @NotNull
    private String username;
    @NotNull
    private String password;
}
