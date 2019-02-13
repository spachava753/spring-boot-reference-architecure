package net.sp.referenceapp.config;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface AccountProcessor {
  String ACCOUNTS_INPUT = "accounts-in";
  String ACCOUNTS_OUTPUT = "accounts-out";

  @Input(AccountProcessor.ACCOUNTS_INPUT)
  SubscribableChannel accountInput();

  @Output(AccountProcessor.ACCOUNTS_OUTPUT)
  MessageChannel accountOutput();

}