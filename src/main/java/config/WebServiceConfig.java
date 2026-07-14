package com.cio.olympic.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(
            ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, "/ws/*");
    }

    @Bean(name = "athletes")
    public DefaultWsdl11Definition athleteWsdl11Definition(@org.springframework.beans.factory.annotation.Qualifier("athleteSchema") XsdSchema athleteSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("AthletePort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://cio.com/olympic/athlete");
        wsdl11Definition.setSchema(athleteSchema);
        return wsdl11Definition;
    }

    @Bean(name = "resultats")
    public DefaultWsdl11Definition resultatWsdl11Definition(@org.springframework.beans.factory.annotation.Qualifier("resultatSchema") XsdSchema resultatSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("ResultatPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://cio.com/olympic/resultat");
        wsdl11Definition.setSchema(resultatSchema);
        return wsdl11Definition;
    }

    @Bean(name = "athleteSchema")
    public XsdSchema athleteSchema() {
        return new SimpleXsdSchema(new ClassPathResource("xsd/athlete.xsd"));
    }

    @Bean(name = "resultatSchema")
    public XsdSchema resultatSchema() {
        return new SimpleXsdSchema(new ClassPathResource("xsd/resultat.xsd"));
    }
}