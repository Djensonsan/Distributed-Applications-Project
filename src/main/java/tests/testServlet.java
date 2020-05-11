package tests;

import tests.testBean;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "myServlet", urlPatterns = "/")
public class testServlet extends HttpServlet {

    @EJB
    private tests.testBean testBean;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        out.println("Bean is saying: "+this.testBean.sayHello());
        out.println("<ul>");
        for(TestEntity t: this.testBean.findByName("Jens")) {
            out.println("<li>"+t.getName()+"</li>");
        }
        out.println("</ul>");
    }
}
