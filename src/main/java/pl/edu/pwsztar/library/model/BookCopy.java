package pl.edu.pwsztar.library.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table
public class BookCopy {

    @GeneratedValue
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn
    private Book book;

    @NotNull
    @Column
    private boolean borrowed;
}
