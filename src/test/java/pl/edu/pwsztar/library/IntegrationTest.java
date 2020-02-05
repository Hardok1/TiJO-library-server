package pl.edu.pwsztar.library;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.edu.pwsztar.library.controller.AccountController;
import pl.edu.pwsztar.library.controller.AuthorController;
import pl.edu.pwsztar.library.controller.BookController;
import pl.edu.pwsztar.library.controller.BorrowedBookController;
import pl.edu.pwsztar.library.model.Account;
import pl.edu.pwsztar.library.service.AccountService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTest {

    @Autowired
    AccountController accountController;

    @Autowired
    AuthorController authorController;

    @Autowired
    BookController bookController;

    @Autowired
    BorrowedBookController borrowedBookController;

    @Autowired
    AccountService accountService;

    MockMvc mockMvcAccount;
    MockMvc mockMvcAuthor;
    MockMvc mockMvcBook;
    MockMvc mockMvcBorrowedBook;

    @Test
    public void controllersTest() throws Exception {
        mockMvcAccount = standaloneSetup(accountController).build();
        mockMvcAuthor = standaloneSetup(authorController).build();
        mockMvcBook = standaloneSetup(bookController).build();
        mockMvcBorrowedBook = standaloneSetup(borrowedBookController).build();
        Account admin = accountService.createMockAdmin();

        //Creating account
        ResultActions resultActions = mockMvcAccount.perform(
                MockMvcRequestBuilders.post("/account/signup")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"login\":\"AA\", \"password\":\"BB\", \"name\":\"CC\", \"lastName\":\"DD\", \"email\":\"EE\", \"place\":\"FF\", \"street\":\"GG\", \"homeNumber\":\"HH\", \"phoneNumber\":\"II\"}")
        );
        resultActions.andExpect(status().isCreated());


        //Login to account
        resultActions = mockMvcAccount.perform(
                MockMvcRequestBuilders.post("/account/signin")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" + "\t\"login\" : \"AA\",\n" + "\t\"password\" : \"BB\"\n" + "}")
        );
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(content().json("{\"login\": \"AA\",\n" +
                "    \"password\": \"BB\",\n" +
                "    \"name\": \"CC\"}"));

        //Add author (adds author with id 3)
        resultActions = mockMvcAuthor.perform(
                MockMvcRequestBuilders.post("/authors/addAuthor")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "\t\"authorName\" : \"Andrzej Sapkowski\",\n" +
                                "\t\"accountId\" : \"" + admin.getId() +"\"\n" +
                                "}")
        );
        resultActions.andExpect(status().isCreated());

        //Add Book
        resultActions = mockMvcBook.perform(
                MockMvcRequestBuilders.post("/books/addBook")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "\t    \"name\": \"Harry Potter\",\n" +
                                "        \"bookImageUrl\": \"adres url do miniaturki\",\n" +
                                "        \"description\": \"opis książki\",\n" +
                                "        \"price\": 20,\n" +
                                "        \"authors\": [3],\n" +
                                "        \"quantity\": 2,\n" +
                                "\t    \"accountId\": "+admin.getId()+"\n" +
                                "}")
        );
        resultActions.andExpect(status().isCreated());


    }


}
