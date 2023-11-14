package org.example;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;
import org.example.controller.StudentController;

public class Main {
    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        tomcat.getConnector().setPort(8081);
        Context tomcatContext = tomcat.addContext("", null);
        Wrapper studentControllerWrapper = Tomcat.addServlet(tomcatContext, "StudentController", new StudentController());
        studentControllerWrapper.addMapping("/students/*");
        tomcat.start();
    }
}