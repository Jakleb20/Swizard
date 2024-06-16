package at.kaindorf.htl.ex0025.ShoeFiles;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoeServiceImpl implements ShoeService {

    private final ShoeRepository shoeRepository;

    @Autowired
    public ShoeServiceImpl(ShoeRepository shoeRepository) {
        this.shoeRepository = shoeRepository;
    }

    @Override
    public void saveShoe(ShoeDto shoeDto) {
        Shoe shoe = new Shoe();
        shoe.setName(shoeDto.getName());
        shoe.setSize(String.valueOf(shoeDto.getSize()));
        shoe.setColor(shoeDto.getColor());
        shoeRepository.save(shoe);
    }

    @Override
    public List<ShoeDto> getAllShoes() {
        return shoeRepository.findAll().stream()
                .map(shoe -> {
                    ShoeDto shoeDto = new ShoeDto();
                    shoeDto.setName(shoe.getName());
                    shoeDto.setSize(Integer.parseInt(shoe.getSize()));
                    shoeDto.setColor(shoe.getColor());
                    return shoeDto;
                }).collect(Collectors.toList());
    }
}
