package net.sp.referenceapp.web.rest;

import net.sp.referenceapp.dto.AccountDTO;
import net.sp.referenceapp.service.AccountAdminService;
import net.sp.referenceapp.web.rest.error.AccountRetrievalException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zalando.problem.Status;
import java.util.Collection;

@RestController
@RequestMapping("/api/admin/accounts")
@Api("/api/admin/accounts")
public class AccountAdminRestController {

  @Autowired
  private AccountAdminService accountAdminService;

  @GetMapping
  @ApiOperation(value = "Get all Accounts", notes = "Returns a collection of Account objects",
      response = AccountDTO.class)
  @ApiResponses({@ApiResponse(code = 200, message = "Success", response = AccountDTO.class)})
  public ResponseEntity<Collection<AccountDTO>> getAllAccounts() {
    Collection<AccountDTO> accounts = accountAdminService.findAllAccounts();
    if (accounts.isEmpty()) {
      throw new AccountRetrievalException("There are no account objects found", Status.NOT_FOUND);
    }
    return new ResponseEntity<>(accounts, HttpStatus.OK);
  }


}
