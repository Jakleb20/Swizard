package at.kaindorf.htl.ex0025;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MyUserServiceImpl implements IMyUserService
{
  private MyUserRepository userRepository;

  private PasswordEncoder passwordEncoder;

  @Autowired
  public MyUserServiceImpl(MyUserRepository userRepository, PasswordEncoder passwordEncoder)
  {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public void saveUser(UserDto userDto)
  {
    MyUser user = new MyUser();
    user.setName(userDto.getName());
    user.setYearOfBirth(userDto.getYearOfBirth());
    user.setPassword( passwordEncoder.encode(userDto.getPassword()) );
    userRepository.save(user);
  }

  @Override
  public MyUser findByName(String name)
  {
    return userRepository.findByName(name);
  }

  @Override
  public List<UserDto> findAllUsers()
  {
    List<MyUser> users = userRepository.findAll();
    return users.stream().map( user ->
    {
      UserDto userDto = new UserDto();
      userDto.setName(user.getName());
      userDto.setYearOfBirth(user.getYearOfBirth());
      return userDto;
    }).collect(Collectors.toList());
  }
}
