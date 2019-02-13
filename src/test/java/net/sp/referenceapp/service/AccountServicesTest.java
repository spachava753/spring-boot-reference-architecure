package net.sp.referenceapp.service;

import net.sp.referenceapp.RefAppApplication;
import net.sp.referenceapp.domain.Account;
import net.sp.referenceapp.dto.AccountDTO;
import net.sp.referenceapp.repository.AccountRepository;
import net.sp.referenceapp.service.impl.AccountServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RefAppApplication.class)
//@Profile("test")
public class AccountServicesTest {

    Logger logger = LoggerFactory.getLogger(AccountServicesTest.class);

    @TestConfiguration
    static class AccountServiceImplTestContextConfiguration {

        @Bean
        public AccountService accountService() {
            return new AccountServiceImpl();
        }
    }

    @Autowired
    AccountService accountService;

    @MockBean
    AccountRepository accountRepository;

    @Test
    public void whenFindAccountByUsername_thenAccount(){
        Account account1 = new Account();
        account1.setFirstName("John");
        account1.setLastName("Doe");
        account1.setPassword("password");
        account1.setContact("1234567890");


        Random random = new Random();
        String ssn = String.format("%09d", random.nextInt(1000000000));
        account1.setSsn(ssn);
        account1.setUsername("jdoe" + random);

        Mockito.when(accountRepository.findByUsername(account1.getUsername())).thenReturn(Optional.of(account1));

        logger.debug("Testing findByUsername service method");
        AccountDTO account = accountService.findAccountByUsername(account1.getUsername());
        assertThat(account.getUsername()).isEqualTo(account1.getUsername());
    }
}
