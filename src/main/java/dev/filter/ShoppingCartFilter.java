package dev.filter;

import javax.servlet.*;
import java.io.IOException;

public class ShoppingCartFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String blockRequest = servletRequest.getParameter("blockTheRequest");
        if(blockRequest != null || blockRequest.equals("false")) {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
