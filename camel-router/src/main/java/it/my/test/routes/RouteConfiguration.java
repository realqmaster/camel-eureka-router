package it.my.test.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

import it.my.test.models.SpecialBody;

@Component
public class RouteConfiguration extends RouteBuilder {

	@Override
  public void configure() throws Exception {

    from("direct:service-call")
        .choice()
        .when(e -> e.getIn().getHeader("product-type").equals("A"))
        .setHeader(Exchange.HTTP_METHOD, constant("POST"))
        .serviceCall("product-a-service/service")
        .endChoice()
        .when(e -> e.getIn().getHeader("product-type").equals("B"))
        .setHeader(Exchange.HTTP_METHOD, constant("POST"))
        .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
        .setHeader("internal-auth-token", constant("abcd"))
        .process(
            new Processor() {

              @Override
              public void process(Exchange exchange) throws Exception {
                Message in = exchange.getIn();
                String body = in.getBody(String.class);
                SpecialBody special = new SpecialBody(body);
                in.setBody(special);
              }
            })
    	.marshal().json(JsonLibrary.Jackson)
        .serviceCall("product-b-service/service")
        .endChoice()
        .otherwise()
        .setBody()
        .constant("Unsupported Type!")
        .end();
    
    from("direct:cbody-call")
    	.setHeader(Exchange.HTTP_METHOD, constant("POST"))
    	.setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
    	.setHeader("internal-auth-token", constant("abcd"))
    	.marshal().json(JsonLibrary.Jackson)
    	.serviceCall("product-b-service/service");
;
    
  }
}
