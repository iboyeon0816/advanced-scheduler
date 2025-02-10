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

/**
 * 모든 요청에 대해 사용자의 인증 여부를 검사하는 필터이다.
 *
 * 화이트리스트에 포함된 경로에 대해서는 인증 검사를 수행하지 않으며,
 * 이외에는 세션 정보를 확인하여 인증되지 않은 경우 401 응답을 반환한다.
 */
@RequiredArgsConstructor
public class LoginFilter implements Filter {

    // 인증이 필요하지 않은 요청 URI 목록
    private static final String[] WHITE_LIST = { "/signup" , "/login" };

    private final ObjectMapper objectMapper;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String requestURI = httpRequest.getRequestURI();

        if (!inWhiteList(requestURI)) { // 인증 필요
            HttpSession session = httpRequest.getSession(false);

            // 인증 실패 시 401 응답 반환
            if (session == null || session.getAttribute(SessionConst.LOGIN_USER) == null) {
                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                httpResponse.setCharacterEncoding(StandardCharsets.UTF_8.name());
                httpResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);

                ErrorResponse body = ErrorResponse.of(HttpStatus.UNAUTHORIZED, "Login is required");
                httpResponse.getWriter().write(objectMapper.writeValueAsString(body));
                return;
            }
        }

        // 인증 완료 시 다음 필터로 요청 전달
        chain.doFilter(request, response);
    }

    private boolean inWhiteList(String requestURI) {
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }
}
