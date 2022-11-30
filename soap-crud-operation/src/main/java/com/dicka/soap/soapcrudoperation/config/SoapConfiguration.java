package com.dicka.soap.soapcrudoperation.config;


import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.ws.wsdl.wsdl11.SimpleWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class SoapConfiguration extends WsConfigurerAdapter {

    @SuppressWarnings({
            "rawtypes", "unchecked"
    })
    @Bean
    public ServletRegistrationBean servletRegistrationBean(ApplicationContext applicationContext){
        MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
        messageDispatcherServlet.setApplicationContext(applicationContext);
        messageDispatcherServlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(messageDispatcherServlet, "/soap/*");
    }

    /** using xsd file **/
    @Bean(name = "employee")
    public DefaultWsdl11Definition defaultWsdlForEmployee(XsdSchema schema){
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("soapcrudServiceHttp");
        wsdl11Definition.setLocationUri("soapEmployeeService");
        wsdl11Definition.setTargetNamespace("interfaces.soap.springboot.dicka.com");
        wsdl11Definition.setSchema(employeeSchema());
        return wsdl11Definition;
    }

    /** using wsdl **/
    @Bean(name = "virtualAccount")
    public SimpleWsdl11Definition defaultWsdlForVirtualAccount(){
        SimpleWsdl11Definition wsdl11Definition = new SimpleWsdl11Definition();
        wsdl11Definition.setWsdl(new ClassPathResource("wsdl/virtualAccount.wsdl"));
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema employeeSchema(){
        return new SimpleXsdSchema(new ClassPathResource("exmployee.xsd"));
    }
}
