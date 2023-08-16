package com.example.jwt01.controller;


import com.example.jwt01.entity.User;
import com.example.jwt01.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import com.example.jwt01.utils.JWTUtils;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/user/login")
    public Map<String, Object> login(User user) {
        log.info("name&&pwd=" + user.getName() + user.getPassword());
        Map<String, Object> map = new HashMap<>();
        try {
            User userInfo = userService.login(user);
            Map<String, String> payload = new HashMap<>();
            payload.put("id", user.getId());
            payload.put("name", user.getName());
            String token = JWTUtils.getToken(payload);
            map.put("state", true);
            map.put("token", token);
            map.put("user", userInfo.getName());
        } catch (Exception e) {
            map.put("state", false);
            map.put("msg", e.getMessage());
        }
        return map;
    }

    @GetMapping("/user/tt")
    public void tt(HttpServletRequest request) {
        log.info(String.valueOf(request));
    }

}
