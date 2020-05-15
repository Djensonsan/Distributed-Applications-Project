package dev;

import dev.beans.CustomerTestBean;
import dev.entities.OrderEntity;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "testDevServlet",urlPatterns = "/dev")
public class TestDevServlet extends HttpServlet {
    @EJB
    private CustomerTestBean customerBean;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.customerBean.addCustomer();
        this.customerBean.addOrder();
        this.customerBean.addOrdertoCustomer();

        List<OrderEntity> orders = this.customerBean.getCustomerOrders(1L);
        PrintWriter out = response.getWriter();

        response.setContentType("text/html");
        out.println("All orders of customer: 1");
        out.println("<ul>");
        for(OrderEntity o : orders) {
            out.println("<li> Order status:"+o.getStatus()+"</li>");
        }
        out.println("</ul>");
    }
}
