package it.my.test;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("service")
public class ServiceController {

  @PostMapping("")
  public String callService(@RequestBody SpecialBody body, @RequestHeader("internal-auth-token") String auth) {
    return "I am service B, and I received [" + body + "]";
  }
}
