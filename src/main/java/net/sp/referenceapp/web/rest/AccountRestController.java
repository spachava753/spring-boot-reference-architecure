package net.sp.referenceapp.web.rest;

import java.util.Collection;
import javax.validation.Valid;
import io.swagger.annotations.Api;
import net.sp.referenceapp.dto.AccountDTO;
import net.sp.referenceapp.service.AccountService;
import net.sp.referenceapp.web.rest.error.AccountRetrievalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.zalando.problem.Status;

@RestController
@RequestMapping("/api/accounts")
@Api("/api/accounts")
public class AccountRestController {

  @Autowired
  private AccountService accountService;

  @GetMapping(value = "/lastname/{lastName}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<Collection<AccountDTO>> getAccountsList(
      @PathVariable("lastName") String accountLastName) {
    if (accountLastName == null) {
      accountLastName = "";
    }
    Collection<AccountDTO> accounts = this.accountService.findAccountByLastName(accountLastName);
    if (accounts.isEmpty()) {
      return new ResponseEntity<Collection<AccountDTO>>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<Collection<AccountDTO>>(accounts, HttpStatus.OK);
  }

  @GetMapping(value = "/username/{userName}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<AccountDTO> getAccountsListByUserName(
      @PathVariable("userName") String accountUserName) {
    if (accountUserName == null) {
      accountUserName = "";
    }
    AccountDTO account = this.accountService.findAccountByUsername(accountUserName);
    return new ResponseEntity<AccountDTO>(account, HttpStatus.OK);
  }


  @GetMapping(value = "/{accountId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<AccountDTO> getAccount(@PathVariable("accountId") String accountId) {
    AccountDTO account = null;
    account = this.accountService.findAccountById(accountId);
    if (account == null) {
      throw new AccountRetrievalException("Could not retreive the Account object",
          Status.NOT_FOUND);
    }
    return new ResponseEntity<AccountDTO>(account, HttpStatus.OK);
  }

  @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<AccountDTO> addAccount(@RequestBody @Valid AccountDTO account,
      BindingResult bindingResult, UriComponentsBuilder ucBuilder) {
    BindingErrorsResponse errors = new BindingErrorsResponse();
    HttpHeaders headers = new HttpHeaders();
    if (bindingResult.hasErrors() || (account == null)) {
      errors.addAllErrors(bindingResult);
      headers.add("errors", errors.toJSON());
      return new ResponseEntity<AccountDTO>(headers, HttpStatus.BAD_REQUEST);
    }
    AccountDTO accountDTO = this.accountService.saveAccount(account);
    headers
        .setLocation(ucBuilder.path("/api/accounts/{id}").buildAndExpand(account.getId()).toUri());
    return new ResponseEntity<AccountDTO>(accountDTO, headers, HttpStatus.CREATED);
  }

  @PutMapping(value = "/{accountId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<AccountDTO> updateAccount(@PathVariable("accountId") String accountId,
      @RequestBody @Valid AccountDTO account, BindingResult bindingResult,
      UriComponentsBuilder ucBuilder) {
    BindingErrorsResponse errors = new BindingErrorsResponse();
    HttpHeaders headers = new HttpHeaders();
    if (bindingResult.hasErrors() || (account == null)) {
      errors.addAllErrors(bindingResult);
      headers.add("errors", errors.toJSON());
      return new ResponseEntity<AccountDTO>(headers, HttpStatus.BAD_REQUEST);
    }
    AccountDTO currentAccount = this.accountService.findAccountById(accountId);
    if (currentAccount == null) {
      return new ResponseEntity<AccountDTO>(HttpStatus.NOT_FOUND);
    }
    AccountDTO accountDTO = this.accountService.updateAccount(account);
    return new ResponseEntity<AccountDTO>(accountDTO, HttpStatus.NO_CONTENT);
  }

  @DeleteMapping(value = "/{accountId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  // @Transactional
  public ResponseEntity<Void> deleteAccount(@PathVariable("accountId") String accountId) {
    AccountDTO Account = this.accountService.findAccountById(accountId);
    if (Account == null) {
      return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }
    this.accountService.deleteAccount(Account);
    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
  }


  @GetMapping(value = "/message/{accountId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<AccountDTO> sendAccountUpdate(@PathVariable("accountId") String accountId) {
    AccountDTO account = null;
    account = this.accountService.sendAccountUpdate(accountId);
    if (account == null) {
      throw new AccountRetrievalException("Could not retreive the Account object",
          Status.NOT_FOUND);
    }
    return new ResponseEntity<AccountDTO>(account, HttpStatus.OK);
  }


  @GetMapping(value = "/exceptions", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  // @Transactional
  public ResponseEntity<Void> AccountExceptionThrower() {
    throw new AccountRetrievalException("Just throwing an exception using Problem",
        Status.BAD_REQUEST);
  }

}
