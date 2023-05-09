package cat.itacademy.barcelonactiva.RoyoTerol.Marina.s05.t01.n02.model.service;

import cat.itacademy.barcelonactiva.RoyoTerol.Marina.s05.t01.n02.model.dto.FlowerDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FlowerService {

    FlowerDto createFlower(FlowerDto flowerDto);

    List<FlowerDto> getAllFlowers();

    FlowerDto getOneFlower(Integer id);

    FlowerDto updateFlower(FlowerDto flowerDto, Integer id);

    boolean deleteFlower(Integer id);

    boolean checkString(String string);

}
