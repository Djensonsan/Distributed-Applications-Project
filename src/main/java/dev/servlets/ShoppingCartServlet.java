package dev.servlets;


import dev.beans.CustomerBean;
import dev.beans.ItemBean;
import dev.customExceptions.ItemNotFoundException;
import dev.entities.ItemEntity;
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
import java.io.PrintWriter;


@WebServlet(name = "shoppingCartServlet", urlPatterns = "/ShoppingCartServlet")
public class ShoppingCartServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final String CART_SESSION_KEY = "shoppingCart";
    @EJB
    private CustomerBean customerBean;
    @EJB
    private ItemBean itemBean;

    public ShoppingCartServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Hello from servlet");
        Cart cartBean = (Cart) request.getSession().getAttribute(CART_SESSION_KEY);
        boolean fail = false;

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
                fail = true;
                throw new ServletException(e);
            }
        } else {
            System.out.println("shoppingCart found!");
        }

        //Add Product
        String productId = request.getParameter("product");
        if (productId != null && productId.length() > 0) {
            try {
                ItemEntity itemEntity = itemBean.getItem(Long.parseLong(productId));
                cartBean.addProductToCart(itemEntity);
                System.out.println("product " + itemEntity.getName() + " added");
            } catch (ItemNotFoundException e) {
                System.out.println("ERROR: Failed to Add Product " + e);
                response.sendError(400);
                fail = true;
            }
        }

        //Checkout
        String checkout = request.getParameter("checkout");
        if (checkout != null && checkout.equalsIgnoreCase("yes")) {
            // Request instructs to complete the purchase
            cartBean.checkOut();
            System.out.println("Shopping cart checked out ");
        }

        //Delete
        String delete = request.getParameter("delete");
        if (checkout != null && checkout.equalsIgnoreCase("yes")) {
            // Request instructs to complete the purchase
            cartBean.delete();
            System.out.println("Shopping cart was deleted");
        }

        if (cartBean != null && fail == false) {
            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(200);
            out.print(cartBean.getJSON());
            out.flush();
        }
    }

}
