package dev.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShoppingCartFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String blockRequest = servletRequest.getParameter("blockTheRequest");
        if(blockRequest == null || blockRequest.equals("false")) {
            filterChain.doFilter(servletRequest, servletResponse);
        }
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.sendError(HttpServletResponse.SC_FORBIDDEN);
    }

    @Override
    public void destroy() {

    }
}
