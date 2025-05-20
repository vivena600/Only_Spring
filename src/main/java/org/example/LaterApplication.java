package org.example;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class LaterApplication {
    private static final int PORT = 8080;

    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        tomcat.getConnector().setPort(PORT);

        //пустой путь
        Context tomcatContext = tomcat.addContext("", null);

        AnnotationConfigWebApplicationContext webAppContext = new AnnotationConfigWebApplicationContext();
        webAppContext.setServletContext(tomcatContext.getServletContext());
        webAppContext.scan("org.example");
        webAppContext.refresh();

        //добавляем диспетчер запросов
        DispatcherServlet dispatcherServlet = new DispatcherServlet(webAppContext);
        Wrapper wrapper = Tomcat.addServlet(tomcatContext, "dispatcher", dispatcherServlet);
        wrapper.addMapping("/");
        wrapper.setLoadOnStartup(1);
        tomcat.start();
    }
}
