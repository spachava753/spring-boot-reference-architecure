package net.sp.referenceapp.service.impl;

import net.sp.referenceapp.domain.Account;
import net.sp.referenceapp.dto.AccountDTO;
import net.sp.referenceapp.event.AccountUpdateEvent;
import net.sp.referenceapp.messaging.AccountUpdateService;
import net.sp.referenceapp.repository.AccountRepository;
import net.sp.referenceapp.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class AccountServiceImpl implements AccountService {

  Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

  @Autowired
  private AccountRepository accountRepository;

  @Autowired
  private AccountUpdateService accountUpdateService;

  public AccountServiceImpl() {}


  @CachePut(value = "tasks", condition = "#noCache", key = "#noCache")
  @Cacheable(value = "tasks", condition = "!#noCache", key = "!#noCache")
  @Override
  @Transactional(readOnly = true)
  public Collection<AccountDTO> findAllAccounts() throws DataAccessException {
    Collection<Account> accounts = accountRepository.findAll();
    List<AccountDTO> accountDTOs = new ArrayList<>();
    for (Account account : accounts) {
      accountDTOs.add(new AccountDTO(account));
    }
    return accountDTOs;
  }


  @Override
  @Transactional
  public void deleteAccount(AccountDTO account) throws DataAccessException {

    Optional<Account> optionalAccount = null;
    try {
      optionalAccount = accountRepository.findById(account.getId());
    } catch (ObjectRetrievalFailureException | EmptyResultDataAccessException e) {

    }
    accountRepository.delete(optionalAccount.get());

  }

  @Override
  @Transactional(readOnly = true)
  public AccountDTO findAccountById(String id) throws DataAccessException {
    Optional<Account> optionalAccount = null;
    try {
      optionalAccount = accountRepository.findById(id);
    } catch (ObjectRetrievalFailureException | EmptyResultDataAccessException e) {
      // just ignore not found exceptions for Jdbc/Jpa realization
      return null;
    }
    return new AccountDTO(optionalAccount.get());
  }

  @Override
  @Transactional
  public AccountDTO saveAccount(AccountDTO accountDTO) throws DataAccessException {
    Account currentAccount = new Account();
    currentAccount.setFirstName(accountDTO.getFirstName());
    currentAccount.setLastName(accountDTO.getLastName());
    currentAccount.setContact(accountDTO.getContact());
    currentAccount.setUsername(accountDTO.getUsername());
    currentAccount.setPassword(accountDTO.getPassword());
    currentAccount.setSsn(accountDTO.getSsn());
    currentAccount = accountRepository.save(currentAccount);
    accountDTO = new AccountDTO(currentAccount);

    // sending updates to all the micro services
    // accountNotificationService.send(accountDTO);
    accountUpdateService.sendAccountUpdate(accountDTO);
    return accountDTO;
  }


  @Override
  @Transactional
  public AccountDTO updateAccount(AccountDTO account) throws DataAccessException {
    Optional<Account> opAccount = this.accountRepository.findById(account.getId());
    Account currentAccount = null;
    AccountDTO accountDTO = null;
    if (opAccount.get() != null) {
      currentAccount = opAccount.get();
      if (account.getFirstName() != null)
        currentAccount.setFirstName(currentAccount.getFirstName());
      if (account.getLastName() != null)
        currentAccount.setLastName(account.getLastName());
      if (account.getContact() != null)
        currentAccount.setContact(account.getContact());
      if (account.getUsername() != null)
        currentAccount.setUsername(account.getUsername());
      if (account.getPassword() != null)
        currentAccount.setPassword(account.getPassword());
      if (account.getSsn() != null)
        currentAccount.setSsn(account.getSsn());

      currentAccount = accountRepository.save(currentAccount);
      accountDTO = new AccountDTO(currentAccount);

      // sending updates to all the micro services
      // accountNotificationService.send(accountDTO);
      accountUpdateService.sendAccountUpdate(accountDTO);
    }
    return accountDTO;


  }


  @Override
  @Transactional
  public Account updateAccount(AccountUpdateEvent accountUpdateEvent) throws DataAccessException {

    Optional<Account> optionalAccount = null;
    Account account = null;
    String id = accountUpdateEvent.getHeaderMap().get("ID").toString();
    try {
      optionalAccount = accountRepository.findById(id);
      account = optionalAccount.get();
      String firstName = accountUpdateEvent.getHeaderMap().get("firstName").toString();
      String lastName = accountUpdateEvent.getHeaderMap().get("lastName").toString();
      String userName = accountUpdateEvent.getHeaderMap().get("userName").toString();
      String password = accountUpdateEvent.getHeaderMap().get("password").toString();
      account.setFirstName(firstName);
      account.setLastName(lastName);
      account.setUsername(userName);
      account.setPassword(password);
      accountRepository.save(account);
    } catch (ObjectRetrievalFailureException e) {
      return null;
    }

    return account;


  }


  @Override
  @Transactional(readOnly = true)
  public List<AccountDTO> findAccountByLastName(String lastName) throws DataAccessException {
    List<AccountDTO> accountDTOs = null;
    try (Stream<Account> accounts = accountRepository.findByLastName(lastName)) {
      accountDTOs = accounts.map(account -> new AccountDTO(account)).collect(Collectors.toList());
    }
    return accountDTOs;
  }

  @Override
  public List<AccountDTO> findAccountByFirstName(String firstName) throws DataAccessException {
    List<AccountDTO> accountDTOs = null;
    try (Stream<Account> accounts = accountRepository.findByFirstName(firstName)) {
      accountDTOs = accounts.map(account -> new AccountDTO(account)).collect(Collectors.toList());
    }
    return accountDTOs;
  }

  @Override
  public AccountDTO findAccountByUsername(String username) throws DataAccessException {
    Optional<Account> account = accountRepository.findByUsername(username);
    AccountDTO accountDTO = null;
    if (account.isPresent()) {
      accountDTO = new AccountDTO(account.get());
      accountUpdateService.sendAccountUpdate(accountDTO);
    }
    return accountDTO;
  }

  @Override
  public AccountDTO findAccountBySsn(String ssn) throws DataAccessException {
    Optional<Account> account = accountRepository.findBySsn(ssn);
    AccountDTO accountDTO = null;
    if (account.isPresent())
      accountDTO = new AccountDTO(account.get());
    return accountDTO;
  }



  @Override
  @Transactional(readOnly = true)
  public AccountDTO sendAccountUpdate(String id) throws DataAccessException {
    Optional<Account> optionalAccount = null;
    AccountDTO accountDTO = null;
    try {
      optionalAccount = accountRepository.findById(id);
      accountDTO = new AccountDTO(optionalAccount.get());

      // sending updates to all the micro services
      // accountNotificationService.send(accountDTO);
      accountUpdateService.sendAccountUpdate(accountDTO);
    } catch (ObjectRetrievalFailureException | EmptyResultDataAccessException e) {
      // just ignore not found exceptions for Jdbc/Jpa realization
      return null;
    }
    return accountDTO;
  }


  @CacheEvict(value = "tasks", allEntries = true)
  public void clearCache() {
    logger.info("Cache cleared");
  }
}
