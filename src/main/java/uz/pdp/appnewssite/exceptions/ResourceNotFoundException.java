package uz.pdp.appnewssite.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;


@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
public class ResourceNotFoundException extends RuntimeException {
    private final String resourceName;
    private final String resourceField;
    private final Object object;
}
