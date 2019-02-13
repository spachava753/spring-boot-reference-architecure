package net.sp.referenceapp.repository;

import net.sp.referenceapp.domain.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@DirtiesContext
// @Profile("test")
public class AccountRepositoryTest {

  @Autowired
  AccountRepository accountRepository;

  @Autowired
  TestEntityManager testEntityManager;

  @Test
  public void whenFindByFirstName_returnAccountList() {
    Account account1 = new Account();
    account1.setFirstName("John");
    account1.setLastName("Doe");
    account1.setPassword("password");
    account1.setContact("1234567890");
    Random random = new Random();
    String ssn = String.format("%09d", random.nextInt(1000000000));
    account1.setSsn(ssn);
    account1.setUsername("jdoe" + random);


    testEntityManager.persist(account1);

    try (Stream<Account> accounts = accountRepository.findByFirstName(account1.getFirstName())) {
      assertThat(accounts.count()).isGreaterThan(0);
    }
  }

  @Test
  public void whenFindByLastName_returnAccountList() {
    Account account1 = new Account();
    account1.setFirstName("John");
    account1.setLastName("Doe");
    account1.setPassword("password");
    account1.setContact("1234567890");
    Random random = new Random();
    String ssn = String.format("%09d", random.nextInt(1000000000));
    account1.setSsn(ssn);
    account1.setUsername("jdoe" + random);


    testEntityManager.persist(account1);
    try (Stream<Account> accounts = accountRepository.findByFirstName(account1.getFirstName())) {
      assertThat(accounts.count()).isGreaterThan(0);
    }
  }

  @Test
  public void whenFindByContact_returnAccountList() {
    Account account1 = new Account();
    account1.setFirstName("John");
    account1.setLastName("Doe");
    account1.setPassword("password");
    account1.setContact("1234567890");
    Random random = new Random();
    String ssn = String.format("%09d", random.nextInt(1000000000));
    account1.setSsn(ssn);
    account1.setUsername("jdoe" + random);


    testEntityManager.persist(account1);

    try (Stream<Account> accounts = accountRepository.findByLastName(account1.getLastName())) {
      assertThat(accounts.count()).isGreaterThan(0);
    }
  }

  @Test
  public void whenFindByUsername_returnAccount() {
    Account account1 = new Account();
    account1.setFirstName("John");
    account1.setLastName("Doe");
    account1.setPassword("password");
    account1.setContact("1234567890");
    Random random = new Random();
    String ssn = String.format("%09d", random.nextInt(1000000000));
    account1.setSsn(ssn);
    account1.setUsername("jdoe" + random);


    testEntityManager.persist(account1);

    Optional<Account> account = accountRepository.findByUsername(account1.getUsername());

    assertThat(account.get().getUsername()).isEqualTo(account1.getUsername());
  }

  @Test
  public void whenFindBySsn_returnAccount() {
    Account account1 = new Account();
    account1.setFirstName("John");
    account1.setLastName("Doe");
    account1.setPassword("password");
    account1.setContact("1234567890");
    Random random = new Random();
    String ssn = String.format("%09d", random.nextInt(1000000000));
    account1.setSsn(ssn);
    account1.setUsername("jdoe" + random);

    testEntityManager.persist(account1);

    Optional<Account> account = accountRepository.findBySsn(account1.getSsn());

    assertThat(account.get().getSsn()).isEqualTo(account1.getSsn());
  }
}
