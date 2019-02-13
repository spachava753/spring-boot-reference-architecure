package net.sp.referenceapp.event;

import java.util.Map;
import net.sp.referenceapp.dto.AccountDTO;


public class AccountUpdateEvent extends AccountEvent {

  private AccountDTO accountDTO;
  private static String EVENT_NAME = "AccountUpdateEvent";

  public AccountUpdateEvent(AccountDTO accountDTO) {
    this(accountDTO, null);
  }

  public AccountUpdateEvent(AccountDTO accountDTO, Map headerMap) {
    super(EVENT_NAME);
    this.accountDTO = accountDTO;
    if (headerMap != null)
      this.setHeaderMap(headerMap);
    this.addHeader("eventName", getEventName());
    this.addHeader("ID", accountDTO.getId());
    this.addHeader("firstName", accountDTO.getFirstName());
    this.addHeader("lastName", accountDTO.getLastName());
    this.addHeader("userName", accountDTO.getUsername());
    this.addHeader("password", accountDTO.getPassword());

  }
}
