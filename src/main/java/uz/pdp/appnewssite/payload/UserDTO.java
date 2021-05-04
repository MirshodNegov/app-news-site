package uz.pdp.appnewssite.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @NotNull(message = "Ism bo'sh bo'lmasligi kk")
    private String fullName;
    @NotNull(message = "Username bo'sh bo'lmasligi kk")
    private String username;
    @NotNull(message = "parol bo'sh bo'lmasligi kk")
    private String password;
    @NotNull(message = "lavozim bo'sh bo'lmasligi kk")
    private Long roleId;

}
