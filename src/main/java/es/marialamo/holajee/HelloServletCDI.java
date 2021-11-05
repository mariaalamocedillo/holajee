package es.marialamo.holajee;


import jakarta.inject.Inject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(HelloServletCDI.URL)
public class HelloServletCDI extends HttpServlet {

    public static final String URL = "helloServletCDI";

    @Inject
    private Message message;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        PrintWriter printWriter = response.getWriter();
        response.setContentType("text/plain;charset=UTF-8");
        printWriter.print(message.get());
    }

}