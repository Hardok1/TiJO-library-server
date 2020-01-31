package pl.edu.pwsztar.library.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "book_copy")
public class BookCopy {

    @GeneratedValue
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @NotNull
    @Column(name = "is_borrowed")
    private boolean borrowed;
}
