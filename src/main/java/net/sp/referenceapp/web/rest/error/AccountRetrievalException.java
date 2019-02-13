package net.sp.referenceapp.web.rest.error;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.StatusType;
import javax.annotation.Nullable;

public final class AccountRetrievalException extends AbstractThrowableProblem {
  public AccountRetrievalException(@Nullable String title, @Nullable StatusType status) {
    super(ErrorConstants.ENTITY_NOT_FOUND_TYPE, title, status);
  }
}
