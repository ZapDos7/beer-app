package request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddBeerRequest {
    private String name;
    private String country;
    private String description;
}
