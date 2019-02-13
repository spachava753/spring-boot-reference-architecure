package net.sp.referenceapp.web.rest;

import net.sp.referenceapp.dto.AccountDTO;
import net.sp.referenceapp.service.AccountAdminService;
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
import java.util.List;
import java.util.Random;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AccountAdminRestController.class)
// @Profile("test")
public class AccountAdminRestControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private AccountAdminService accountAdminService;

  @Test
  public void whenGetAllAccounts_thenReturnJsonArray() {
    List<AccountDTO> accounts = new ArrayList<>();
    AccountDTO account1 = new AccountDTO();
    account1.setFirstName("John");
    account1.setLastName("Doe");

    account1.setPassword("password");
    account1.setContact("1234567890");
    Random random = new Random();
    String ssn = String.format("%09d", random.nextInt(1000000000));
    account1.setSsn(ssn);
    account1.setUsername("jdoe" + random);
    accounts.add(account1);

    Mockito.when(accountAdminService.findAllAccounts()).thenReturn(accounts);

    try {
      mockMvc.perform(get("/api/admin/accounts/").contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(1)))
          .andExpect(jsonPath("$[0].username", is(account1.getUsername())));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
