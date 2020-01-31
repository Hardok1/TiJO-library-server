package pl.edu.pwsztar.library.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToMany
    @JoinColumn
    private List<Author> author = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "book_copy")
    private List<BookCopy> bookCopy = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "review")
    private List<Review> review = new ArrayList<>();

    public Book() {
    }

    public Book(String name, String description, String bookImageUrl, Double price, List<Author> authors) {
        this.name = name;
        this.description = description;
        this.bookImageUrl = bookImageUrl;
        this.price = price;
        this.author = authors;
    }
}
