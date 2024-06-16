package at.kaindorf.htl.ex0025;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyUserRepository extends JpaRepository<MyUser,Long>
{
  public MyUser findByName(String name);
}
