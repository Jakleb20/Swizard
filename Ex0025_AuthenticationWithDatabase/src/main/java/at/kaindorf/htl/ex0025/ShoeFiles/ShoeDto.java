package at.kaindorf.htl.ex0025.ShoeFiles;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ShoeDto {
    @NotEmpty(message="Name should not be empty")
    private String name;

    private int size;

    private String color;
}
