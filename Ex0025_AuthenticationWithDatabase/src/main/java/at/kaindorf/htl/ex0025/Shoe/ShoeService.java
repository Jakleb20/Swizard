package at.kaindorf.htl.ex0025.Shoe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoeService {
    private final ShoeRepository shoeRepository;

    @Autowired
    public ShoeService(ShoeRepository shoeRepository) {
        this.shoeRepository = shoeRepository;
    }

    public Shoe saveShoe(Shoe shoe) {
        return shoeRepository.save(shoe);
    }

    public List<Shoe> getAllShoes() {
        return shoeRepository.findAll();
    }
}
