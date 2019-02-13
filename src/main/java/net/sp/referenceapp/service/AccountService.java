package net.sp.referenceapp.service;

import net.sp.referenceapp.domain.Account;
import net.sp.referenceapp.dto.AccountDTO;
import net.sp.referenceapp.event.AccountUpdateEvent;
import org.springframework.dao.DataAccessException;
import java.util.Collection;
import java.util.List;

public interface AccountService {

  public AccountDTO findAccountById(String id) throws DataAccessException;

  public AccountDTO sendAccountUpdate(String id) throws DataAccessException;

  public Collection<AccountDTO> findAllAccounts() throws DataAccessException;

  public AccountDTO saveAccount(AccountDTO account) throws DataAccessException;

  public AccountDTO updateAccount(AccountDTO account) throws DataAccessException;

  public void deleteAccount(AccountDTO account) throws DataAccessException;

  public List<AccountDTO> findAccountByLastName(String lastName) throws DataAccessException;

  public List<AccountDTO> findAccountByFirstName(String firstName) throws DataAccessException;

  public AccountDTO findAccountByUsername(String username) throws DataAccessException;

  public AccountDTO findAccountBySsn(String ssn) throws DataAccessException;

  public Account updateAccount(AccountUpdateEvent accountUpdateEvent) throws DataAccessException;
}
