package net.sp.referenceapp.web.rest;

import net.sp.referenceapp.domain.Health;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class HealthRestController {

  private static final String up = "UP";
  private final AtomicLong counter = new AtomicLong();

  @GetMapping("/health")
  public Health health() {
    return new Health(counter.incrementAndGet(), up);
  }

}
