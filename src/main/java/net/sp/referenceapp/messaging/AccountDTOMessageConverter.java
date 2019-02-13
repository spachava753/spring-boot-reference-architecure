package net.sp.referenceapp.messaging;

import org.springframework.messaging.Message;
import org.springframework.messaging.converter.AbstractMessageConverter;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeType;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.messaging.MessageHeaders;
import java.io.IOException;
import net.sp.referenceapp.dto.AccountDTO;


@Component
public class AccountDTOMessageConverter extends AbstractMessageConverter {

  private Logger logger = LoggerFactory.getLogger(AccountDTOMessageConverter.class);

  @Autowired
  private ObjectMapper objectMapper;


  public AccountDTOMessageConverter() {
    super(MimeType.valueOf("application/json"));
  }

  @Override
  protected boolean supports(Class<?> clazz) {
    return clazz.equals(AccountDTO.class);
  }

  @Override
  protected Object convertFromInternal(Message<?> message, Class<?> targetClass,
      Object conversionHint) {
    logger.info("******************convertFromInternal******************");
    try {
      return objectMapper.readValue(message.getPayload().toString(),
          new TypeReference<AccountDTO>() {});
    } catch (IOException e) {
      return null;
    }
  }

  @Override
  protected Object convertToInternal(Object payload, @Nullable MessageHeaders headers,
      @Nullable Object conversionHint) {
    logger.info("******************convertToInternal******************");
    MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
    Message<?> message = converter.toMessage(payload, null);
    return message;
  }
}
