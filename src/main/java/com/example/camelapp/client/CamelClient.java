package com.example.camelapp.client;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;

public class CamelClient {
    public static void main(String arg[]) {
        while(true) {
            callAPI();
        }
    }

    private static void callAPI() {
        CamelContext context = new DefaultCamelContext();
        try {
            context.start();

            ProducerTemplate producerTemplate = context.createProducerTemplate();
            Exchange exchange = producerTemplate.request("http://localhost:8080/hello", new MyProcessor());

            System.out.println("Response from REST endpoint: " + exchange.getMessage().getBody(String.class));
        } finally {
            context.stop();
        }
    }

    static class MyProcessor implements Processor {
        @Override
        public void process(Exchange exchange) throws Exception {
            // Set the HTTP method and request body
            exchange.getIn().setHeader(Exchange.HTTP_METHOD, "GET"); // Set the HTTP method as required
            exchange.getIn().setHeader(Exchange.CONTENT_TYPE, "application/json"); // Set the content type as required
//            exchange.getIn().setBody("{'key': 'value'}"); // Set the request body as required
        }
    }
}
