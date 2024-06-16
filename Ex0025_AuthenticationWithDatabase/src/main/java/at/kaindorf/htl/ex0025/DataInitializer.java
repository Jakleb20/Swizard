package at.kaindorf.htl.ex0025;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.annotation.PostConstruct;

@Configuration
public class DataInitializer {

    @Autowired
    private MyUserRepository myUser;

    @Autowired
    private MyUserRepository myUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        if (myUserRepository.findByName("admin") == null) {
            MyUser admin = new MyUser();
            admin.setName("admin");
            admin.setPassword(passwordEncoder.encode("adminpassw"));
            admin.setYearOfBirth(0);
            admin.setRole(Role.ROLE_ADMIN);
            myUserRepository.save(admin);
        }

        if (myUserRepository.findByName("user") == null) {
            MyUser user = new MyUser();
            user.setName("user");
            user.setPassword(passwordEncoder.encode("userpassw"));
            user.setYearOfBirth(0);
            user.setRole(Role.ROLE_USER);
            myUserRepository.save(user);
        }
    }

}

