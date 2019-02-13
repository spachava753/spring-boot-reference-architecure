package net.sp.referenceapp.messaging;


import net.sp.referenceapp.config.AccountProcessor;
import net.sp.referenceapp.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;
import net.sp.referenceapp.dto.AccountDTO;

@Component
public class AccountMessageListenerService {

  Logger logger = LoggerFactory.getLogger(AccountUpdateService.class);

  private final AccountProcessor accountProcessor;
  private final AccountService accountService;

  public AccountMessageListenerService(AccountService accountService,
      AccountProcessor accountProcessor) {
    this.accountProcessor = accountProcessor;
    this.accountService = accountService;
  }

  @StreamListener(target = AccountProcessor.ACCOUNTS_INPUT)
  public void processAccountCreate(AccountDTO account) {
    logger.info("****************** processAccountChange ******************");
    account = accountService.saveAccount(account);
    logger.info("updated account:" + account);
  }

}
