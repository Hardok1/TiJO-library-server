package pl.edu.pwsztar.library.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
@Entity
@Table(name = "book")
public class Book {

    @GeneratedValue
    @Id
    private Long id;

    @NotNull
    @Column
    private String name;

    @NotNull
    @Column(name = "book_image_url")
    private String bookImageUrl;

    @NotNull
    @Column
    private String description;

    @NotNull
    @Column
    private Double price;

    @Basic
    @Temporal(TemporalType.DATE)
    private java.util.Date date;

    @ManyToMany
    @JoinColumn
    private Collection<Author> author = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "book_copy")
    private Collection<BookCopy> bookCopy = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "review")
    private Collection<Review> review = new ArrayList<>();

}
