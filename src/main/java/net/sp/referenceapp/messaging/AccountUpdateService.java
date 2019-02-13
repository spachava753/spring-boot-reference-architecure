package net.sp.referenceapp.messaging;

import net.sp.referenceapp.config.AccountProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import net.sp.referenceapp.dto.AccountDTO;

@Component
public class AccountUpdateService {

	Logger logger = LoggerFactory.getLogger(AccountUpdateService.class);

    private final AccountProcessor accountProcessor;
    
    public AccountUpdateService(AccountProcessor accountProcessor) {
    	this.accountProcessor = accountProcessor;
    }
    
	public AccountDTO sendAccountUpdate(AccountDTO account) {
		logger.info("******************  sendAccountUpdate  ******************");
		logger.info("Received value "+ account);
		MessageChannel messageChannel = accountProcessor.accountOutput();
		messageChannel.send(message(account));
		return account;
	}
	
    private static final <T> Message<T> message(T val) {
        return MessageBuilder.withPayload(val)
            .build();
    }

    @Bean
    public MessageConverter getAccountDTOMessageConverter() {
        return new AccountDTOMessageConverter();
    }
}