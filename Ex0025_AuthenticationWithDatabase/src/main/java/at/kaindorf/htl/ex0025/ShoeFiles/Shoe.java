package at.kaindorf.htl.ex0025.ShoeFiles;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="shoes")
public class Shoe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String size;

    @Column(nullable = false)
    private String color;
}
