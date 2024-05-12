package proj.eval.app.controller.api;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

  @GetMapping("/test")
  public Object test() {
    return SecurityContextHolder.getContext().getAuthentication();
  }

  @GetMapping("/admin")
  public String testRoleAdmin() {
    return "Test role admin ok!";
  }

  @GetMapping("/user")
  public String testRoleUser() {
    return "Test role user ok!";
  }
}
