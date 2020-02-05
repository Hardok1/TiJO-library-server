package pl.edu.pwsztar.library;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.edu.pwsztar.library.controller.AccountController;
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
    AccountService accountService;

    MockMvc mockMvc;

    @Test
    public void controllersTest() throws Exception {
        mockMvc = standaloneSetup(accountController).build();
        Account admin = accountService.createMockAdmin();
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/account/signup")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"login\":\"AA\", \"password\":\"BB\", \"name\":\"CC\", \"lastName\":\"DD\", \"email\":\"EE\", \"place\":\"FF\", \"street\":\"GG\", \"homeNumber\":\"HH\", \"phoneNumber\":\"II\"}")
        );
        resultActions.andExpect(status().isCreated());
        resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/account/signin")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" + "\t\"login\" : \"AA\",\n" + "\t\"password\" : \"BB\"\n" + "}")
        );
       resultActions.andExpect(status().isOk());
       resultActions.andExpect(content().json("\"login\": \"AA\",\n" +
               "    \"password\": \"BB\",\n" +
               "    \"name\": \"CC\""));
    }

}
