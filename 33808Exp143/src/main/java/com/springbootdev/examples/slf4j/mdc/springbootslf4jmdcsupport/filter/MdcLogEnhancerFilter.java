package com.springbootdev.examples.slf4j.mdc.springbootslf4jmdcsupport.filter;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Random;

@Component
public class MdcLogEnhancerFilter implements Filter {

    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException
    {
//        MDC.put("userId", "www.SpringBootDev.com");
//        MDC.put("userId", String.valueOf(servletRequest.hashCode()));
//        MDC.put("userId", String.valueOf(new Random().nextInt()));
//        MDC.put("userId", String.valueOf(servletRequest.toString()));

        MDC.put("userId", "user principal:"+ ((HttpServletRequest)servletRequest).getUserPrincipal());

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
