package uz.pdp.springadvanced.springjwttask1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseApi {
    private String message;
    private boolean status;
}
