package com.treintaytres.vdc_backend.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class RoutingFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        String uri = request.getRequestURI();

        if (uri.startsWith("/test")) {
            DbContext.setType("dbt");
            uri = uri.replace("/test", "");
        } else if (uri.startsWith("/pre")) {
            DbContext.setType("dbpre");
            uri = uri.replace("/pre", "");
        }

        String finalUri = uri;
        HttpServletRequest httpRequest = new HttpServletRequestWrapper(request) {
            @Override
            public String getServletPath() {
                return finalUri;
            }
            @Override
            public String getRequestURI() {
                return finalUri;
            }
        };

        try {
            filterChain.doFilter(httpRequest, response);
        } finally {
            DbContext.clearContext();
        }
    }
}
