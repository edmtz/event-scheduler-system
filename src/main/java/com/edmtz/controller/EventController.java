package com.edmtz.controller;



import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

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

public class EventController implements Controller {

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Configurar el tipo de contenido de la respuesta
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        // Escribir directamente en el cuerpo de la respuesta
        PrintWriter writer = response.getWriter();
        writer.write("Hola Mundo desde Spring 1.x");
        writer.flush();

        // Retornar null porque no se usa una vista
        return null;
    }

}