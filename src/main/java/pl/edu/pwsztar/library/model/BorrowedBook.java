package pl.edu.pwsztar.library.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table
public class BorrowedBook {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn
    private Account account;

    @ManyToOne
    @JoinColumn
    private BookCopy bookCopy;

    @NotNull
    @Column
    @Temporal(TemporalType.DATE)
    private java.util.Calendar borrowDate;

    @Column
    @Temporal(TemporalType.DATE)
    private java.util.Calendar returnDate;
}
