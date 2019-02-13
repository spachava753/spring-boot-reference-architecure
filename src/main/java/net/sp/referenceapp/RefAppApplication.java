package net.sp.referenceapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class RefAppApplication {

  private static final Logger log = LoggerFactory.getLogger(RefAppApplication.class);

  public static void main(String[] args) {
    SpringApplication app = new SpringApplication(RefAppApplication.class);
    Environment env = app.run(args).getEnvironment();

    log.info("\n----------------------------------------------------------\n\t"
        + "Application '{}' is running! at http://localhost:{}/refapp/ --------------------------------------",
        env.getProperty("spring.application.name"), env.getProperty("server.port"));

  }
}
