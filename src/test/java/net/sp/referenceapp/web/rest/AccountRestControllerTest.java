package net.sp.referenceapp.web.rest;

import net.sp.referenceapp.dto.AccountDTO;
import net.sp.referenceapp.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.UUID;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AccountRestController.class)
// @Profile("test")
public class AccountRestControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private AccountService accountService;

  @Test
  public void whenGetAccountById_thenReturnJsonArray() {

    Collection<AccountDTO> accounts = new ArrayList<>();
    AccountDTO account1 = new AccountDTO();
    account1.setId(UUID.randomUUID().toString());
    account1.setFirstName("John");
    account1.setLastName("Doe");

    account1.setPassword("password");
    account1.setContact("1234567890");
    Random random = new Random();
    String ssn = String.format("%09d", random.nextInt(1000000000));
    account1.setSsn(ssn);
    account1.setUsername("jdoe" + random.nextInt(1000000000));
    accounts.add(account1);

    Mockito.when(accountService.findAccountById(account1.getId())).thenReturn(account1);

    try {
      mockMvc
          .perform(get("/api/accounts/" + account1.getId()).contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk()).andExpect(jsonPath("$.username", is(account1.getUsername())));
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
}
