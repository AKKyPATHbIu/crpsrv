package com.epam.crpsrv.security;

import java.io.IOException;
import java.util.regex.Pattern;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@Profile({"compose", "local"})
public class IpFilter extends OncePerRequestFilter {

    private final static String REG_EXP_ALLOWED_IP_ADDRESSES = "192\\.168\\.*|0\\:0\\:0\\:0\\:0\\:0\\:0\\:1";
    private final static Pattern PATTERN_ALLOWED_IP_ADDRESSES = Pattern.compile(REG_EXP_ALLOWED_IP_ADDRESSES);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (accept(request.getRemoteAddr())) {
            filterChain.doFilter(request, response);
        }
    }

    private boolean accept(String ipAddress) {
        return PATTERN_ALLOWED_IP_ADDRESSES.matcher(ipAddress).find();
    }
}