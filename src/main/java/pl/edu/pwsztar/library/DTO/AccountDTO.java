package pl.edu.pwsztar.library.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {

    private String login;
    private String password;
    private String name;
    private String lastName;
    private String email;
    private String place;
    private String street;
    private String homeNumber;
    private String phoneNumber;
}
