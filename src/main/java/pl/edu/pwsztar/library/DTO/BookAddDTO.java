package pl.edu.pwsztar.library.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookAddDTO {
    private String name;
    private String bookImageUrl;
    private String description;
    private double price;
    private List<Long> authors;
    private int quantity;
    private Long accountId;
}
