package at.kaindorf.htl.ex0025;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class MyUserDetailsService implements UserDetailsService {

  private MyUserRepository myUserRepository;
  private Logger logger = LoggerFactory.getLogger(getClass());

  @Autowired
  public MyUserDetailsService(MyUserRepository myUserRepository) {
    this.myUserRepository = myUserRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    logger.info("Loading user by username: {}", username);
    MyUser user = myUserRepository.findByName(username);
    if (user != null) {
      logger.info("User found: {}", user);
      return new User(user.getName(), user.getPassword(), Collections.singleton(new SimpleGrantedAuthority(user.getRole().name())));
    } else {
      logger.error("User not found: {}", username);
      throw new UsernameNotFoundException("Invalid username: " + username);
    }
  }
}
