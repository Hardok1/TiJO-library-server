package pl.edu.pwsztar.library.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Calendar;

@Data
@AllArgsConstructor
public class MyBooksDTO {
    private long bookCopyId;
    private long bookId;
    private Calendar borrowDate;
}
