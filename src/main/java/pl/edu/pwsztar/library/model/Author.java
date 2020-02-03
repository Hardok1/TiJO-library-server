package pl.edu.pwsztar.library.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table
public class Author {

    @GeneratedValue
    @Id
    private Long id;

    @NotNull
    @Column
    private String authorName;

    @JsonIgnoreProperties("author")
    @ManyToMany(mappedBy="author",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Book> book = new ArrayList<>();
}
