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

import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService
{
  private MyUserRepository myUserRepository;

  private Logger logger = LoggerFactory.getLogger(getClass());

  @Autowired
  public MyUserDetailsService(MyUserRepository myUserRepository)
  {
    this.myUserRepository = myUserRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
  {
    MyUser user = myUserRepository.findByName(username);
    logger.info("--> "+user);
    if(user != null)
    {
      List<SimpleGrantedAuthority> roles = new ArrayList<>();
      roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
      return new User(user.getName(), user.getPassword(), roles);
    }
    else
    {
      throw new UsernameNotFoundException("--> Invalid username !!!");
    }
  }
}
