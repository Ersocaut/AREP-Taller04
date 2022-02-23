package edu.escuelaing.arep.controller;

import edu.escuelaing.arep.annotation.Component;
import edu.escuelaing.arep.annotation.RequestMapping;

@Component
public class MainController {
    @RequestMapping("main")
    public static String index(){
            return "HTTP/1.1 200 OK\r\n"
                    + "Content-Type: text/html\r\n"
                    + "\r\n"
                    + "<!DOCTYPE html>"
                    + "<html>"
                    + "<head>"
                    + "<meta charset=\"UTF-8\">"
                    + "<title>Hello</title>\n"
                    + "</head>"
                    + "<body>"
                    + "<h1>Hello</h1>"
                    + "</div>"
                    + "<p>"
                    + "Greetings from Spring Boot!"
                    + "</p>"
                    + "</body>"
                    + "</html>";
    }
}
