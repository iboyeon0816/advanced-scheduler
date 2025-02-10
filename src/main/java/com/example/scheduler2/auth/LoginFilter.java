package com.example.scheduler2.auth;

import com.example.scheduler2.exception.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
public class LoginFilter implements Filter {

    private static final String[] WHITE_LIST = { "/signup" , "/login" };

    private final ObjectMapper objectMapper;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String requestURI = httpRequest.getRequestURI();

        if (!inWhiteList(requestURI)) { // 인증 필요
            HttpSession session = httpRequest.getSession(false);

            if (session == null || session.getAttribute(SessionConst.LOGIN_USER) == null) {
                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                httpResponse.setCharacterEncoding(StandardCharsets.UTF_8.name());
                httpResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);

                ErrorResponse body = ErrorResponse.of(HttpStatus.UNAUTHORIZED, "Login is required");
                httpResponse.getWriter().write(objectMapper.writeValueAsString(body));
                return;
            }
        }

        chain.doFilter(request, response);
    }

    private boolean inWhiteList(String requestURI) {
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }
}
