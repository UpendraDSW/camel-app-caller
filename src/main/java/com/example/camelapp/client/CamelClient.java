package com.example.camelapp.client;

import com.example.camelapp.context.CustomHttpCamelContext;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;

//import static org.mockito.Mockito.when;

public class CamelClient {
    public static void main(String arg[]) {
        while(true) {
            callAPI();
        }
    }

    private static void callAPI() {
        CamelContext context = new CustomHttpCamelContext();
        try {
            context.start();
            context.getComponentNames();
            ProducerTemplate producerTemplate = context.createProducerTemplate();
            Exchange exchange = producerTemplate.request("http://localhost:8080/hello", new MyProcessor());
//            context.start();
            System.out.println("Response from REST endpoint: " + exchange.getMessage().getBody(String.class));
        } finally {
            context.stop();
        }
    }

    static class MyProcessor implements Processor {
        @Override
        public void process(Exchange exchange) throws Exception {
            exchange.getIn().setHeader(Exchange.HTTP_METHOD, "GET");
            exchange.getIn().setHeader(Exchange.CONTENT_TYPE, "application/json");
        }
    }
}
