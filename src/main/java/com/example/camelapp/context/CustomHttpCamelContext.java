package com.example.camelapp.context;

import org.apache.camel.Endpoint;
import org.apache.camel.component.http.HttpComponent;
import org.apache.camel.impl.DefaultCamelContext;

public class CustomHttpCamelContext extends DefaultCamelContext {
//    Component component = this.getComponent(scheme);
    private final String scheme = "http";

    protected Endpoint doGetEndpoint(String uri, boolean normalized, boolean prototype) {
        HttpComponent component = (HttpComponent) this.getComponent(scheme);
        component.setConnectionTimeToLive(1000);
        return super.doGetEndpoint(uri, normalized, prototype);
    }
}
