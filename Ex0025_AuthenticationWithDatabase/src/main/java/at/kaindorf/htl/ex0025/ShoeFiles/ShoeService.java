package at.kaindorf.htl.ex0025.ShoeFiles;

import java.util.List;

public interface ShoeService {
    void saveShoe(ShoeDto shoeDto);
    List<ShoeDto> getAllShoes();
}
