package pl.edu.pwsztar.library.model;

import lombok.Getter;
import lombok.Setter;
import pl.edu.pwsztar.library.DTO.ReviewDTO;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table
public class Review {

    @GeneratedValue
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn
    private Account account;

    @ManyToOne
    @JoinColumn
    private Book book;

    @NotNull
    @Basic
    private int grade;

    @NotNull
    @Basic
    private String content;

    @NotNull
    @Column
    @Temporal(TemporalType.DATE)
    private java.util.Calendar date;

    public Review() {
    }

    public Review(ReviewDTO reviewDTO){

    }
}
