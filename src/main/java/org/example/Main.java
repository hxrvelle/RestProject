package org.example;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;
import org.example.controller.DisciplineController;
import org.example.controller.PhoneController;
import org.example.controller.StudentController;

public class Main {
    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        tomcat.getConnector().setPort(8081);
        Context tomcatContext = tomcat.addContext("", null);

        Wrapper studentControllerWrapper = Tomcat.addServlet(tomcatContext, "StudentController", new StudentController());
        studentControllerWrapper.addMapping("/students/*");
        Wrapper phoneControllerWrapper = Tomcat.addServlet(tomcatContext, "PhoneController", new PhoneController());
        phoneControllerWrapper.addMapping("/phones/*");
        Wrapper disciplineControllerWrapper = Tomcat.addServlet(tomcatContext, "DisciplineController", new DisciplineController());
        disciplineControllerWrapper.addMapping("/disciplines/*");

        tomcat.start();
    }
}