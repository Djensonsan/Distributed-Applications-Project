package dev;

import dev.beans.customerTestBean;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "testDevServlet",urlPatterns = "/dev")
public class testDevServlet extends HttpServlet {
    @EJB
    private customerTestBean customerBean;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.customerBean.addCustomer();
        this.customerBean.addOrder();
    }
}
