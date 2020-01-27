package pl.edu.pwsztar.library.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "account")
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
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Column
    private String email;

    @NotNull
    @Column(name = "user_type")
    private String userType;

    @NotNull
    @Column
    private String place;

    @NotNull
    @Column
    private String street;

    @NotNull
    @Column(name = "home_number")
    private String homeNumber;

    @NotNull
    @Column(name = "phone_number")
    private String phoneNumber;

    /*public Account(Long id, @NotNull String login, @NotNull String password, @NotNull String name, @NotNull String lastName, @NotNull String email, @NotNull String userType, @NotNull String place, @NotNull String street, @NotNull String homeNumber, @NotNull String phoneNumber) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.userType = userType;
        this.place = place;
        this.street = street;
        this.homeNumber = homeNumber;
        this.phoneNumber = phoneNumber;
    }

    public Account(@NotNull String login, @NotNull String password, @NotNull String name, @NotNull String lastName, @NotNull String email, @NotNull String userType, @NotNull String place, @NotNull String street, @NotNull String homeNumber, @NotNull String phoneNumber) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.userType = userType;
        this.place = place;
        this.street = street;
        this.homeNumber = homeNumber;
        this.phoneNumber = phoneNumber;
    }

    public Account() {
    }*/
}
