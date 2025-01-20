package com.edmtz.controller;



import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import java.io.IOException;
import java.io.PrintWriter;

//@RestController
//@RequestMapping("/services")
//public class EventController {
//
//    @GetMapping
//    public String getEvents() {
//        return "HOLA MUNDO";
//    }
//
//}

//public class EventController implements Controller {
//
//    @Override
//    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        // Configurar el tipo de contenido de la respuesta
//        response.setContentType("text/plain");
//        response.setCharacterEncoding("UTF-8");
//
//        // Escribir directamente en el cuerpo de la respuesta
//        PrintWriter writer = response.getWriter();
//        writer.write("Hola Mundo desde Spring 1.x");
//        writer.flush();
//
//        // Retornar null porque no se usa una vista
//        return null;
//    }
//
//}

public class EventController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String message = "Hello from Servlet!";
        response.getWriter().write("<h1>" + message + "</h1>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String param = request.getParameter("param");
        response.getWriter().write("<h1>Received POST param" + param + "</h1>");
    }

}