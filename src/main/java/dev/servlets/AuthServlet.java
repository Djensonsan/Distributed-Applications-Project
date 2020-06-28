package dev.servlets;

import com.google.gson.JsonObject;
import dev.beans.CustomerBean;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AuthServlet", urlPatterns = "/auth")
public class AuthServlet extends HttpServlet {
    @EJB
    private CustomerBean customerBean;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Authentication
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        System.out.println(email + " " + password);
        Boolean userAuthenticated = customerBean.authenticateCustomer(email, password);

        //create Json Object
        JsonObject json = new JsonObject();

        if (userAuthenticated) {
            json.addProperty("succes", true);
        } else {
            json.addProperty("succes", false);
        }

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(json.toString());
        out.flush();
    }
}
