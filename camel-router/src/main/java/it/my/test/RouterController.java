package it.my.test;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.my.test.models.SpecialBody;

@RestController
@RequestMapping("serviceCall")
public class RouterController {

  @Autowired ProducerTemplate producer;

  @PostMapping("service")
  public String callService(@RequestParam("type") String type, @RequestBody String body) {

    String response =
        producer.requestBodyAndHeader("direct:service-call", body, "product-type", type, String.class);
    return response;
  }
  
  @GetMapping("complex")
  public String useComplexBody() {
	    String response =
	            producer.requestBodyAndHeader("direct:cbody-call", new SpecialBody("My BODY"), "product-type", "B", String.class);
	        return response;
  }
}
