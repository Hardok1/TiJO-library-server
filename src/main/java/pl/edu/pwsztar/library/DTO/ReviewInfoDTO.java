package pl.edu.pwsztar.library.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewInfoDTO {
    private Integer averageGrade;
    private Integer reviewsCount;
}
