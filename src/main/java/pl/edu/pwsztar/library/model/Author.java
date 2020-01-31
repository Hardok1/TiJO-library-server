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
@Table(name = "author")
public class Author {

    @GeneratedValue
    @Id
    private Long id;

    @NotNull
    @Column(name = "author_name")
    private String authorName;

    @ManyToMany
    @JoinColumn
    private List<Book> book = new ArrayList<>();
}
