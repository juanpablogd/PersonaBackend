package com.alphai.persona.web.controlador;

import com.alphai.persona.dominio.dto.AuthRequest;
import com.alphai.persona.dominio.dto.AuthResponse;
import com.alphai.persona.dominio.servicio.AiUserDetailsService;
import com.alphai.persona.web.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AiUserDetailsService aiUserDetailsService;
    @Autowired
    private JWTUtil jwtUtil;
    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> createToken(@RequestBody AuthRequest request){
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPass()));
            UserDetails userDetails = aiUserDetailsService.loadUserByUsername(request.getUsername());
            String jwt = jwtUtil.generateToken(userDetails);
            return new ResponseEntity<>(new AuthResponse(jwt),HttpStatus.OK);
        } catch (BadCredentialsException e){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

    }
}
