package net.sp.referenceapp.web.rest;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
@RequestMapping("/")
public class DocsController {

  @GetMapping
  public void redirectToSwagger(HttpServletResponse response) throws IOException {
    response.sendRedirect("/refapp/swagger-ui.html");
  }

}

