package com.example.jwt01.interceptor;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.jwt01.utils.JWTUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.HashMap;
import java.util.Map;

public class JWTInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Map<String, Object> map = new HashMap<>();
        String token = request.getHeader("token");
        try {
            DecodedJWT verifiedInfo = JWTUtils.verify(token);
            return true;
        } catch (Exception e) {
            map.put("msg", e.getMessage());
        }
        map.put("state", false);
        String json = new ObjectMapper().writeValueAsString(map);
        response.setContentType("application/json:charset=UTF_8");
        response.getWriter().println(json);
        return false;
    }
}
