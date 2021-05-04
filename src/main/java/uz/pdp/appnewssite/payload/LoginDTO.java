package uz.pdp.appnewssite.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {

    @NotNull(message = "Username bo'sh bo'lmasligi kk")
    private String username;
    @NotNull(message = "Parol bo'sh bo'lmasligi kk")
    private String password;

}
