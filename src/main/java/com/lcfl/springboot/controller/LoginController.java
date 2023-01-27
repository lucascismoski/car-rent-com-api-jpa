package com.lcfl.springboot.controller;

import com.lcfl.springboot.dto.Login;
import com.lcfl.springboot.dto.Session;
import com.lcfl.springboot.model.User;
import com.lcfl.springboot.repository.UserRepository;
import com.lcfl.springboot.security.JWTCreator;
import com.lcfl.springboot.security.JWTObject;
import com.lcfl.springboot.security.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;

@RestController
public class LoginController {
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private SecurityConfig securityConfig;
    @Autowired
    private UserRepository repository;

    @PostMapping("/api/login")
    public Session logar (@RequestBody Login login) {
        User user = repository.findByUsername(login.getUsername());
        if(user!=null) {
            boolean passwordOk =  encoder.matches(login.getPassword(), user.getPassword());
            if (!passwordOk) {
                throw new RuntimeException("Invalid password for user: " + login.getUsername());
            }

            Session session = new Session();
            session.setLogin(user.getUsername());

            JWTObject jwtObject = new JWTObject();
            jwtObject.setIssuedAt(new Date(System.currentTimeMillis()));
            jwtObject.setExpiration((new Date(System.currentTimeMillis() + SecurityConfig.EXPIRATION)));
            jwtObject.setRoles(user.getRoles());
            session.setToken(JWTCreator.create(SecurityConfig.PREFIX, SecurityConfig.KEY, jwtObject));
            return session;
        }else {
            throw new RuntimeException("Error on login");
        }
    }
}
