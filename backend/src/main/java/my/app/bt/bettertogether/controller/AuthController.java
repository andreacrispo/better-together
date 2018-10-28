package my.app.bt.bettertogether.controller;

import lombok.extern.slf4j.Slf4j;
import my.app.bt.bettertogether.util.JWTUtil;
import my.app.bt.bettertogether.domain.AuthRequest;
import my.app.bt.bettertogether.domain.AuthResponse;
import my.app.bt.bettertogether.domain.User;
import my.app.bt.bettertogether.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

    private JWTUtil jwtUtil;
    private PasswordEncoder passwordEncoder;
    private UserService userService;

    @Autowired
    public AuthController(JWTUtil jwtUtil, PasswordEncoder passwordEncoder, UserService userService) {
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @PostMapping(value = "/login")
    public Mono<ResponseEntity<AuthResponse>> auth(@RequestBody AuthRequest request) {
        String username = request.getUsername();
        return Mono.just(userService.loadUserByUsername(username))
                          .map(userDetails -> {
                                if ( passwordEncoder.matches(request.getPassword(), userDetails.getPassword()) ) {
                                    return ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(userDetails)));
                                } else {
                                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
                                }
                          });
    }


    @PostMapping(value = "/signup")
    public Mono<ResponseEntity> auth(@RequestBody User user) {
        return Mono.just(userService.signUp(user))
                  .map(ResponseEntity::ok);
    }

}