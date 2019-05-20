package it.my.test;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("serviceCall")
public class RouterController {

  @Autowired ProducerTemplate producer;

  @GetMapping("service")
  public String callService(@RequestParam("type") String type) {

    String response =
        producer.requestBodyAndHeader(
            "direct:service-call", "I am the BODY", "product-type", type, String.class);

    return response;
  }
}
