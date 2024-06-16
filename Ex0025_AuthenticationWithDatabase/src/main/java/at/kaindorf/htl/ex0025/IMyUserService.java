package at.kaindorf.htl.ex0025;

import java.util.List;

public interface IMyUserService
{
  void saveUser(UserDto userDto);

  MyUser findByName(String name);

  List<UserDto> findAllUsers();
}
