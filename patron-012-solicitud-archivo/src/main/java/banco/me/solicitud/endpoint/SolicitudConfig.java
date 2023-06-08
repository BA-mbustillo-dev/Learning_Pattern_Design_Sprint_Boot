package banco.me.solicitud.endpoint;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class SolicitudConfig {

    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext){
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/ws/v1/services/*");
    }

    @Bean(name = "WS_TRA_Solicitud_Archivo")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema solicitudArchivoShema){
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("WS_TRA_Solicitud_Archivo");
        wsdl11Definition.setLocationUri("/ws/v1/services/");
        wsdl11Definition.setTargetNamespace("http://banco.me/");
        wsdl11Definition.setServiceName("WS_TRA_Solicitud_Archivo");
        wsdl11Definition.setSchema(solicitudArchivoShema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema solicitudArchivoShema(){
        return  new SimpleXsdSchema(new ClassPathResource("/xsd/ms-012-trf-solicitud-archivo.xsd"));
    }
}
