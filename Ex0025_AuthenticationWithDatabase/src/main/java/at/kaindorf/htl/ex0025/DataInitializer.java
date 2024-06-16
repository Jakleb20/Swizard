package at.kaindorf.htl.ex0025;

import at.kaindorf.htl.ex0025.ShoeFiles.Shoe;
import at.kaindorf.htl.ex0025.ShoeFiles.ShoeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.annotation.PostConstruct;

@Configuration
public class DataInitializer {

    @Autowired
    private MyUserRepository myUserRepository;

    @Autowired
    private ShoeRepository shoeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        // Adding default users
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

        if (shoeRepository.count() == 0) {
            Shoe shoe1 = new Shoe();
            shoe1.setName("Nike Air Max");
            shoe1.setSize("42");
            shoe1.setColor("Black");
            shoeRepository.save(shoe1);

            Shoe shoe2 = new Shoe();
            shoe2.setName("Adidas Ultraboost");
            shoe2.setSize("43");
            shoe2.setColor("White");
            shoeRepository.save(shoe2);

            Shoe shoe3 = new Shoe();
            shoe3.setName("Puma Suede");
            shoe3.setSize("44");
            shoe3.setColor("Blue");
            shoeRepository.save(shoe3);
        }
    }
}
