package uz.pdp.appnewssite.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
    @NotNull(message = "Title bo'sh bo'lmasligi kk")
    private String title;
    @NotNull(message = "Text bo'sh bo'lmasligi kk")
    private String text;
    @NotNull(message = "url bo'sh bo'lmasligi kk")
    private String url;
}
