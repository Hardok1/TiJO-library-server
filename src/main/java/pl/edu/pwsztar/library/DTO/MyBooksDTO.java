package pl.edu.pwsztar.library.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyBooksDTO {
    private long bookCopyId;
    private long bookId;
    private Calendar borrowDate;
}
