package com.example.api.api.security;

import com.example.api.api.exception.ErrorCode;
import com.example.api.api.exception.ExceptionController;
import com.example.api.api.exception.InvalidRequestException;
import com.example.api.api.http.response.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @author junyeong.jo .
 * @since 2023-02-28
 */
@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    private final ObjectMapper objectMapper;
    private final ExceptionController exceptionController;
    private final JwtManager jwtManager;
    public JwtAuthFilter(
            ObjectMapper objectMapper,
            ExceptionController exceptionController,
            JwtManager jwtManager
    ) {
        this.objectMapper = objectMapper;
        this.exceptionController = exceptionController;
        this.jwtManager = jwtManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ErrorResponse errorResponse;
        try {
            String accessToken = request.getHeader("Authorization");
            if (accessToken != null && accessToken.startsWith("Bearer ")) {
                String bearerToken = accessToken.replace("Bearer ", "");

                SecurityContextHolder.getContext().setAuthentication(jwtManager.getAuthentication(bearerToken));
                filterChain.doFilter(request, response);
            } else {
                errorResponse = new ErrorResponse(
                        ErrorCode.INVALID_ACCESS_TOKEN,
                        "access token is null"
                );
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.setStatus(400);
                response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
            }

        } catch (InvalidRequestException e) {
            logger.error(e);
            e.printStackTrace();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            errorResponse = new ErrorResponse(
                    e.getErrorCode(),
                    e.getDebugMessage()
            );
            response.setStatus(400);
            response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
        } catch (Exception e) {
            logger.error(e);
            e.printStackTrace();
            errorResponse = new ErrorResponse(
                    ErrorCode.INTERNAL_SERVER_ERROR,
                    e.getMessage()
            );
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(500);
            response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
        }
    }
    public void sendErrorResponse() {

    }
//    @Override
//    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
//        return request.getRequestURI().split("/")[1].equals("open-api");
//    }
}
