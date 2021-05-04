package uz.pdp.appnewssite.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    @NotNull(message = "text bo'sh bo'lishi mumkin emas")
    private String text;
    @NotNull(message = "post id bo'sh bo'lishi mumkin emas")
    private Long postId;
}
