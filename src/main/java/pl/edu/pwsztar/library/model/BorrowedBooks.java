package pl.edu.pwsztar.library.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "borrowed_book")
public class BorrowedBooks {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "book_copy_id")
    private BookCopy bookCopy;

    @NotNull
    @Column(name = "rent_date")
    @Temporal(TemporalType.DATE)
    private java.util.Date rentDate;

    @Column(name = "return_date")
    @Temporal(TemporalType.DATE)
    private java.util.Date returnDate;
}
