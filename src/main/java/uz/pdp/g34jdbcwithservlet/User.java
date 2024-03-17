package uz.pdp.g34jdbcwithservlet;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class User {
    private Long id;
    private String username;
    private String password;
    private String email;
}
