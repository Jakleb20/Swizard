package at.kaindorf.htl.ex0025;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="myusers")
public class MyUser {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(nullable = false, unique = true)
  private String name;

  @Column(nullable = false)
  private String password;

  private int yearOfBirth;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Role role;
}
