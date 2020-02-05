package pl.edu.pwsztar.library.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.edu.pwsztar.library.DTO.AccountDTO;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Account {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column
    private String login;

    @NotNull
    @Column
    private String password;

    @NotNull
    @Column
    private String name;

    @NotNull
    @Column
    private String lastName;

    @NotNull
    @Column
    private String email;

    @NotNull
    @Column
    private String userType;

    @NotNull
    @Column
    private String place;

    @NotNull
    @Column
    private String street;

    @NotNull
    @Column
    private String homeNumber;

    @NotNull
    @Column
    private String phoneNumber;

    public Account(@NotNull AccountDTO accountDTO) {
        this.login = accountDTO.getLogin();
        this.password = accountDTO.getPassword();
        this.name = accountDTO.getName();
        this.lastName = accountDTO.getLastName();
        this.email = accountDTO.getEmail();
        this.place = accountDTO.getPlace();
        this.street = accountDTO.getStreet();
        this.homeNumber = accountDTO.getHomeNumber();
        this.phoneNumber = accountDTO.getPhoneNumber();
        this.userType = "user";
    }
}
