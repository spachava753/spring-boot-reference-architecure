package net.sp.referenceapp.web.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HealthRestController.class)
@RunWith(SpringRunner.class)
public class HealthRestControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void whenCallHealth_returnStatus() {
    try {
      mockMvc.perform(get("/health").contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
