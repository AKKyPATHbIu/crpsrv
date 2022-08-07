package com.epam.crpsrv.security;

import java.io.IOException;
import java.util.List;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class IpFilter extends OncePerRequestFilter {

    private final List ALLOWED_IP_ADDRESSES = List.of(
            "192.168.80.1",
            "0:0:0:0:0:0:0:1"
    );

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (accept(request.getRemoteAddr())) {
            filterChain.doFilter(request, response);
        }
    }

    private boolean accept(String ipAddress) {
        return ALLOWED_IP_ADDRESSES.contains(ipAddress);
    }
}