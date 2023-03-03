package com.example.api.api.security;

import com.example.api.api.exception.ErrorCode;
import com.example.api.api.exception.InvalidRequestException;
import com.example.api.api.http.response.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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

    public JwtAuthFilter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String accessToken = request.getHeader("Authorization").replace("Bearer ", "");
/* kotlin code
//            val principalDetails = jwtService.parseAccessToken(accessToken).also {
//                requestContext.roleTypes = it.roleTypes
//                requestContext.socialType = it.socialType
//                requestContext.userKey = it.userKey
//                requestContext.validUser = true
//            }
//            val authentication = principalDetails.createAuthentication()
            SecurityContextHolder.getContext().setAuthentication(principalDetails.createAuthentication());
 */

            filterChain.doFilter(request, response);

        } catch (InvalidRequestException e) {
            logger.error(e.getDebugMessage());
            ErrorResponse errorResponse = new ErrorResponse(
                    ErrorCode.INTERNAL_SERVER_ERROR,
                    e.getMessage(),
                    ErrorCode.INTERNAL_SERVER_ERROR.getCode()
            );
            response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
        }
    }
//    @Override
//    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
//        return request.getRequestURI().split("/")[1].equals("open-api");
//    }
}
