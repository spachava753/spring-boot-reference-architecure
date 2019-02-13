package net.sp.referenceapp.service.impl;


import net.sp.referenceapp.domain.Account;
import net.sp.referenceapp.dto.AccountDTO;
import net.sp.referenceapp.repository.AccountRepository;
import net.sp.referenceapp.service.AccountAdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class AccountAdminServiceImpl implements AccountAdminService {

  Logger logger = LoggerFactory.getLogger(AccountAdminServiceImpl.class);

  @Autowired
  private AccountRepository accountRepository;

  public AccountAdminServiceImpl() {}

  @CachePut(value = "tasks", condition = "#noCache", key = "#noCache")
  @Cacheable(value = "tasks", condition = "!#noCache", key = "!#noCache")
  @Override
  @Transactional(readOnly = true)
  public List<AccountDTO> findAllAccounts() throws DataAccessException {
    Collection<Account> accounts = accountRepository.findAll();
    List<AccountDTO> accountDTOs = new ArrayList<>();
    for (Account account : accounts) {
      accountDTOs.add(new AccountDTO(account));
    }
    return accountDTOs;
  }

}
