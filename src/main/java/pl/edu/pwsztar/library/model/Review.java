package pl.edu.pwsztar.library.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "review")
public class Review {

    @GeneratedValue
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @NotNull
    @Basic
    private int grade;

    @NotNull
    @Basic
    private String review;

    @NotNull
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private java.util.Date date;

}
