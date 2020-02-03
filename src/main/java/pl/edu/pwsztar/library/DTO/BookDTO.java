package pl.edu.pwsztar.library.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.edu.pwsztar.library.model.Author;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    private Long id;
    private String name;
    private String bookImageUrl;
    private String description;
    private Double price;
    private double averageGrade;
    @JsonIgnoreProperties("book")
    private List<Author> author;
}
