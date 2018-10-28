package my.app.bt.bettertogether.service;

import lombok.extern.slf4j.Slf4j;
import my.app.bt.bettertogether.domain.Role;
import my.app.bt.bettertogether.domain.User;
import my.app.bt.bettertogether.entity.UserEntity;
import my.app.bt.bettertogether.exception.ResourceNotFoundException;
import my.app.bt.bettertogether.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@Slf4j
public class UserService implements UserDetailsService {

    private UserRepository userRepo;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User loadUserByUsername(String username)  {

        return this.userRepo.findByUsername(username)
                            .map( entity -> {
                                User user = new User();
                                user.setUsername(entity.getUsername());
                                user.setPassword(entity.getPassword());
                                user.setRoles(Collections.singletonList(Role.ROLE_ADMIN)); // TMP
                                user.setEnabled(entity.getEnabled());
                                return user;
                            })
                            .orElseThrow(() -> new UsernameNotFoundException(username + " not found"));

    }

    public UserEntity findByUsername(String username) {
        return this.userRepo.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("user", "username", username));
    }

    public UserEntity signUp(User user){
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(user.getUsername());
        userEntity.setPassword( passwordEncoder.encode(user.getPassword()) );
        user.setEnabled(true);
        return  this.userRepo.save(userEntity);
    }

}