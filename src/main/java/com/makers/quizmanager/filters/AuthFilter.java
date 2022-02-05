package com.makers.quizmanager.filters;

import com.makers.quizmanager.Constants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Order(Ordered.HIGHEST_PRECEDENCE)
public class AuthFilter extends GenericFilterBean {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        ((HttpServletResponse) response).setHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse) response).setHeader("Access-Control-Allow-Credentials", "true");
        ((HttpServletResponse) response).setHeader("Access-Control-Allow-Methods",
                "GET, POST, UPDATE" );
        ((HttpServletResponse) response).setHeader("Access-Control-Max-Age", "3600");
        ((HttpServletResponse) response).setHeader("Access-Control-Allow-Headers",
                "Origin, X-Requested-With, Content-Type, Accept, Key, Authorization");

        if("OPTIONS".equalsIgnoreCase(((HttpServletRequest) request).getMethod())) {
            ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_OK);
        } else {
            String authHeader = ((HttpServletRequest) request).getHeader("Authorization");
            if (authHeader != null) {
                String[] authHeaderArr = authHeader.split("Bearer ");
                if (authHeaderArr.length > 1 && authHeaderArr[1] != null) {
                    String token = authHeaderArr[1];
                    try {
                        Claims claims = Jwts.parser().setSigningKey(Constants.API_SECRET_KEY)
                                .parseClaimsJws(token).getBody();
                        httpServletRequest.setAttribute("userId", Integer.parseInt(claims.get("userId").toString()));
                        httpServletRequest.setAttribute("roleId", Integer.parseInt(claims.get("roleId").toString()));
                    } catch (Exception e) {
                        httpServletResponse.sendError(HttpStatus.FORBIDDEN.value(), "Invalid or expired token");
                        return;
                    }
                } else {
                    httpServletResponse.sendError(HttpStatus.FORBIDDEN.value(), "Authorization token must be Bearer [token]");
                    return;
                }
            } else {
                httpServletResponse.sendError(HttpStatus.FORBIDDEN.value(), "Authorization token must be provided");
                return;
            }
            chain.doFilter(httpServletRequest, httpServletResponse);
        }
    }
}
