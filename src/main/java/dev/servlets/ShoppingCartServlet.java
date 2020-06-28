package dev.servlets;

import dev.beans.CustomerBean;
import dev.interfaces.Cart;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "shoppingCartServlet", urlPatterns = "/ShoppingCartServlet")
public class ShoppingCartServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final String CART_SESSION_KEY = "shoppingCart";
    @EJB
    private CustomerBean customerBean;

    public ShoppingCartServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Hello from servlet");
        Cart cartBean = (Cart) request.getSession().getAttribute(CART_SESSION_KEY);

//        // Authentication
//        String email = request.getParameter("email");
//        String password = request.getParameter("password");
//
//        System.out.println(email + " " + password);
//        Boolean userAuthenticated = customerBean.authenticateCustomer(email, password);
//
//        if (!userAuthenticated) {
//            System.out.println("User doesn't exist!");
//        } else {
//            System.out.println("User exists, please continue!");
//        }

        if (cartBean == null) {
            // EJB is not yet in the HTTP session
            // This means that the client just sent his first request
            // We obtain a CartBean instance and add it to the session object.
            try {
                InitialContext ic = new InitialContext();
                cartBean = (Cart) ic.lookup("java:module/CartBean");

                request.getSession().setAttribute(CART_SESSION_KEY, cartBean);

                System.out.println("shoppingCart created");

            } catch (NamingException e) {
                throw new ServletException(e);
            }
        } else {
            System.out.println("shoppingCart found!");
        }

        String productName = request.getParameter("product");
        if (productName != null && productName.length() > 0) {

//            Product product = new Product();
//            product.setType(productName);
//            cartBean.addProductToCart(product);

            System.out.println("product " + productName + " added");
        }

        String checkout = request.getParameter("checkout");
        if (checkout != null && checkout.equalsIgnoreCase("yes")) {
            // Request instructs to complete the purchase
            cartBean.checkOut();
            System.out.println("Shopping cart checked out ");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
